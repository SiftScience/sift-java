package com.siftscience;

public class TestUtils {
    static String unescapeJson(String json) {
        return json.replaceAll("\\\"", "\\\\\"").replaceAll("\n", "");
    }
}
