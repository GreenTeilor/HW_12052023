package by.teachmeskills.ps.utils;

import java.util.Base64;

public class EncryptionUtils {
    private EncryptionUtils() {

    }

    public static String encrypt(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }

    public static String decrypt(String cipherText) {
        return new String(Base64.getDecoder().decode(cipherText));
    }
}
