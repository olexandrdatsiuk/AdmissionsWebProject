package ua.training.util;

import ua.training.exception.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a Encryptor.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public class Encryptor {
    private Encryptor() {
        throw new AssertionError(Message.PRIVATE_CONSTRUCTOR_ERROR);
    }

    /**
     * Encrypts a value with MD5 algorithm.
     *
     * @param value A String containing the value to crypt.
     * @return A String representing the encrypted value in
     * hexadecimal form in upper case.
     * @throws IllegalArgumentException - if value is null.
     */
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
            // todo logger
        }
        return hash.toString();
    }
}
