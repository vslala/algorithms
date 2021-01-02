package com.bma.problemsolving.codewars;

import java.util.HashMap;
import java.util.HashSet;

public class SimpleEncryption {
    private SimpleEncryption() {
    }

    public static String encrypt(String text, int n) {
        if (n  <= 0) return text;

        // make the string odd then remove the special character after processing
        if (text.length() % 2 == 0)
            text += "%";

        var positionMap = new HashMap<>();
        String output = text;
        for (int round = 1; round <= n; round ++) {
            var builder = new StringBuilder();
            for (int i = 0, pos = 1; i < text.length(); i++, pos += 2) {
                final int index = pos % text.length();
                builder.append(output.charAt(index));
                positionMap.put(index, i);
            }
            output = builder.toString();
        }

        System.out.println(positionMap);

        return  output.replace("%", "");
    }

    public static String decrypt(String text, int n) {
        if (n <= 0) return text;

        String output =  text;
        for (int round = 1; round <= n; round++) {
            var builder = new StringBuilder();
            int right = output.length() / 2;
            int left = 0;
            for (int i = 0; i < output.length() / 2; i++) {
                builder.append(output.charAt(right++)).append(output.charAt(left++));
            }

            if (text.length() % 2 != 0)
                builder.append(output.charAt(right));
            output = builder.toString();
        }

        return output;
    }
}

class Kata {

    public static String encrypt(final String text, int n) {
        if (n <= 0 || text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder firstPart = new StringBuilder();
        StringBuilder secondPart = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char aChar = text.charAt(i);
            if (i % 2 == 1) {
                firstPart.append(aChar);
            } else {
                secondPart.append(aChar);
            }
        }

        return encrypt(firstPart.append(secondPart).toString(), --n);
    }

    public static String decrypt(final String encryptedText, int n) {
        if (n <= 0 || encryptedText == null || encryptedText.isEmpty()) {
            return encryptedText;
        }

        StringBuilder text = new StringBuilder();
        final int half = encryptedText.length() / 2;
        for (int i = 0; i < half; i++) {
            text.append(encryptedText.charAt(half + i)).append(encryptedText.charAt(i));
        }
        if (encryptedText.length() % 2 == 1) {
            text.append(encryptedText.charAt(encryptedText.length() - 1));
        }

        return decrypt(text.toString(), --n);
    }

}
