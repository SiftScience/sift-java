package com.siftscience;

import com.siftscience.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    static String unescapeJson(String json) {
        return json.replaceAll("\\\"", "\\\\\"").replaceAll("\n", "");
    }

    static Address sampleAddress1() {
        return new Address()
                .setName("Bill Jones")
                .setPhone("1-415-555-6040")
                .setAddress1("2100 Main Street")
                .setAddress2("Apt 3B")
                .setCity("New London")
                .setRegion("New Hampshire")
                .setCountry("US")
                .setZipCode("03257");
    }

    static Address sampleAddress2() {
        return new Address()
                .setName("Bill Jones")
                .setPhone("1-415-555-6041")
                .setAddress1("2100 Main Street")
                .setAddress2("Apt 3B")
                .setCity("New London")
                .setRegion("New Hampshire")
                .setCountry("US")
                .setZipCode("03257");
    }

    static Address sampleAddress3() {
        return new Address()
                .setCity("Seattle")
                .setRegion("Washington")
                .setCountry("US")
                .setZipCode("98112");
    }

    static PaymentMethod samplePaymentMethod1() {
        return new PaymentMethod()
                .setPaymentType("$credit_card")
                .setPaymentGateway("$braintree")
                .setCardBin("542486")
                .setCardLast4("4444");
    }

    static PaymentMethod samplePaymentMethod2() {
        return new PaymentMethod()
                .setPaymentType("$credit_card")
                .setCardBin("542486")
                .setCardLast4("4444");
    }

    static List<String> sampleTags1() {
        List<String> tags = new ArrayList<>();
        tags.add("Popcorn");
        tags.add("Snacks");
        tags.add("On Sale");
        return tags;
    }

    static Item sampleItem1() {
        return new Item()
                .setItemId("12344321")
                .setProductTitle("Microwavable Kettle Corn: Original Flavor")
                .setPrice(4990000L)
                .setUpc("097564307560")
                .setSku("03586005")
                .setBrand("Peters Kettle Corn")
                .setManufacturer("Peters Kettle Corn")
                .setCategory("Food and Grocery")
                .setTags(sampleTags1())
                .setQuantity(4L);
    }


    static List<String> sampleTags2() {
        List<String> tags = new ArrayList<>();
        tags.add("Awesome");
        tags.add("Wintertime specials");
        return tags;
    }

    static Item sampleItem2() {
        return new Item()
                .setItemId("B004834GQO")
                .setProductTitle("The Slanket Blanket-Texas Tea")
                .setPrice(39990000L)
                .setUpc("67862114510011")
                .setSku("004834GQ")
                .setBrand("Slanket")
                .setManufacturer("Slanket")
                .setCategory("Blankets & Throws")
                .setTags(sampleTags2())
                .setColor("Texas Tea")
                .setQuantity(2L);
    }

    static Discount sampleDiscount1() {
        return new Discount()
                .setAmount(5000000L)
                .setCurrencyCode("USD")
                .setMinimumPurchaseAmount(25000000L);
    }

    static Discount sampleDiscount2() {
        return new Discount()
                .setAmount(5000000L)
                .setCurrencyCode("USD");
    }

    static CreditPoint sampleCreditPoint() {
        return new CreditPoint()
                .setAmount(100L)
                .setCreditPointType("account karma");
    }

    static Promotion samplePromotion1() {
        return new Promotion()
                .setPromotionId("FirstTimeBuyer")
                .setStatus("$success")
                .setDescription("$5 off")
                .setDiscount(sampleDiscount1());
    }

    static Promotion samplePromotion2() {
        return new Promotion()
                .setPromotionId("FriendReferral")
                .setStatus("$success")
                .setReferrerUserId("janejane102")
                .setCreditPoint(TestUtils.sampleCreditPoint());
    }

    static Promotion samplePromotion3() {
        return new Promotion()
                .setPromotionId("NewRideDiscountMay2016")
                .setStatus("$success")
                .setDescription("$5 off your first 5 rides")
                .setReferrerUserId("elon-m93903")
                .setDiscount(sampleDiscount2());
    }

    static List<String> sampleCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Housing");
        categories.add("Apartments");
        categories.add("2 Bedrooms");
        return categories;
    }
}
