package com.siftscience;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testJoinWithCommaEmpty() {
        assertEquals("", StringUtils.joinWithComma(null));
        assertEquals("", StringUtils.joinWithComma(Collections.emptyList()));
    }

    @Test
    public void testJoinWithComma() {
        assertEquals("foo", StringUtils.joinWithComma(Collections.singleton("foo")));
        assertEquals("foo,bar", StringUtils.joinWithComma(Arrays.asList("foo", "bar")));
        assertEquals("1,2", StringUtils.joinWithComma(Arrays.asList(1, 2)));
    }
}
