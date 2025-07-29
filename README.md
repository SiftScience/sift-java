# sift-java

[![CircleCI](https://circleci.com/gh/SiftScience/sift-java.svg?style=svg)](https://circleci.com/gh/SiftScience/sift-java)

The official Java bindings for the latest version (v205) of the [Sift API](https://sift.com/developers/docs/java/apis-overview).

## Requirements
Java 1.7 or later.

## Setup
### Maven
```xml
<dependency>
    <groupId>com.siftscience</groupId>
    <artifactId>sift-java</artifactId>
    <version>3.21.2</version>
</dependency>
```
### Gradle
```
dependencies {
    compile 'com.siftscience:sift-java:3.21.2'
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

Create a SiftClient object with your API key and account id. SiftClient is thread-safe
and can be used to access all supported APIs.
```java
SiftClient client = new SiftClient("YOUR_API_KEY", "YOUR_ACCOUNT_ID");
```

### Send Events

[API Docs](https://sift.com/developers/docs/java/events-api)

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
        // https://sift.com/developers/docs/java/events-api/reserved-events/create-order

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

[API Docs](https://sift.com/developers/docs/java/score-api/synchronous-scores)

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

EventRequest objects can also be modified to
return a Score Percentiles using the `EventRequest#withScorePercentiles` method.

```java
// Build the ScoreRequest with score percentiles
EventRequest createOrderRequest = client.buildRequest(createOrderFieldSet)
        .withScores("payment_abuse")
        .withScorePercentiles();

// Send the request. May throw a SiftException.
ScoreResponse response = request.send();

// Inspect score percentiles.
Map<String, Double> percentiles = siftResponse.getAbuseScore("payment_abuse").getPercentiles();
```


#### Score API

[API Docs](https://sift.com/developers/docs/java/score-api/score-api)

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

[API Docs](https://sift.com/developers/docs/java/labels-api)

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

[API Docs](https://sift.com/developers/docs/java/labels-api/unlabel-user)

Similarly, use an `UnlabelFieldSet` to unlabel a user.
```java
UnlabelRequest request = client.buildRequest(new UnlabelFieldSet()
        .setUserId("billy_jones_301")

        // Optional abuse type to unlabel for. Omit to unlabel for all abuse types.
        .setAbuseType("payment_abuse"));
```

### Workflow Status
#### Synchronous Workflow Statuses

[API Docs](https://sift.com/developers/docs/java/workflows-api/workflow-decisions)

Similarly to the Scores API, EventRequest objects can also be modified to
return a Workflow Status using the `EventRequest#withWorkflowStatus` method.
```java
EventRequest createOrderRequest = client.buildRequest(createOrderFieldSet)
        .withWorkflowStatus();
```

#### Workflow Status API

[API Docs](https://sift.com/developers/docs/java/workflows-api/workflow-status)

To query the Workflow Status API, create a request with a WorkflowStatusFieldSet.
```java
WorkflowStatusRequest request = client.buildRequest(new WorkflowStatusFieldSet()
        .setWorkflowRunId("someid"));
```

### Apply Decision API

[API Docs](https://sift.com/developers/docs/java/decisions-api/apply-decision)

To apply a decision to a user, create a request with userId and ApplyDecisionFieldSet.
```java
ApplyDecisionRequest request = client.buildRequest(
        new ApplyDecisionFieldSet()
            .setUserId("a_user_id")
            .setDecisionId("decision_id")
            .setSource(DecisionSource.AUTOMATED_RULE)
            .setDescription("description of decision applied")
);
```

To apply a decision to an order, create a request with userId, orderId, and ApplyDecisionFieldSet.
```java
ApplyDecisionRequest request = client.buildRequest(
        new ApplyDecisionFieldSet()
            .setUserId("a_user_id")
            .setOrderId("a_order_id")
            .setDecisionId("decision_id")
            .setSource(DecisionSource.MANUAL_REVIEW)
            .setAnalyst("analyst@example.com")      
            .setDescription("description of decision applied")
);
```

To apply a decision to a session, create a request with userId, sessionId, and ApplyDecisionFieldSet.
```java
ApplyDecisionRequest request = client.buildRequest(
        new ApplyDecisionFieldSet()
            .setUserId("a_user_id")
            .setSessionId("a_session_id")
            .setDecisionId("decision_id")
            .setSource(DecisionSource.MANUAL_REVIEW)
            .setAnalyst("analyst@example.com")
            .setDescription("description of decision applied")
);
```

To apply a decision to a piece of content, create a request with userId, contentId, and ApplyDecisionFieldSet.
```java
ApplyDecisionRequest request = client.buildRequest(
        new ApplyDecisionFieldSet()
            .setUserId("a_user_id")
            .setContentId("a_content_id")
            .setDecisionId("decision_id")
            .setSource(DecisionSource.MANUAL_REVIEW)
            .setAnalyst("analyst@example.com")
            .setDescription("description of decision applied")
);
```


#### Get decisions

[API Docs](https://sift.com/developers/docs/java/decisions-api/get-decisions)

To retrieve available decisions, build a request with a GetDecisionsFieldSet.
```java
GetDecisionsRequest request = client.buildRequest(new GetDecisionsFieldSet()
        .setAbuseTypes(ImmutableList.of(AbuseType.PAYMENT_ABUSE, AbuseType.CONTENT_ABUSE)));
```

Additionally, this field set supports filtering on results by entity and abuse type(s).
```java
GetDecisionsRequest request = client.buildRequest(new GetDecisionsFieldSet())
        .setEntityType(EntityType.ORDER)
        .setAbuseTypes(ImmutableList.of(AbuseType.PAYMENT_ABUSE, AbuseType.CONTENT_ABUSE))
```

Pagination is also supported, with offset by index (`from`) and limit (`limit`).
The default `limit` is to return up to 100 results.
The default offset value `from` is 0.
```java
GetDecisionsRequest request = client.buildRequest(new GetDecisionsFieldSet())
        .setFrom(15)
        .setLimit(10);  
```

A request will return a paginated response if the number of query results are greater than the set `limit`. In these
cases, the response object will contain a url (`nextRef`) at which the next set of results may be retrieved. Build a
new request from this `nextRef`, as follows:
```java
GetDecisionsResponse response = request.send();
String nextRef = response.getBody().getNextRef();
GetDecisionsRequest nextRequest = client.buildRequest(GetDecisionsFieldSet.fromNextRef(nextRef));

```

### Decision Status API

[API Docs](https://sift.com/developers/docs/java/decisions-api/decision-status)

To query the Decision Status API, create a request with a DecisionStatusFieldSet.
```java
DecisionStatusRequest request = client.buildRequest(new DecisionStatusFieldSet()
        .setEntity(DecisionStatusFieldSet.ENTITY_ORDERS) // or ENTITY_USERS
        .setEntityId("someid"));
```

To query the Decision Status API for a Session, create a request with a DecisionStatusFieldSet, including a user id.
```java
DecisionStatusRequest request = client.buildRequest(new DecisionStatusFieldSet()
        .setEntity(DecisionStatusFieldSet.ENTITY_SESSIONS)
        .setUserId("a_user_id")
        .setEntityId("a_session_id"));
```

To query the Decision Status API for Content, create a request with a DecisionStatusFieldSet, including a user id.
```java
DecisionStatusRequest request = client.buildRequest(new DecisionStatusFieldSet()
        .setEntity(DecisionStatusFieldSet.ENTITY_CONTENT)
        .setUserId("a_user_id")
        .setEntityId("a_content_id"));
```
