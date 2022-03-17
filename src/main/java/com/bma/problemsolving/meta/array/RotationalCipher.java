package com.bma.problemsolving.meta.array;

/**
 * Rotational Cipher
 * One simple way to encrypt a string is to "rotate" every alphanumeric character by a certain amount. Rotating a character means replacing it with another character that is a certain number of steps away in normal alphabetic or numerical order.
 * For example, if the string "Zebra-493?" is rotated 3 places, the resulting string is "Cheud-726?". Every alphabetic character is replaced with the character 3 letters higher (wrapping around from Z to A), and every numeric character replaced with the character 3 digits higher (wrapping around from 9 to 0). Note that the non-alphanumeric characters remain unchanged.
 * Given a string and a rotation factor, return an encrypted string.
 *
 * Signature
 * string rotationalCipher(string input, int rotationFactor)
 *
 * Input
 * 1 <= |input| <= 1,000,000
 * 0 <= rotationFactor <= 1,000,000
 *
 * Output
 * Return the result of rotating input a number of times equal to rotationFactor.
 *
 * Example 1
 * input = Zebra-493?
 * rotationFactor = 3
 * output = Cheud-726?
 *
 * Example 2
 * input = abcdefghijklmNOPQRSTUVWXYZ0123456789
 * rotationFactor = 39
 * output = nopqrstuvwxyzABCDEFGHIJKLM9012345678
 *
 * @author varun.shrivastava
 */
class RotationalCipher {

    public String rotationalCipher(String input, int rotationFactor) {
        StringBuilder sb = new StringBuilder();
        for (char c: input.toCharArray()) {
            if (c >= '0' && c <= '9') {
                int factor = rotationFactor % 10;
                char newChar = (char) (c + factor);
                sb.append(nextChar(newChar, '0', '9'));
            } else if (c >= 'A' && c <= 'Z') {
                int factor = rotationFactor % 26;
                char newChar = (char) (c + factor);
                sb.append(nextChar(newChar, 'A', 'Z'));
            } else if (c >= 'a' && c <= 'z') {
                int factor = rotationFactor % 26;
                char newChar = (char) (c + factor);
                sb.append(nextChar(newChar, 'a', 'z'));
            } else {
                sb.append(c);
            }

        }

        return sb.toString();
    }

    private char nextChar(char newChar, char low, char high) {
        if (newChar > high) {
            char nextChar = (char) (low + (newChar - high) - 1);
            return nextChar;
        }

        return newChar;
    }
}
