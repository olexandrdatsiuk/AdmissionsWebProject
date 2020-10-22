package ua.training.util;

import ua.training.exception.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypter {
    private Crypter() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    public static String cryptWithMD5(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }

        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                hash.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            // do nothing
        }
        return hash.toString();
    }
}
