package com.thoughtworks.startup.util;

import org.junit.Test;

import static com.thoughtworks.startup.util.RegexUtil.isThoughtWorkEmail;
import static org.assertj.core.api.Assertions.assertThat;


public class RegexUtilTest {

    @Test
    public void testMatcherThoughtWorksEmail() throws Exception {
        String email = "test@tw.com";

        boolean matcher = isThoughtWorkEmail(email);

        assertThat(matcher).isTrue();
    }

    @Test
    public void testNotMatcherNonThoughtWorksEmail() throws Exception {
        String[] emails = new String[] {"test@google.com", "test @gmail.com", "test_wrong_email"};

        for (String email : emails) {
            boolean matcher = isThoughtWorkEmail(email);
            assertThat(matcher).isFalse();
        }
    }
}