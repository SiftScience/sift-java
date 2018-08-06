package com.siftscience;

import java.util.Collection;

final class StringUtils {

    private StringUtils() {

    }

    static String joinWithComma(Collection<?> elements) {
        if (elements == null || elements.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object o: elements) {
            sb.append(',');
            sb.append(o.toString());
        }
        return sb.substring(1, sb.length());
    }
}
