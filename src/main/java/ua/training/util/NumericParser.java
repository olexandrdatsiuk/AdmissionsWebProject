package ua.training.util;

import ua.training.exception.Message;

/**
 * Represents a NumericParser.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class NumericParser {

    private NumericParser() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    /**
     * Parses int value from string.
     *
     * @param s A String containing the value to parse.
     * @return A int representing the parsed value or 0,
     * if the NumberFormatException is thrown
     */
    public static int parseInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return Math.max(i, 0);
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }

    /**
     * Parses float value from string.
     *
     * @param s A String containing the value to parse.
     * @return A float representing the parsed value or 0,
     * if the NumberFormatException is thrown
     */
    public static float parseFloat(String s) {
        try {
            float i = Float.parseFloat(s);
            return Math.max(i, 0);
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }
}
