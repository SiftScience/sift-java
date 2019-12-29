package com.siftscience;

import static java.net.HttpURLConnection.HTTP_OK;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.siftscience.model.Address;
import com.siftscience.model.Comment;
import com.siftscience.model.CreateCommentFieldSet;
import com.siftscience.model.CreateListingFieldSet;
import com.siftscience.model.CreateMessageFieldSet;
import com.siftscience.model.CreatePostFieldSet;
import com.siftscience.model.CreateProfileFieldSet;
import com.siftscience.model.CreateReviewFieldSet;
import com.siftscience.model.Image;
import com.siftscience.model.Item;
import com.siftscience.model.Listing;
import com.siftscience.model.Message;
import com.siftscience.model.Post;
import com.siftscience.model.Profile;
import com.siftscience.model.Review;
import com.siftscience.model.UpdateCommentFieldSet;
import com.siftscience.model.UpdateListingFieldSet;
import com.siftscience.model.UpdateMessageFieldSet;
import com.siftscience.model.UpdatePostFieldSet;
import com.siftscience.model.UpdateProfileFieldSet;
import com.siftscience.model.UpdateReviewFieldSet;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class ContentEventTest {

    @Test
    public void testCreateComment() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

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
    public void testCreateListing() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$listing\"     : {\n" +
            "       \"$subject\": \"2 Bedroom Apartment for Rent\",\n" +
            "       \"$body\": \"Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$contact_address\": {\n" +
            "           \"$phone\": \"1-415-555-6041\",\n" +
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
            "       \"$listed_items\": [{\n" +
            "           \"$price\": 2950000000,\n" +
            "           \"$currency_code\": \"USD\",\n" +
            "           \"$tags\": [\"heat\", \"washer/dryer\"]\n" +
            "       }],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"Billy's picture\"\n" +
            "       }],\n" +
            "       \"$expiration_time\": 1549063157000,\n" +
            "   }\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("Billy's picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address contactAddress = new Address()
            .setPhone("1-415-555-6041")
            .setCity("New London")
            .setName("Bill Jones")
            .setZipCode("03257")
            .setRegion("New Hampshire")
            .setCountry("US");

        Address locationAddress = new Address()
            .setCity("Seattle")
            .setZipCode("98112")
            .setRegion("Washington")
            .setCountry("US");

        Item item = new Item()
            .setCurrencyCode("USD")
            .setPrice(2950000000L)
            .setTags(Arrays.asList("heat", "washer/dryer"));

        Listing l = new Listing()
            .setBody("Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.")
            .setSubject("2 Bedroom Apartment for Rent")
            .setContactAddress(contactAddress)
            .setContactEmail("alex_301@domain.com")
            .setLocations(Collections.singletonList(locationAddress))
            .setListedItems(Collections.singletonList(item))
            .setImages(images)
            .setExpirationTime(1549063157000L);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new CreateListingFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setListing(l));

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
    public void testCreateMessage() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$message\"     : {\n" +
            "       \"$body\": \"Let's meet at 5pm\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$recipient_user_ids\": [\"fy9h989sjphh71\"],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"My hike today!\"\n" +
            "       }],\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("My hike today!")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Message m = new Message()
            .setBody("Let's meet at 5pm")
            .setContactEmail("alex_301@domain.com")
            .setRecipientUserIds(Collections.singletonList("fy9h989sjphh71"))
            .setImages(images);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new CreateMessageFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setMessage(m));

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
    public void testCreateProfile() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$profile\"     : {\n" +
            "       \"$body\": \"Hi! My name is Alex and I just moved to New London!\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$contact_address\": {\n" +
            "           \"$phone\": \"1-415-555-6041\",\n" +
            "           \"$name\": \"Bill Jones\",\n" +
            "           \"$city\": \"New London\",\n" +
            "           \"$region\": \"New Hampshire\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"03257\"\n" +
            "       },\n" +
            "       \"$categories\": [\"Friends\", \"Long-term dating\"],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"Alex’s picture\"\n" +
            "       }],\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("Alex’s picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address contactAddress = new Address()
            .setPhone("1-415-555-6041")
            .setCity("New London")
            .setName("Bill Jones")
            .setZipCode("03257")
            .setRegion("New Hampshire")
            .setCountry("US");

        Profile p = new Profile()
            .setBody("Hi! My name is Alex and I just moved to New London!")
            .setCategories(Arrays.asList("Friends", "Long-term dating"))
            .setContactAddress(contactAddress)
            .setContactEmail("alex_301@domain.com")
            .setImages(images);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new CreateProfileFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setProfile(p));

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
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

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
    public void testCreateReview() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$create_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$review\"     : {\n" +
            "       \"$subject\": \"Amazing Tacos!\"," +
            "       \"$body\": \"I ate the tacos.\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$locations\": [{\n" +
            "           \"$city\": \"Seattle\",\n" +
            "           \"$region\": \"Washington\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"98112\"\n" +
            "       }],\n" +
            "       \"$reviewed_content_id\": \"listing-234234\",\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"Calamari tacos.\"\n" +
            "       }],\n" +
            "       \"$rating\": 4.5" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("Calamari tacos.")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address locationAddress = new Address()
            .setCity("Seattle")
            .setRegion("Washington")
            .setCountry("US")
            .setZipCode("98112");

        Review r = new Review()
            .setBody("I ate the tacos.")
            .setSubject("Amazing Tacos!")
            .setContactEmail("alex_301@domain.com")
            .setLocations(Collections.singletonList(locationAddress))
            .setReviewedContentId("listing-234234")
            .setImages(images)
            .setRating(4.5);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new CreateReviewFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setReview(r));

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
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

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
    public void testUpdateListing() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$update_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$listing\"     : {\n" +
            "       \"$subject\": \"2 Bedroom Apartment for Rent\",\n" +
            "       \"$body\": \"Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$contact_address\": {\n" +
            "           \"$phone\": \"1-415-555-6041\",\n" +
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
            "       \"$listed_items\": [{\n" +
            "           \"$price\": 2950000000,\n" +
            "           \"$currency_code\": \"USD\",\n" +
            "           \"$tags\": [\"heat\", \"washer/dryer\"]\n" +
            "       }],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"Billy's picture\"\n" +
            "       }],\n" +
            "       \"$expiration_time\": 1549063157000,\n" +
            "   }\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("Billy's picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address contactAddress = new Address()
            .setPhone("1-415-555-6041")
            .setCity("New London")
            .setName("Bill Jones")
            .setZipCode("03257")
            .setRegion("New Hampshire")
            .setCountry("US");

        Address locationAddress = new Address()
            .setCity("Seattle")
            .setZipCode("98112")
            .setRegion("Washington")
            .setCountry("US");

        Item item = new Item()
            .setCurrencyCode("USD")
            .setPrice(2950000000L)
            .setTags(Arrays.asList("heat", "washer/dryer"));

        Listing l = new Listing()
            .setBody("Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.")
            .setSubject("2 Bedroom Apartment for Rent")
            .setContactAddress(contactAddress)
            .setContactEmail("alex_301@domain.com")
            .setLocations(Collections.singletonList(locationAddress))
            .setListedItems(Collections.singletonList(item))
            .setImages(images)
            .setExpirationTime(1549063157000L);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdateListingFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setListing(l));

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
    public void testUpdateMessage() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$update_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$message\"     : {\n" +
            "       \"$body\": \"Let's meet at 5pm\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$recipient_user_ids\": [\"fy9h989sjphh71\"],\n" +
            "       \"$subject\": \"Hello world\",\n" +
            "       \"$root_content_id\": \"posting-123\",\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"My hike today!\"\n" +
            "       }],\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("My hike today!")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Message m = new Message()
            .setBody("Let's meet at 5pm")
            .setContactEmail("alex_301@domain.com")
            .setRecipientUserIds(Collections.singletonList("fy9h989sjphh71"))
            .setSubject("Hello world")
            .setRootContentId("posting-123")
            .setImages(images);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdateMessageFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setMessage(m));

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
    public void testUpdateProfile() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$update_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$profile\"     : {\n" +
            "       \"$body\": \"Hi! My name is Alex and I just moved to New London!\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$contact_address\": {\n" +
            "           \"$phone\": \"1-415-555-6041\",\n" +
            "           \"$name\": \"Bill Jones\",\n" +
            "           \"$city\": \"New London\",\n" +
            "           \"$region\": \"New Hampshire\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"03257\"\n" +
            "       },\n" +
            "       \"$categories\": [\"Friends\", \"Long-term dating\"],\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"Alex’s picture\"\n" +
            "       }],\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("Alex’s picture")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address contactAddress = new Address()
            .setPhone("1-415-555-6041")
            .setCity("New London")
            .setName("Bill Jones")
            .setZipCode("03257")
            .setRegion("New Hampshire")
            .setCountry("US");

        Profile p = new Profile()
            .setBody("Hi! My name is Alex and I just moved to New London!")
            .setCategories(Arrays.asList("Friends", "Long-term dating"))
            .setContactAddress(contactAddress)
            .setContactEmail("alex_301@domain.com")
            .setImages(images);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdateProfileFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setProfile(p));

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
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

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
    public void testUpdateReview() throws Exception {
        String expectedRequestBody = "{\n" +
            "  \"$type\"       : \"$update_content\",\n" +
            "  \"$api_key\"    : \"YOUR_API_KEY\",\n" +
            "  \"$user_id\"    : \"fyw3989sjpqr71\",\n" +
            "  \"$content_id\"       : \"comment-23412\",\n" +
            "\n" +
            "  \"$session_id\"       : \"a234ksjfgn435sfg\",\n" +
            "  \"$status\"           : \"$active\",\n" +
            "  \"$ip\"           : \"255.255.255.0\",\n" +
            "  \"$review\"     : {\n" +
            "       \"$subject\": \"Amazing Tacos!\"," +
            "       \"$body\": \"I ate the tacos.\",\n" +
            "       \"$contact_email\": \"alex_301@domain.com\",\n" +
            "       \"$locations\": [{\n" +
            "           \"$city\": \"Seattle\",\n" +
            "           \"$region\": \"Washington\",\n" +
            "           \"$country\": \"US\",\n" +
            "           \"$zipcode\": \"98112\"\n" +
            "       }],\n" +
            "       \"$reviewed_content_id\": \"listing-234234\",\n" +
            "       \"$images\": [{\n" +
            "           \"$md5_hash\": \"aflshdfbalsubdf3234sfdkjb\",\n" +
            "           \"$link\": \"https://www.domain.com/file.png\",\n" +
            "           \"$description\": \"Calamari tacos.\"\n" +
            "       }],\n" +
            "       \"$rating\": 4.5" +
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

        // Create a new client and link it to the mock server.
        SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID",
            new OkHttpClient.Builder()
                .addInterceptor(OkHttpUtils.urlRewritingInterceptor(server))
                .build());

        Image image = new Image()
            .setDescription("Calamari tacos.")
            .setLink("https://www.domain.com/file.png")
            .setMd5Hash("aflshdfbalsubdf3234sfdkjb");

        List<Image> images = new ArrayList<>();
        images.add(image);

        Address locationAddress = new Address()
            .setCity("Seattle")
            .setRegion("Washington")
            .setCountry("US")
            .setZipCode("98112");

        Review r = new Review()
            .setBody("I ate the tacos.")
            .setSubject("Amazing Tacos!")
            .setContactEmail("alex_301@domain.com")
            .setLocations(Collections.singletonList(locationAddress))
            .setReviewedContentId("listing-234234")
            .setImages(images)
            .setRating(4.5);

        // Build and execute the request against the mock server.
        SiftRequest request = client.buildRequest(new UpdateReviewFieldSet()
            .setUserId("fyw3989sjpqr71")
            .setContentId("comment-23412")
            .setSessionId("a234ksjfgn435sfg")
            .setStatus("$active")
            .setIp("255.255.255.0")
            .setReview(r));

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
