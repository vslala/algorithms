package com.bma.problemsolving.codewars.morsecode;

import java.util.Arrays;
import java.util.Map;

public class MorseCodeDecoder {

    private static final Map<String, String> morseCodeToTextDictionary = Map.ofEntries(
            Map.entry("---..","8"),
            Map.entry("----.","9"),
            Map.entry("..--..","?"),
            Map.entry(".--.-.","@"),
            Map.entry(".-","a"),
            Map.entry("-...","b"),
            Map.entry("-.-.","c"),
            Map.entry("-..","d"),
            Map.entry(".","e"),
            Map.entry("..-.","f"),
            Map.entry("--.","g"),
            Map.entry("....","h"),
            Map.entry("..","i"),
            Map.entry(".---","j"),
            Map.entry("-.-","k"),
            Map.entry(".-..","l"),
            Map.entry("--","m"),
            Map.entry("-.","n"),
            Map.entry("---","o"),
            Map.entry(".--.","p"),
            Map.entry("--.-","q"),
            Map.entry(".-.","r"),
            Map.entry("...","s"),
            Map.entry("-","t"),
            Map.entry("..-","u"),
            Map.entry("...-","v"),
            Map.entry(".--","w"),
            Map.entry("-..-","x"),
            Map.entry("-.--","y"),
            Map.entry("--..","z"),
            Map.entry("-.-.--","!"),
            Map.entry("-.--.","("),
            Map.entry("-.--.-",")"),
            Map.entry("--..--",","),
            Map.entry("-....-","-"),
            Map.entry(".-.-.-","."),
            Map.entry("-..-.","/"),
            Map.entry("-----","0"),
            Map.entry(".----","1"),
            Map.entry("..---","2"),
            Map.entry("...--","3"),
            Map.entry("....-","4"),
            Map.entry(".....","5"),
            Map.entry("-....","6"),
            Map.entry("--...","7"),
            Map.entry("...---...","SOS")
    );
    private static final Map<String, String> textToMorseCodeDictionary = Map.ofEntries(
            Map.entry("0", "-----"),
            Map.entry("1", ".----"),
            Map.entry("2", "..---"),
            Map.entry("3", "...--"),
            Map.entry("4", "....-"),
            Map.entry("5", "....."),
            Map.entry("6", "-...."),
            Map.entry("7", "--..."),
            Map.entry("8", "---.."),
            Map.entry("9", "----."),
            Map.entry("a", ".-"),
            Map.entry("b", "-..."),
            Map.entry("c", "-.-."),
            Map.entry("d", "-.."),
            Map.entry("e", "."),
            Map.entry("f", "..-."),
            Map.entry("g", "--."),
            Map.entry("h", "...."),
            Map.entry("i", ".."),
            Map.entry("j", ".---"),
            Map.entry("k", "-.-"),
            Map.entry("l", ".-.."),
            Map.entry("m", "--"),
            Map.entry("n", "-."),
            Map.entry("o", "---"),
            Map.entry("p", ".--."),
            Map.entry("q", "--.-"),
            Map.entry("r", ".-."),
            Map.entry("s", "..."),
            Map.entry("t", "-"),
            Map.entry("u", "..-"),
            Map.entry("v", "...-"),
            Map.entry("w", ".--"),
            Map.entry("x", "-..-"),
            Map.entry("y", "-.--"),
            Map.entry("z", "--.."),
            Map.entry(".", ".-.-.-"),
            Map.entry(",", "--..--"),
            Map.entry("?", "..--.."),
            Map.entry("!", "-.-.--"),
            Map.entry("-", "-....-"),
            Map.entry("/", "-..-."),
            Map.entry("@", ".--.-."),
            Map.entry("(", "-.--."),
            Map.entry(")", "-.--.-"),
            Map.entry("SOS", "...---...")
    );
    public static final String MORSE_CODE_WORD_SEPARATOR = "   ";
    private static final String MORSE_CODE_CHAR_SEPARATOR = " ";

    public String decode(String morseCode) {
        morseCode = morseCode.trim();
        var output = new StringBuilder();
        var words = morseCode.split(MORSE_CODE_WORD_SEPARATOR);
        Arrays.stream(words).map(word -> word.split(MORSE_CODE_CHAR_SEPARATOR))
                .forEach(morseChar -> {
                    for (String c : morseChar) output.append(morseCodeToTextDictionary.get(c.trim()));
                    output.append(" ");
                });

        return output.toString().toUpperCase().trim();
    }

}
