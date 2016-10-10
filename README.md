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
EventRequest createOrderRequest = client.buildRequest(new CreateOrderFieldSet()
        // Required fields ($api_key and $type) are automatically filled
        // in by the library, although $api_key can be overridden on a
        // per-request basis.
        .setUserId("bill_jones") 
        
        // Supported fields. See all fields for $create_order at:
        // https://siftscience.com/developers/docs/curl/events-api/reserved-events/create-order
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
        
        // Custom fields.
        .setCustomField("digital_wallet", "apple_pay")
        .setCustomField("coupon_code", "dollarMadness")
        .setCustomField("shipping_choice", "FedEx Ground Courier")
        .setCustomField("is_first_time_buyer", false)
);
```
