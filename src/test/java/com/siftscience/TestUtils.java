package com.siftscience;

import com.siftscience.model.Address;
import com.siftscience.model.Booking;
import com.siftscience.model.CreditPoint;
import com.siftscience.model.Discount;
import com.siftscience.model.Guest;
import com.siftscience.model.Item;
import com.siftscience.model.PaymentMethod;
import com.siftscience.model.Promotion;
import com.siftscience.model.Segment;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    static String unescapeJson(String json) {
        return json.replaceAll("\"", "\\\\\"").replaceAll("\n", "");
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

    static List<String> sampleTags3() {
        List<String> tags = new ArrayList<>();
        tags.add("team-123");
        tags.add("region-123");
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

    static Booking sampleBooking() {
        List<Guest> guests = new ArrayList<>();
        guests.add(sampleGuest1());
        guests.add(sampleGuest2());

        List<Segment> segments = new ArrayList<>();
        segments.add(sampleSegment());

        return new Booking()
            .setBookingType("$flight")
            .setTitle("SFO - LAS, 2 Adults")
            .setStartTime(12038412903L)
            .setEndTime(12048412903L)
            .setGuests(guests)
            .setSegments(segments)
            .setPrice(49900000L)
            .setCurrencyCode("USD")
            .setTags(sampleTags3())
            .setQuantity(1L);
    }

    static Guest sampleGuest1() {
        return new Guest()
                .setName("John Doe")
                .setBirthDate("1985-01-19")
                .setLoyaltyProgram("skymiles")
                .setLoyaltyProgramId("PSOV34DF")
                .setPhone("1-415-555-6040")
                .setEmail("jdoe@domain.com");
    }

    static Guest sampleGuest2() {
        return new Guest()
            .setName("Jane Doe");
    }

    static Segment sampleSegment() {
        return new Segment()
            .setDepartureAddress(sampleAddress1())
            .setArrivalAddress(sampleAddress2())
            .setStartTime(2190121220L)
            .setEndTime(2290122129L)
            .setVesselNumber("LH454")
            .setFareClass("Premium Economy")
            .setDepartureAirportCode("SFO")
            .setArrivalAirportCode("LAS");
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
