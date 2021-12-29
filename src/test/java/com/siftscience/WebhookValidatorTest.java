package com.siftscience;

import com.siftscience.utils.WebhookValidator;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.junit.Assert;
import org.junit.Test;

public class WebhookValidatorTest {

    @Test
    public void testWebhookValidation() {

        final String secretKey = "1d708fe409f22591";
        final String requestBody = "{\n" +
                "  \"entity\": {\n" +
                "    \"type\": \"user\",\n" +
                "    \"id\": \"USER123\"\n" +
                "  },\n" +
                "  \"decision\": {\n" +
                "    \"id\": \"block_user_payment_abuse\"\n" +
                "  },\n" +
                "  \"time\": 1461963439151\n" +
                "}";
       final String signature = "sha1=" + new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secretKey).hmacHex(requestBody);

        Assert.assertTrue(WebhookValidator.isValidWebhook(signature, requestBody, secretKey));
    }

    @Test
    public void testWebhookValidationForInvalidSecretKey() {

        final String secretKey = "1d708fe409f22591";
        final String requestBody = "{\n" +
                "  \"entity\": {\n" +
                "    \"type\": \"user\",\n" +
                "    \"id\": \"USER123\"\n" +
                "  },\n" +
                "  \"decision\": {\n" +
                "    \"id\": \"block_user_payment_abuse\"\n" +
                "  },\n" +
                "  \"time\": 1461963439151\n" +
                "}";
        final String signature = "sha1=" + new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secretKey).hmacHex(requestBody);

        Assert.assertFalse(WebhookValidator.isValidWebhook(signature, requestBody, "invalid key"));
    }
}
