package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;

import com.siftscience.model.Address;
import com.siftscience.model.Comment;
import com.siftscience.model.CreateCommentFieldSet;
import com.siftscience.model.CreatePostFieldSet;
import com.siftscience.model.Image;
import com.siftscience.model.Post;
import com.siftscience.model.UpdateCommentFieldSet;
import com.siftscience.model.UpdatePostFieldSet;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentEventTest {
    @Test
    public void testCreateComment() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY_HERE\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$comment\"     : {\n" +
            "       \"$body\": \"Congrats on the new role!\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$parent_comment_id\": \"comment-23407\",\n" +
            "       \"$root_content_id\": \"listing-12923213\",\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"An old picture\"\n" +
            "       }]\n" +
            "   }" +
            "}";


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
        SiftClient client = new SiftClient("YOUR_API_KEY_HERE");
        client.setBaseUrl(baseUrl);

        Image image = new Image()
            .setDescription("An old picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Comment c = new Comment()
            .setBody("Congrats on the new role!")
            .setContactEmail("alex_301@domain.com")
            .setImages(images)
            .setParentCommentId("comment-23407")
            .setRootContentId("listing-12923213");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new CreateCommentFieldSet()
            .setApiKey("YOUR_API_KEY_HERE")
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setComment(c));


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

    @Test
    public void testCreatePost() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY_HERE\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$post\"     : {\n" +
            "       \"$subject\": \"My new apartment!\"," +
            "       \"$body\": \"Moved into my new apartment yesterday.\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$contact_address\": {\n" +
            "           \"$name\": \"Bill Jones\",\n" +
            "           \"$city\": \"New London\",\n" +
            "           \"$region\": \"New Hampshire\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"03257\"\n" +
            "       },\n" +
            "       \"$locations\": [{\n" +
            "           \"$city\": \"Seattle\",\n" +
            "           \"$region\": \"Washington\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"98112\"\n" +
            "       }],\n" +
            "       \"$categories\": [\"Personal\"],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"An old picture\"\n" +
            "       }],\n" +
            "       \"$expiration_time\": 1549063157000" +
            "   }" +
            "}";

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
        SiftClient client = new SiftClient("YOUR_API_KEY_HERE");
        client.setBaseUrl(baseUrl);

        Image image = new Image()
            .setDescription("An old picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address contactAddress = new Address()
            .setCity("New London")
            .setName("Bill Jones")
            .setZipCode("03257")
            .setRegion("New Hampshire")
            .setCountry("US");

        Address locationAddress = new Address()
            .setCity("Seattle")
            .setRegion("Washington")
            .setCountry("US")
            .setZipCode("98112");

        List<Address> locations = Collections.singletonList(locationAddress);

        Post p = new Post()
            .setBody("Moved into my new apartment yesterday.")
            .setCategories(Collections.singletonList("Personal"))
            .setContactAddress(contactAddress)
            .setLocations(locations)
            .setContactEmail("alex_301@domain.com")
            .setExpirationTime(1549063157000L)
            .setImages(images)
            .setSubject("My new apartment!");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new CreatePostFieldSet()
            .setApiKey("YOUR_API_KEY_HERE")
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setPost(p));

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

    @Test
    public void testUpdateComment() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$update_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY_HERE\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$comment\"     : {\n" +
            "       \"$body\": \"Congrats on the new role!\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$parent_comment_id\": \"comment-23407\",\n" +
            "       \"$root_content_id\": \"listing-12923213\",\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"An old picture\"\n" +
            "       }]\n" +
            "   }" +
            "}";


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
        SiftClient client = new SiftClient("YOUR_API_KEY_HERE");
        client.setBaseUrl(baseUrl);

        Image image = new Image()
            .setDescription("An old picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Comment c = new Comment()
            .setBody("Congrats on the new role!")
            .setContactEmail("alex_301@domain.com")
            .setImages(images)
            .setParentCommentId("comment-23407")
            .setRootContentId("listing-12923213");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdateCommentFieldSet()
            .setApiKey("YOUR_API_KEY_HERE")
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setComment(c));


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

    @Test
    public void testUpdatePost() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$update_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY_HERE\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$post\"     : {\n" +
            "       \"$subject\": \"My new apartment!\"," +
            "       \"$body\": \"Moved into my new apartment yesterday.\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$contact_address\": {\n" +
            "           \"$name\": \"Bill Jones\",\n" +
            "           \"$city\": \"New London\",\n" +
            "           \"$region\": \"New Hampshire\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"03257\"\n" +
            "       },\n" +
            "       \"$locations\": [{\n" +
            "           \"$city\": \"Seattle\",\n" +
            "           \"$region\": \"Washington\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"98112\"\n" +
            "       }],\n" +
            "       \"$categories\": [\"Personal\"],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"An old picture\"\n" +
            "       }],\n" +
            "       \"$expiration_time\": 1549063157000" +
            "   }" +
            "}";

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
        SiftClient client = new SiftClient("YOUR_API_KEY_HERE");
        client.setBaseUrl(baseUrl);

        Image image = new Image()
            .setDescription("An old picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address contactAddress = new Address()
            .setCity("New London")
            .setName("Bill Jones")
            .setZipCode("03257")
            .setRegion("New Hampshire")
            .setCountry("US");

        Address locationAddress = new Address()
            .setCity("Seattle")
            .setRegion("Washington")
            .setCountry("US")
            .setZipCode("98112");

        List<Address> locations = Collections.singletonList(locationAddress);

        Post p = new Post()
            .setBody("Moved into my new apartment yesterday.")
            .setCategories(Collections.singletonList("Personal"))
            .setContactAddress(contactAddress)
            .setLocations(locations)
            .setContactEmail("alex_301@domain.com")
            .setExpirationTime(1549063157000L)
            .setImages(images)
            .setSubject("My new apartment!");

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdatePostFieldSet()
            .setApiKey("YOUR_API_KEY_HERE")
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setPost(p));

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
}
