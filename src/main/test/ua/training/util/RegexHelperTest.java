package ua.training.util;

import org.junit.Assert;
import org.junit.Test;

public class RegexHelperTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_params_are_null() {
        RegexHelper.getStrWithRegex(null, null);
    }

    @Test
    public void should_return_empty_string_as_result() {
        Assert.assertEquals("", RegexHelper.getStrWithRegex("[0-9]", "asdf"));
    }

    @Test
    public void should_return_not_empty_string_as_result() {
        Assert.assertNotEquals("", RegexHelper.getStrWithRegex("([0-9])", "12345"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_IndexOutOfBoundsException_when_no_group() {
        Assert.assertEquals("", RegexHelper.getStrWithRegex("[0-9]", "12345"));
    }
}
