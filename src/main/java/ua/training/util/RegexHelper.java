package ua.training.util;

import ua.training.exception.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a RegexHelper.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class RegexHelper {
    private RegexHelper() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    /**
     * Finds value matching with regular expression.
     *
     * @param regex A String containing regular expression.
     * @param str   A String for matching.
     * @return A String representing single matched value by group 1 or empty String
     * if no matches.
     * @throws IllegalArgumentException - if one of the parameters is null.
     * @throws IndexOutOfBoundsException - if no group 1.
     */

    public static String getStrWithRegex(String regex, String str) {
        if (regex == null || str == null) {
            throw new IllegalArgumentException("Params contain null value. regex=" + regex + "; str=" + str);
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
