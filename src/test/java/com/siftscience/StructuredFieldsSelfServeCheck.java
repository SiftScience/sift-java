package com.siftscience;

import static org.junit.Assert.assertEquals;

import com.siftscience.exception.SiftException;
import com.siftscience.model.BotIdentification;
import com.siftscience.model.Geo;
import com.siftscience.model.Kyc;
import com.siftscience.model.LoginFieldSet;
import com.siftscience.model.UpdateAccountFieldSet;
import org.junit.Test;

/**
 * NEB-4783 QA self-serve check, section D (client libraries).
 *
 * How to run: set SIFT_QA_API_KEY to the shared QA prod key (same one used for the
 * Postman collection in section A - find it in Console under the name
 * "neb-4783-structured-fields-qa"), then:
 *
 *   SIFT_QA_API_KEY=... ./gradlew test --tests StructuredFieldsSelfServeCheck
 *
 * What this proves:
 *   1. hitLiveApiWithAllFiveFields() - a real round trip against prod, reusing the same
 *      test data as section A's Postman request 02, confirming the Java client actually
 *      sends the new fields correctly end to end (not just that it compiles).
 *   2. tryToAttachKycToLogin() below - commented out on purpose. Uncomment it and this
 *      file will FAIL TO COMPILE. That's the point: LoginFieldSet has no setKyc() method
 *      at all, so a caller can't even attempt the mistake, unlike the other 5 client
 *      libraries which do no validation and would accept it silently.
 */
public class StructuredFieldsSelfServeCheck {

    @Test
    public void hitLiveApiWithAllFiveFields() throws Exception {
        String apiKey = System.getenv("SIFT_QA_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException(
                    "Set SIFT_QA_API_KEY first - see the comment at the top of this file.");
        }

        SiftClient client = new SiftClient(apiKey, "qa_structured_fields_2026");

        EventRequest request = client.buildRequest(new UpdateAccountFieldSet()
                .setUserId("qa_structured_fields_2026")
                .setNationality("US")
                .setYearOfBirth(1985)
                .setKyc(new Kyc()
                        .setNamesMatch(true)
                        .setKycLevel("$full")
                        .setBinNationalityMatch(true)
                        .setProvider("lexisnexis"))
                .setGeo(new Geo().setUuid("gc-abc-123").setProvider("geocomply"))
                .setBotIdentification(new BotIdentification()
                        .setResult("$human")
                        .setProvider("datadome")));

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

    // Uncomment this method to see the compile-time safety point for yourself.
    // LoginFieldSet has no setKyc(...) method, so this will not build.
    //
    // public void tryToAttachKycToLogin() {
    //     new LoginFieldSet().setKyc(new Kyc().setNamesMatch(true));
    // }
}
