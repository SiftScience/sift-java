package com.siftscience.utils;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

public class WebhookValidator {
    private static final String  SHA1 = "sha1=";
    private static final String  SHA256 = "sha256=";

    /**
     *
     * @param siftScienceSignature value of the 'X-Sift-Science-Signature' signature in the http header of the webhook request.
     * @param requestBody body of the webhook request
     * @param secretKey sift webhook secret key
     * @return true, if the webhook request received is coming from Sift
     */

    public static boolean isValidWebhook(String siftScienceSignature, String requestBody, String secretKey) {
        String verificationSignature_Sha1 = SHA1 + new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secretKey.getBytes()).hmacHex(requestBody);
        String verificationSignature_Sha256 = SHA256 + new HmacUtils(HmacAlgorithms.HMAC_SHA_256, secretKey.getBytes()).hmacHex(requestBody);

        return siftScienceSignature.equals(verificationSignature_Sha1) || siftScienceSignature.equals(verificationSignature_Sha256);
    }
}
