package com.siftscience;

import static org.junit.Assert.assertEquals;

import com.siftscience.exception.SiftException;
import com.siftscience.model.BotIdentification;
import com.siftscience.model.CreateAccountFieldSet;
import com.siftscience.model.CreateOrderFieldSet;
import com.siftscience.model.Geo;
import com.siftscience.model.Kyc;
import com.siftscience.model.LoginFieldSet;
import com.siftscience.model.TransactionFieldSet;
import com.siftscience.model.UpdateAccountFieldSet;
import com.siftscience.model.UpdateOrderFieldSet;
import com.siftscience.model.VerificationFieldSet;
import org.junit.Test;

/**
 * NEB-4783 QA self-serve check, section D (client libraries).
 *
 * IMPORTANT: none of the 6 client library PRs (Java, .NET, Python, PHP, Ruby) are merged
 * or released yet. This only works against a local checkout of this branch - installing
 * the published sift-java artifact from Maven will NOT have these fields.
 *
 * How to run: set SIFT_QA_API_KEY to the shared QA prod key (same one used for the
 * Postman collection in section A - find it in Console under the name
 * "neb-4783-structured-fields-qa"), then either:
 *   - run all 7:  SIFT_QA_API_KEY=... ./gradlew test --tests StructuredFieldsSelfServeCheck
 *   - or open this file in IntelliJ (or any IDE) and run individual @Test methods, which
 *     also lets you use autocomplete to browse what's available - see the IDE exploration
 *     note below.
 *
 * What this proves:
 *   1. Seven test methods below, one per event type in the attachment matrix, each
 *      reusing the exact payload shapes already verified in section A's Postman
 *      collection - confirming the Java client sends the new fields correctly end to
 *      end, not just that it compiles.
 *   2. IDE exploration: open this file in IntelliJ, place your cursor after
 *      "new CreateAccountFieldSet()" on a new line, type ".", and look at the
 *      autocomplete list - you'll see setKyc/setGeo/setBotIdentification/setNationality/
 *      setYearOfBirth. Do the same after "new LoginFieldSet()" - setKyc is simply not in
 *      the list, because the method does not exist. That's the concrete "why Java
 *      specifically" story: a caller can't even attempt the mistake here, unlike the
 *      other 5 client libraries which do no validation and would accept anything.
 *   3. tryToAttachKycToLogin() below - commented out on purpose. Uncomment it and this
 *      file will FAIL TO COMPILE with "cannot find symbol: method setKyc(Kyc)" -
 *      the same point as #2, proven by the compiler instead of just the IDE.
 */
public class StructuredFieldsSelfServeCheck {

    private static final String USER_ID = "qa_structured_fields_2026";
    private static final String ORDER_ID = "qa_order_001";

    private static SiftClient client() {
        String apiKey = System.getenv("SIFT_QA_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException(
                    "Set SIFT_QA_API_KEY first - see the comment at the top of this file.");
        }
        return new SiftClient(apiKey, USER_ID);
    }

    private static void sendAndVerify(EventRequest request) throws Exception {
        System.out.println("REQUEST_JSON: " + request.getFieldSet().toJson());
        try {
            EventResponse response = request.send();
            System.out.println("HTTP_STATUS: " + response.getHttpStatusCode());
            System.out.println("API_STATUS: " + response.getApiStatus());
            System.out.println("API_ERROR_MESSAGE: " + response.getApiErrorMessage());
            assertEquals(200, response.getHttpStatusCode());
            assertEquals(0, response.getApiStatus().intValue());
        } catch (SiftException e) {
            System.out.println("SIFT_EXCEPTION: " + e.getMessage());
            System.out.println("SIFT_RESPONSE: " + e.getSiftResponse().getBody().toJson());
            throw e;
        }
    }

    @Test
    public void createAccount_allFiveFields() throws Exception {
        sendAndVerify(client().buildRequest(new CreateAccountFieldSet()
                .setUserId(USER_ID)
                .setNationality("US")
                .setYearOfBirth(1985)
                .setKyc(new Kyc().setNamesMatch(true).setKycLevel("$basic")
                        .setBinNationalityMatch(true).setProvider("lexisnexis"))
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human").setProvider("datadome"))));
    }

    @Test
    public void updateAccount_allFiveFields() throws Exception {
        sendAndVerify(client().buildRequest(new UpdateAccountFieldSet()
                .setUserId(USER_ID)
                .setNationality("US")
                .setYearOfBirth(1985)
                .setKyc(new Kyc().setNamesMatch(true).setKycLevel("$full")
                        .setBinNationalityMatch(true).setProvider("lexisnexis"))
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human").setProvider("datadome"))));
    }

    @Test
    public void login_geoAndBotIdentificationOnly() throws Exception {
        // No $kyc here on purpose - see the class-level comment and
        // tryToAttachKycToLogin() below for why that's not just a choice in this test,
        // it's not possible at all.
        sendAndVerify(client().buildRequest(new LoginFieldSet()
                .setUserId(USER_ID)
                .setLoginStatus("$success")
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human").setProvider("datadome"))));
    }

    @Test
    public void transaction_kycGeoAndBotIdentification() throws Exception {
        sendAndVerify(client().buildRequest(new TransactionFieldSet()
                .setUserId(USER_ID)
                .setAmount(15230000L)
                .setCurrencyCode("USD")
                .setKyc(new Kyc().setNamesMatch(true).setKycLevel("$full")
                        .setBinNationalityMatch(false).setProvider("prove"))
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human").setProvider("human_security"))));
    }

    @Test
    public void createOrder_kycGeoAndBotIdentification() throws Exception {
        sendAndVerify(client().buildRequest(new CreateOrderFieldSet()
                .setUserId(USER_ID)
                .setOrderId(ORDER_ID)
                .setKyc(new Kyc().setNamesMatch(true).setKycLevel("$basic")
                        .setBinNationalityMatch(true).setProvider("lexisnexis"))
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human").setProvider("datadome"))));
    }

    @Test
    public void updateOrder_kycGeoAndBotIdentification() throws Exception {
        sendAndVerify(client().buildRequest(new UpdateOrderFieldSet()
                .setUserId(USER_ID)
                .setOrderId(ORDER_ID)
                .setKyc(new Kyc().setNamesMatch(true).setKycLevel("$basic")
                        .setBinNationalityMatch(true).setProvider("lexisnexis"))
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human").setProvider("datadome"))));
    }

    @Test
    public void verification_kycOnly() throws Exception {
        // No $geo/$bot_identification here on purpose - $verification only supports
        // $kyc per the attachment matrix.
        sendAndVerify(client().buildRequest(new VerificationFieldSet()
                .setUserId(USER_ID)
                .setVerificationType("$kyc")
                .setStatus("$success")
                .setKyc(new Kyc().setNamesMatch(true).setKycLevel("$basic")
                        .setBinNationalityMatch(false).setProvider("lexisnexis"))));
    }

    // Uncomment this method to see the compile-time safety point for yourself.
    // LoginFieldSet has no setKyc(...) method, so this will not build.
    //
    // public void tryToAttachKycToLogin() {
    //     new LoginFieldSet().setKyc(new Kyc().setNamesMatch(true));
    // }
}
