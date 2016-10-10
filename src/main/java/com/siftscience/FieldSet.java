package com.siftscience;

import com.google.gson.*;
import com.siftscience.exception.InvalidFieldException;
import com.siftscience.exception.MissingFieldException;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class FieldSet<T extends FieldSet<T>> {

    public static final String API_KEY = "$api_key";
    public static final String USER_ID = "$user_id";
    public static final String SESSION_ID = "$session_id";
    public static final String EVENT_TYPE = "$type";
    public static final String IP = "$ip";
    public static final String TIME = "$time";
    public static final String IS_BAD = "$is_bad";
    public static final String ABUSE_TYPE = "$abuse_type";

    private static Gson defaultGson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    protected static Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(FieldSet.class, new FieldSetDeserializer())
            .registerTypeHierarchyAdapter(FieldSet.class, new FieldSetSerializer())
            .create();

    private JsonObject customFields = new JsonObject();

    protected abstract boolean allowCustomFields();

    @Nullable
    public abstract String getEventType();

    public T setCustomField(@NotNull String key, @Nullable Number val) {
        if (customFieldSetup(key, val)) {
            this.customFields.addProperty(key, val);
        }
        return (T) this;
    }
    public T setCustomField(@NotNull String key, @Nullable Boolean val) {
        if (customFieldSetup(key, val)) {
            this.customFields.addProperty(key, val);
        }
        return (T) this;
    }
    public T setCustomField(@NotNull String key, @Nullable String val) {
        if (customFieldSetup(key, val)) {
            this.customFields.addProperty(key, val);
        }
        return (T) this;
    }
    private boolean customFieldSetup(@NotNull String key, @Nullable Object val) {
        if (!allowCustomFields()) {
            return false;
        }

        validateCustomFieldKey(key);
        if (val == null) {
            this.clearCustomField(key);
            return false;
        }
        return true;
    }
    public T clearCustomField(@NotNull String key) {
        this.customFields.remove(key);
        return (T) this;
    }
    public T clearCustomFields() {
        this.customFields.entrySet().clear();
        return (T) this;
    }

    private static class FieldSetSerializer implements JsonSerializer<FieldSet> {
        public JsonElement serialize(FieldSet src, Type t, JsonSerializationContext ctx) {
            JsonObject jsonObj = defaultGson.toJsonTree(src).getAsJsonObject();
            for (Map.Entry<String,JsonElement> entry : src.customFields.entrySet()) {
                jsonObj.add(entry.getKey(), entry.getValue());
            }
            jsonObj.addProperty(EVENT_TYPE, src.getEventType());
            jsonObj.addProperty(API_KEY, src.getApiKey());
            return jsonObj;
        }
    }

    private static class FieldSetDeserializer implements JsonDeserializer<FieldSet> {
        public FieldSet deserialize(JsonElement json, Type t, JsonDeserializationContext ctx) {
            FieldSet fieldSet = defaultGson.fromJson(json, t);

            // First turn into a JsonObject and then manually set the API key.
            JsonObject jsonObj = json.getAsJsonObject();
            fieldSet.setApiKey(FieldSet.<String>getField(jsonObj, "$api_key", false));

            // Loop through all top level JSON fields and pick out the custom field keys.
            if (fieldSet.allowCustomFields()) {
                for (Map.Entry<String,JsonElement> entry : jsonObj.entrySet()) {
                    String key = entry.getKey();
                    JsonElement val = entry.getValue();

                    // Invalid custom field keys are skipped over.
                    try {
                        validateCustomFieldKey(key);
                    } catch (InvalidFieldException e) {
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
            }

            return fieldSet;
        }
    }

    private static <T> T getField(@NotNull JsonObject jsonObj,
                                  @NotNull String key,
                                  boolean required) {

        // First check that the key is present.
        if (!jsonObj.has(key)) {
            if (required) {
                throw new MissingFieldException(MissingFieldException.messageForKey(key));
            } else {
                return null;
            }
        }

        // Next, cast to type param and return.
        JsonPrimitive p = jsonObj.getAsJsonPrimitive(key);
        if (p == null || p.isJsonNull()) {
            return null;
        } else if (p.isBoolean()) {
            return (T) (Boolean) p.getAsBoolean();
        } else if (p.isNumber()) {
            return (T) p.getAsNumber();
        } else if (p.isString()) {
            return (T) p.getAsString();
        }

        return null;
    }

    private static void validateCustomFieldKey(String key) {
        if (key == null) {
            throw new InvalidFieldException("Field keys may not be null.");
        } else if (key.isEmpty()) {
            throw new InvalidFieldException("Field keys may not be empty.");
        } else if (key.charAt(0) == '$') {
            throw new InvalidFieldException(
                    "Custom field \"" + key + "\" may not begin with a dollar sign.");
        }
    }

    public String toJson() {
        return gson.toJson(this);
    }

    /**
     * The default validation function checks that either the user id or the session id exists
     * in the resulting JSON since it's the most common requirement among all event types. Also
     * checks that the API key exists.
     */
    public void validate() {
        // API key must exist.
        validateApiKey();

        // At least one of user id or session id must also exist.
        validateUserIdOrSessionId();
    }

    protected void validateApiKey() {
        validateStringField(API_KEY, getApiKey());
    }
    protected void validateUserId() {
        FieldSet.<String>getField(gson.toJsonTree(this).getAsJsonObject(), USER_ID, true);
    }

    protected void validateSessionId() {
        FieldSet.<String>getField(gson.toJsonTree(this).getAsJsonObject(), SESSION_ID, true);
    }

    protected static void validateStringField(String key, String val) {
        if (val == null || val.isEmpty()) {
            throw new MissingFieldException(MissingFieldException.messageForKey(key));
        }
    }
    protected static void validateLongField(String key, Long val) {
        if (val == null) {
            throw new MissingFieldException(MissingFieldException.messageForKey(key));
        }
    }
    protected static void validateBooleanField(String key, Boolean val) {
        if (val == null) {
            throw new MissingFieldException(MissingFieldException.messageForKey(key));
        }
    }

    protected void validateUserIdOrSessionId() {
        int missing = 0;
        try {
            validateUserId();
        } catch (MissingFieldException e) {
            missing++;
        }
        try {
            validateSessionId();
        } catch (MissingFieldException e) {
            missing++;
        }
        if (missing > 1) {
            throw new MissingFieldException("Either \"" + USER_ID + "\" or \"" +
                    SESSION_ID + "\" must exist.");
        }
    }

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public T setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return (T) this;
    }

    // Serialize to JSON and then immediately deserialize to get a deep copy of this FieldSet.
//    public T clone() {
//        return (T) gson.fromJson(this.toJson(), this.getClass());
//    }
}
