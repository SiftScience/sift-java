package com.siftscience.utils;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

public class WebhookValidator {
    private static final String  SHA1 = "sha1=";

    public static boolean isValidWebhook(String siftScienceSignature, String requestBody, String secretKey) {
        String verificationSignature = SHA1 + new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secretKey).hmacHex(requestBody);
        if(siftScienceSignature.equals(verificationSignature))
            return true;
        else
            return false;
    }
}
