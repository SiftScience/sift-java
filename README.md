# Sift Science Java API

The official Java bindings for the latest version (v204) of the [Sift Science API](https://siftscience.com/developers/docs/curl/apis-overview) .

## Requirements
Java 1.7 or later.

## Setup
### Maven
TODO: Write docs for Maven setup
### Gradle
TODO: Write docs for Gradle setup

## How To Use

Create a SiftClient object with your API key. It can be used to access
all supported APIs.
```java
SiftClient client = new SiftClient("your_api_key");
```

### Send Events

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
        // https://siftscience.com/developers/docs/curl/events-api/reserved-events/create-order
        
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
#### Synchronous Scoring
To get a score in the response body of an event request, build your
request with `client.buildRequest(eventFieldSet, abuseTypes)`. The extra
parameter `abuseTypes` is a `List` of Sift Science abuse types for which
the API will provide user scores.

Here's how the `$create_order` example above can be altered to respond
with `payment_abuse` and `promotion_abuse` scores.

```java
// Build the list of requested abuse types.
List<String> abuseTypes = new ArrayList<>();
abuseTypes.add("payment_abuse");
abuseTypes.add("promotion_abuse");

// Add it as an extra parameter. The first parameter is the same as in the first example.
EventRequest createOrderRequest = client.buildRequest(createOrderFieldSet, abuseTypes);

// Send the request.
...

// Inspect scores.
Score paymentAbuseScore = response.getScore("payment_abuse");
Label label = response.getLatestLabel("payment_abuse");
```
