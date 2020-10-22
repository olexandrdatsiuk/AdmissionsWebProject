package ua.training.util;

import ua.training.exception.Message;

public class NumericParser {

    private NumericParser() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static int parseInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return Math.max(i, 0);
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }

    public static float parseFloat(String s) {
        try {
            float i = Float.parseFloat(s);
            return Math.max(i, 0);
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }
}
