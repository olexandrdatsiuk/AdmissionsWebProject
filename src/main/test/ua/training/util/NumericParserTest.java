package ua.training.util;

import org.junit.Assert;
import org.junit.Test;

public class NumericParserTest {
    @Test
    public void parse_int_should_return_zero_when_param_is_null() {
        Assert.assertEquals(0, NumericParser.parseInt(null));
    }

    @Test
    public void parse_int_should_return_zero_when_param_is_not_valid() {
        Assert.assertEquals(0, NumericParser.parseInt("str"));
    }

    @Test
    public void parse_int_should_return_parsed_number_when_param_is_valid() {
        Assert.assertEquals(123, NumericParser.parseInt("123"));
    }

    @Test
    public void parse_float_should_return_parsed_number_when_param_is_valid() {
        Assert.assertEquals(1.2f, NumericParser.parseFloat("1.2"), 0);
    }

    @Test
    public void parse_float_should_return_zero_when_param_is_not_valid() {
        Assert.assertEquals(0, NumericParser.parseFloat("1.2str"), 0);
    }
}
