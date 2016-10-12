# Sift Science Java API

The official Java bindings for the latest version (v204) of the [Sift Science API](https://siftscience.com/developers/docs/java/apis-overview) .

## Requirements
Java 1.7 or later.

## Setup
### Maven
```xml
<dependency>
    <groupId>com.siftscience</groupId>
    <artifactId>sift-java</artifactId>
    <version>1.0</version>
</dependency>
```
### Gradle
```
dependencies {
    compile 'com.siftscience:sift-java:1.0'
}
```

## How To Use

Create a SiftClient object with your API key. It can be used to access
all supported APIs.
```java
SiftClient client = new SiftClient("your_api_key");
```

### [Send Events](https://siftscience.com/developers/docs/java/events-api)

All request types can be built using the overloaded `client.buildRequest`.
Here's an example for the `$create_order` event type.
```java
// Build the request object.
EventRequest createOrderRequest = client.buildRequest(new CreateOrderFieldSet()

        // Required fields ($api_key and $type) are automatically filled in by the library,
        // although $api_key can be overridden on a per-request basis.
        .setUserId("bill_jones") 
        
        // Supported fields.
        .setOrderId("ORDER-28168441")
        .setUserEmail("bjones@altavista.com")
        .setCurrencyCode("USD")
        .setAmount(115940000L) // $115.94
        .setBillingAddress(new Address()
                .setName("Bill Jones")
                .setPhone("1-415-555-6041")
                .setAddress1("2100 Main Street")
                .setAddress2("Apt 3B")
                .setCity("New London")
                .setRegion("New Hampshire")
                .setCountry("US")
                .setZipCode("03257"))
        .setExpeditedShipping(true)
        // More supported fields documented at:
        // https://siftscience.com/developers/docs/java/events-api/reserved-events/create-order
        
        // Custom fields.
        .setCustomField("digital_wallet", "apple_pay")
        .setCustomField("coupon_code", "dollarMadness")
        .setCustomField("shipping_choice", "FedEx Ground Courier")
        .setCustomField("is_first_time_buyer", false)
);

// Send the request.
EventResponse response;
try {
    response = createOrderRequest.send();
} catch (SiftException e) {
    SiftResponse errorResponse = e.getSiftResponse();
    // ... handle InvalidRequestException and/or ServerException subtypes.
}

// Inspect the response.
response.isSuccessful(); // true
response.getErrorMessage(); // "OK"
response.getSiftStatusCode(); // 0
response.getHttpStatusCode(); // 200
EventResponseBody responseBody = response.getResponseBody();
```

### Get Scores
#### [Synchronous Scoring](https://siftscience.com/developers/docs/java/score-api/synchronous-scores)
To get a score in the response body of an event request, build your
request with `client.buildRequest(eventFieldSet, abuseTypes)`. The extra
parameter `abuseTypes` is a `List` of Sift Science abuse types for which
the API will provide user scores.

Here's how the `$create_order` example above can be altered to respond
with `payment_abuse` and `promotion_abuse` scores.

```java
// Add a list of requested abuse types as an extra parameter.
// The first parameter is the same as in the first example.
EventRequest createOrderRequest = client.buildRequest(
        createOrderFieldSet,
        Arrays.asList("payment_abuse", "promotion_abuse")
);

// Send the request. May throw a SiftException.
EventResponse response = createOrderRequest.send();

// Inspect scores.
Score paymentAbuseScore = response.getScore("payment_abuse");
Label latestPaymentAbuseLabel = response.getLatestLabel("payment_abuse");
```

#### [Score API](https://siftscience.com/developers/docs/java/score-api/score-api)
Scores may also be requested separately from incoming event requests.
Provide a `ScoreFieldSet` containing a list of abuse types `client.buildRequest`
to send a request to the Scores API.

```java
// Build the ScoreRequest with a list of abuse types.
ScoreRequest request = client.buildRequest(new ScoreFieldSet()
        .setUserId("bill_jones")
        .setAbuseTypes(Arrays.asList("payment_abuse", "promotion_abuse"))
);

// Send the request. May throw a SiftException.
ScoreResponse response = request.send();

// Inspect scores.
Score paymentAbuseScore = response.getScore("payment_abuse");
Label latestPaymentAbuseLabel = response.getLatestLabel("payment_abuse");
```

### Labels
#### [Label User](https://siftscience.com/developers/docs/java/labels-api)
To send a label, build a request using a `LabelFieldSet`;
```java
LabelRequest request = client.buildRequest(new LabelFieldSet()
        .setUserId("bill_jones")
        .setIsBad(true)
        .setAbuseType("payment_abuse")
        .setDescription("The user was testing cards repeatedly for a valid card")
        .setSource("manual review")
        .setAnalyst("someone@your-site.com")
);
```

#### [Unlabel User](https://siftscience.com/developers/docs/java/labels-api/unlabel-user)
Similarly, use an `UnlabelFieldSet` to unlabel a user.
```java
UnlabelRequest request = client.buildRequest(new UnlabelFieldSet()
        .setUserId("billy_jones_301")
        
        // Optional abuse type to unlabel for. Omit to unlabel for all abuse types.
        .setAbuseType("payment_abuse"));
```
