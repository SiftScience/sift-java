package com.siftscience;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siftscience.model.AddItemToCartFieldSet;
import com.siftscience.model.AddPromotionFieldSet;
import com.siftscience.model.App;
import com.siftscience.model.BaseAppBrowserFieldSet;
import com.siftscience.model.Browser;
import com.siftscience.model.CreateAccountFieldSet;
import com.siftscience.model.CreateCommentFieldSet;
import com.siftscience.model.CreateListingFieldSet;
import com.siftscience.model.CreateMessageFieldSet;
import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.CreatePostFieldSet;
import com.siftscience.model.CreateProfileFieldSet;
import com.siftscience.model.CreateReviewFieldSet;
import com.siftscience.model.CustomEventFieldSet;
import com.siftscience.model.LoginFieldSet;
import com.siftscience.model.RemoveItemFromCartFieldSet;
import com.siftscience.model.TransactionFieldSet;
import com.siftscience.model.UpdateAccountFieldSet;
import com.siftscience.model.UpdateCommentFieldSet;
import com.siftscience.model.UpdateListingFieldSet;
import com.siftscience.model.UpdateMessageFieldSet;
import com.siftscience.model.UpdateOrderFieldSet;
import com.siftscience.model.UpdatePostFieldSet;
import com.siftscience.model.UpdateProfileFieldSet;
import com.siftscience.model.UpdateReviewFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.object.IsCompatibleType.typeCompatibleWith;

public class BaseAppBrowserFieldSetTest {

    private static final String DUMMY_API_KEY = "your_api_key_here";
    private static final String DUMMY_TEST_FIELD = "test";
    private static final String DUMMY_USERID = "billy_jones_301";
    private static final String REQUEST_BODY_TEMPLATE = "{\n" +
        "\"$type\"      : \"$test\",\n" +
        "\"$api_key\"   : \"" + DUMMY_API_KEY + "\",\n" +
        "\"$test_field\": \"" + DUMMY_TEST_FIELD + "\",\n" +
        "\"$user_id\"   : \"" + DUMMY_USERID + "\"" +
        "%s" +
        "\n}";

    @Test
    public void testAllSubclasses() {
        Class<?> [] subclasses = {
            AddItemToCartFieldSet.class,
            AddPromotionFieldSet.class,
            CreateAccountFieldSet.class,
            CreateCommentFieldSet.class,
            CreateListingFieldSet.class,
            CreateMessageFieldSet.class,
            CreateOrderFieldSet.class,
            CreatePostFieldSet.class,
            CreateProfileFieldSet.class,
            CreateReviewFieldSet.class,
            CustomEventFieldSet.class,
            LoginFieldSet.class,
            RemoveItemFromCartFieldSet.class,
            TransactionFieldSet.class,
            UpdateAccountFieldSet.class,
            UpdateCommentFieldSet.class,
            UpdateListingFieldSet.class,
            UpdateMessageFieldSet.class,
            UpdateOrderFieldSet.class,
            UpdatePostFieldSet.class,
            UpdateProfileFieldSet.class,
            UpdateReviewFieldSet.class
        };

        for (Class<?> subclass : subclasses) {
            assertThat(subclass, typeCompatibleWith(BaseAppBrowserFieldSet.class));
        }
    }

    @Test
    public void testApp() throws Exception {
        String appName = "Calculator";
        String operatingSystem = "iOS";
        test(
            new TestFieldSet().setTestField(DUMMY_TEST_FIELD)
                .setUserId(DUMMY_USERID)
                .setApp(new App().setAppName(appName)
                    .setOperatingSystem(operatingSystem)),
            String.format(REQUEST_BODY_TEMPLATE, ",\n" +
                "  \"$app\"          : {\n" +
                "      \"$os\"       : \"" + operatingSystem + "\",\n" +
                "      \"$app_name\" : \"" + appName + "\"\n" +
                "   }\n")
        );
    }

    @Test
    public void testBrowser() throws Exception {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3)";
        test(
            new TestFieldSet().setTestField(DUMMY_TEST_FIELD)
                .setUserId(DUMMY_USERID)
                .setBrowser(new Browser().setUserAgent(userAgent)),
            String.format(REQUEST_BODY_TEMPLATE, ",\n" +
                "  \"$browser\"       : {\n" +
                "      \"$user_agent\": \"" + userAgent + "\"\n" +
                "   }\n")
        );
    }

    @Test
    public void testNeitherAppNorBrowser() throws Exception {
        test(
            new TestFieldSet().setTestField(DUMMY_TEST_FIELD).setUserId(DUMMY_USERID),
            String.format(REQUEST_BODY_TEMPLATE, "")
        );
    }

    private void test(TestFieldSet t, String expectedRequestBody) throws Exception {
        // Start a new mock server and enqueue a mock response.
        MockWebServer server = new MockWebServer();
        MockResponse response = new MockResponse();
        response.setResponseCode(HTTP_OK);
        response.setBody("{\n" +
            "    \"status\" : 0,\n" +
            "    \"error_message\" : \"OK\",\n" +
            "    \"time\" : 1327604222,\n" +
            "    \"request\" : \"" + TestUtils.unescapeJson(expectedRequestBody) + "\"\n" +
            "}");
        server.enqueue(response);
        server.start();
        HttpUrl baseUrl = server.url("");

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient(DUMMY_API_KEY);
        client.setBaseUrl(baseUrl);

        SiftRequest request = client.buildRequest(t);
        SiftResponse siftResponse = request.send();

        // Verify the request.
        RecordedRequest request1 = server.takeRequest();
        Assert.assertEquals("POST", request1.getMethod());
        Assert.assertEquals("/v205/events", request1.getPath());
        JSONAssert.assertEquals(expectedRequestBody, request.getFieldSet().toJson(), true);

        // Verify the response.
        Assert.assertEquals(HTTP_OK, siftResponse.getHttpStatusCode());
        Assert.assertEquals(0, (int) siftResponse.getBody().getStatus());
        JSONAssert.assertEquals(response.getBody().readUtf8(),
            siftResponse.getBody().toJson(), true);

        server.shutdown();
    }

    private static class TestFieldSet extends BaseAppBrowserFieldSet<TestFieldSet> {
        public TestFieldSet fromJson(String json) {
            return gson.fromJson(json, TestFieldSet.class);
        }

        @Expose
        @SerializedName("$test_field")
        private String test;

        @Override
        public String getEventType() {
            return "$test";
        }

        public String getTestField() {
            return test;
        }

        public TestFieldSet setTestField(String test) {
            this.test = test;
            return this;
        }
    }
}
