package com.siftscience;

import com.google.gson.*;
import com.siftscience.exception.InvalidFieldException;
import com.siftscience.model.GetDecisionsResponseBody;

import java.lang.reflect.Type;
import java.util.Map;

import static com.siftscience.model.GetDecisionFieldSet.*;

/**
 * FieldSet represents a set of fields/parameters to send along with an API request. It handles
 * JSON serialization, field validation, and provides support for custom fields. One important
 * thing to note about FieldSets is that although they know how to serialize into JSON, their
 * fields will not always be used as such. For example, some requests don't have a JSON body at
 * all so the "$api_key" field of the FieldSet will be used as a query parameter of the HTTP
 * request instead.
 */
public abstract class FieldSet<T extends FieldSet<T>> {
    // Reserved field names.
    public static final String API_KEY = "$api_key";
    public static final String USER_ID = "$user_id";
    public static final String SESSION_ID = "$session_id";
    public static final String EVENT_TYPE = "$type";
    public static final String IP = "$ip";
    public static final String TIME = "$time";
    public static final String IS_BAD = "$is_bad";
    public static final String ABUSE_TYPE = "$abuse_type";

    // Serialization happens in two stages. First, the object is serialized with `defaultGson`
    // according to the @Expose and @SerializedName annotations on the subclass type. Then, the
    // resulting JSON map is augmented with the serialization results of the `gson` Gson instance
    // below which handles custom fields.
    private static Gson defaultGson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    protected static Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(FieldSet.class, new FieldSetDeserializer())
            .registerTypeHierarchyAdapter(FieldSet.class, new FieldSetSerializer())
            .registerTypeAdapter(GetDecisionsResponseBody.Decision.class,
                    new DecisionSetDeserializer())
            .create();

    // Every Events API request will have a reserved "$type" field that never changes for that
    // event type so subclasses should return their associated type such as "$create_order".
    public String getEventType() {
        return null;
    }

    protected boolean shouldJsonSerializeApiKey() {
        return false;
    }

    // customFields is the map of all custom k/v fields belonging to this FieldSet.
    private JsonObject customFields = new JsonObject();

    // Setters for custom fields. Values can only be numbers, booleans, or strings.
    protected T setCustomField(String key, Number val) {
        if (customFieldSetup(key, val)) {
            this.customFields.addProperty(key, val);
        }
        return (T) this;
    }
    protected T setCustomField(String key, Boolean val) {
        if (customFieldSetup(key, val)) {
            this.customFields.addProperty(key, val);
        }
        return (T) this;
    }
    protected T setCustomField(String key, String val) {
        if (customFieldSetup(key, val)) {
            this.customFields.addProperty(key, val);
        }
        return (T) this;
    }
    private boolean customFieldSetup(String key, Object val) {
        if (val == null) {
            this.clearCustomField(key);
            return false;
        }
        return true;
    }

    // Clearing a custom field is equivalent to setting the value to null since nulls don't get
    // serialized.
    protected T clearCustomField(String key) {
        this.customFields.remove(key);
        return (T) this;
    }
    protected T clearCustomFields() {
        this.customFields.entrySet().clear();
        return (T) this;
    }

    public void validate() {
        for (Map.Entry<String, JsonElement> entry : customFields.entrySet()) {
            if (entry.getKey().startsWith("$")) {
                throw new InvalidFieldException("Custom field \"" + entry.getKey() +
                        "\" may not begin with a dollar sign.");
            }
        }
    }

    /**
     * Custom serialization adapter for DecisionSet
     */
    private static class DecisionSetDeserializer implements
            JsonDeserializer<GetDecisionsResponseBody.Decision> {
        @Override
        public GetDecisionsResponseBody.Decision deserialize(JsonElement json,
                                                             Type t,
                                                             JsonDeserializationContext ctx)
                throws JsonParseException {
            GetDecisionsResponseBody.Decision decision = defaultGson.fromJson(json, t);
            JsonObject asJsonObject = json.getAsJsonObject();
            if (asJsonObject.has("abuse_type")) {
                try {
                    decision.setAbuseType(AbuseType.valueOf(asJsonObject.get("abuse_type")
                            .getAsString().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    //Unable to deserialize abuseType
                }
            }
            if (asJsonObject.has("category")) {
                try {
                    decision.setCategory(DecisionCategory.valueOf(asJsonObject.get("category")
                        .getAsString().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    //Unable to deserialize category type
                }
            }
            if (asJsonObject.has("entity_type")) {
                try {
                    decision.setEntityType(EntityType.valueOf(asJsonObject.get("entity_type")
                    .getAsString().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    //Unable to deserialize entityType
                }
            }
            return decision;
        }
    }

    /**
     * Custom serialization adapter for all FieldSets.
     */
    private static class FieldSetSerializer implements JsonSerializer<FieldSet> {
        public JsonElement serialize(FieldSet src, Type t, JsonSerializationContext ctx) {
            // First look for all @Expose'd fields on the subclass.
            JsonObject jsonObj = defaultGson.toJsonTree(src).getAsJsonObject();

            // Next augment with the custom fields from this base class.
            for (Map.Entry<String, JsonElement> entry : src.customFields.entrySet()) {
                jsonObj.add(entry.getKey(), entry.getValue());
            }

            // And then add the common reserved fields shared between all FieldSets.
            jsonObj.addProperty(EVENT_TYPE, src.getEventType());
            if (src.shouldJsonSerializeApiKey()) {
                jsonObj.addProperty(API_KEY, src.getApiKey());
            }
            return jsonObj;
        }
    }

    /**
     * Custom deserialization adapter for all FieldSets.
     */
    private static class FieldSetDeserializer implements JsonDeserializer<FieldSet> {
        public FieldSet deserialize(JsonElement json, Type t, JsonDeserializationContext ctx) {
            FieldSet fieldSet = defaultGson.fromJson(json, t);

            // First turn into a JsonObject and then manually set the API key.
            JsonObject jsonObj = json.getAsJsonObject();
            if (jsonObj.has(API_KEY)) {
                JsonPrimitive p = jsonObj.getAsJsonPrimitive(API_KEY);
                if (p != null && !p.isJsonNull()) {
                    if (!p.isString()) {
                        throw new InvalidFieldException("API key must be a string.");
                    }
                    fieldSet.setApiKey(p.getAsString());
                }
            }

            // Loop through all top level JSON fields and pick out the custom field keys.
            for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
                String key = entry.getKey();
                JsonElement val = entry.getValue();

                if (key.startsWith("$")) {
                    continue;
                }

                if (val.isJsonNull()) {
                    // Null is ignored.
                    continue;
                } else if (!val.isJsonPrimitive()) {
                    // If not null and not a primitive, then that's not allowed.
                    throw new InvalidFieldException("\"" + key + "\" must be either" +
                            "a number, boolean, string, or null.");
                }

                // Now we know it's a primitive.
                JsonPrimitive primitiveVal = val.getAsJsonPrimitive();
                if (primitiveVal.isString()) {
                    fieldSet.setCustomField(key, primitiveVal.getAsString());
                } else if (primitiveVal.isNumber()) {
                    fieldSet.setCustomField(key, primitiveVal.getAsNumber());
                } else if (primitiveVal.isBoolean()) {
                    fieldSet.setCustomField(key, primitiveVal.getAsBoolean());
                }
            }

            return fieldSet;
        }
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public T setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return (T) this;
    }
}
