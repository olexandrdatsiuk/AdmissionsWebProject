package ua.training.util;

import ua.training.exception.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {
    private RegexHelper() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static String getStrWithRegex(String regex, String str) {
        if (regex == null || str == null) {
            throw new IllegalArgumentException("params contain null value. regex=" + regex + "; str=" + str);
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
