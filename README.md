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
    <version>1.1.1</version>
</dependency>
```
### Gradle
```
dependencies {
    compile 'com.siftscience:sift-java:1.1.1'
}
```
### Other
Download and install the latest Jar from the [releases page](https://github.com/SiftScience/sift-java/releases). This zip file contains the Jar files of the libraries that sift-java depends on (Gson, OkHttp, and Okio), so those will have to be installed also.

You may also generate the distribution Zip from source.
```
$ git clone git@github.com:SiftScience/sift-java.git
$ cd sift-java
$ ./gradlew distZip   # Zip file saved to ./build/distributions/
```

## How To Use

Create a SiftClient object with your API key. SiftClient is thread safe
and can be used to access all supported APIs.
```java
SiftClient client = new SiftClient("your_api_key");
```

### Send Events

[API Docs](https://siftscience.com/developers/docs/java/events-api)

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
    // ... handle InvalidRequestException(4xx) and/or ServerException(5xx) subtypes.
}

// Inspect the response.
response.isOk();                // true
response.getApiErrorMessage();  // "OK"
response.getApiStatus();        // 0
response.getHttpStatusCode();   // 200
EventResponseBody body = response.getBody();
```

### Get Scores
#### Synchronous Scoring

[API Docs](https://siftscience.com/developers/docs/java/score-api/synchronous-scores)

To get a score in the response body of an event request, build your event
request as usual and then augment the request with a list of abuse types
for which you would like scores returned using `EventRequest#withScores`.

Here's how the `$create_order` example above can be altered to respond
with `payment_abuse` and `promotion_abuse` scores.

```java
// Add a list of requested abuse types as an extra parameter.
// The first parameter is the same as in the first example.
EventRequest createOrderRequest = client.buildRequest(createOrderFieldSet)
        .withScores("payment_abuse", "promotion_abuse");

// Send the request. May throw a SiftException.
EventResponse response = createOrderRequest.send();

// Inspect scores.
AbuseScore paymentAbuseScore = response.getAbuseScore("payment_abuse");
```

You may also invoke the `withScores` method with no arguments to return
scores for all abuse types.

#### Score API

[API Docs](https://siftscience.com/developers/docs/java/score-api/score-api)

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
AbuseScore paymentAbuseScore = response.getAbuseScore("payment_abuse");
```

### Labels
#### Label User

[API Docs](https://siftscience.com/developers/docs/java/labels-api)

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

#### Unlabel User

[API Docs](https://siftscience.com/developers/docs/java/labels-api/unlabel-user)

Similarly, use an `UnlabelFieldSet` to unlabel a user.
```java
UnlabelRequest request = client.buildRequest(new UnlabelFieldSet()
        .setUserId("billy_jones_301")
        
        // Optional abuse type to unlabel for. Omit to unlabel for all abuse types.
        .setAbuseType("payment_abuse"));
```

### Workflow Status
#### Synchronous Workflow Statuses

[API Docs](https://siftscience.com/developers/docs/java/workflows-api/workflow-decisions)

Similarly to the Scores API, EventRequest objects can also be modified to
return a Workflow Status using the `EventRequest#withWorkflowStatus` method.
```java
EventRequest createOrderRequest = client.buildRequest(createOrderFieldSet)
        .withWorkflowStatus();
```

#### Workflow Status API

[API Docs](https://siftscience.com/developers/docs/java/workflows-api/workflow-status)

To query the Workflow Status API, create a request with a WorkflowStatusFieldSet.
```java
WorkflowStatusRequest request = client.buildRequest(new WorkflowStatusFieldSet()
        .setAccountId("your_account_id")
        .setWorkflowRunId("someid"));
```

#### Get decisions

[API Docs](https://siftscience.com/developers/docs/java/decisions-api/get-decisions)

To retrieve available decisions, create a request with GetDecisionsFieldSet.
```java
GetDecisions request = client.buildRequest(new GetDecisionsFieldSet()
        .setAbuseTypes(ImmutableList.of(AbuseType.PAYMENT_ABUSE, AbuseType.CONTENT_ABUSE))
        .setAccountId("your_account_id"));
```
Additionally, this request supports filtering on results by creation time, applicable entity type, and abuse type(s). 
```java
GetDecisions request = client.buildRequest(new GetDecisionsFieldSet()
        .setAccountId("your_account_id"))
        .setEntityType(EntityType.ORDER)
        .setCreatedBefore(Instant.now().minus(7, ChronoUnit.WEEKS).getEpochSecond())
        .setAbuseTypes(ImmutableList.of(AbuseType.PAYMENT_ABUSE, AbuseType.CONTENT_ABUSE));
```

### Decision Status API

[API Docs](https://siftscience.com/developers/docs/java/decisions-api/decision-status)

To query the Decision Status API, create a request with a DecisionStatusFieldSet.
```java
DecisionStatusRequest request = client.buildRequest(new DecisionStatusFieldSet()
        .setAccountId("your_account_id")
        .setEntity(DecisionStatusFieldSet.ENTITY_ORDERS) // or ENTITY_USERS
        .setEntityId("someid"));
```
