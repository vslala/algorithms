package com.bma.algorithms.adventofcode2020;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

interface Validator {
    boolean validate(String credential);
}

@AllArgsConstructor
class RangeAndLengthValidator implements Validator {

    private int lower;
    private int higher;
    private int length;

    @Override
    public boolean validate(String credential) {
        if (credential.length() != length) return false;

        int year = Integer.parseInt(credential);
        return year >=lower && year <= higher;
    }
}

class HeightValidator implements Validator {

    @Override
    public boolean validate(String credential) {
        String unit = credential.substring(credential.length() - 2);
        if (unit.equals("cm") || unit.equals("in")) {
            int height = Integer.parseInt(credential.substring(0, credential.length() - 2));
            if (unit.equals("cm"))
                return height >= 150 && height <= 193;
            return height >= 59 && height <= 76;
        }
        return false;
    }
}

class HairColorValidator implements Validator{

    @Override
    public boolean validate(String credential) {
        if (credential.length() != 7 || credential.charAt(0) != '#')
            return false;

        String hex = credential.substring(1);
        for (int i=0; i  < hex.length(); i++) {
            if (!Character.isDigit(hex.charAt(i)) || !((int)hex.charAt(i) >= 97 && hex.charAt(i) <= 102))
                return false;
        }

        return true;
    }
}

@AllArgsConstructor
class WhiteListValidator implements Validator {

    private String[] whitelist;

    @Override
    public boolean validate(String credential) {
        return Arrays.asList(whitelist).contains(credential);
    }
}

class NumberValidator implements  Validator {

    @Override
    public boolean validate(String credential) {
        return String.valueOf(Integer.parseInt(credential)).length() == 9;
    }
}

class AlwaysTrue implements Validator {

    @Override
    public boolean validate(String credential) {
        return true;
    }
}

@ToString
@Getter
class PassportDetails {
    private String byr; // (Birth Year)
    private String iyr; // (Issue Year)
    private String eyr; // (Expiration Year)
    private String hgt; // (Height)
    private String hcl; // (Hair Color)
    private String ecl; // (Eye Color)
    private String pid; // (Passport ID)
    private String cid; // (Country ID)

    public boolean isValid() {

        var validators = Map.of(
                byr, new RangeAndLengthValidator(1920, 2002, 4),
                iyr, new RangeAndLengthValidator(2010, 2020, 4),
                eyr, new RangeAndLengthValidator(2020, 2030, 4),
                hgt, new HeightValidator(),
                hcl, new HairColorValidator(),
                ecl, new WhiteListValidator(new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}),
                pid, new NumberValidator(),
                cid, new AlwaysTrue()
        );

        AtomicBoolean isValid = new AtomicBoolean();
        return validators.entrySet()
                .stream()
                .allMatch(stringValidatorEntry -> stringValidatorEntry.getValue().validate(stringValidatorEntry.getKey()));
    }
}

class Parser {
    public static Optional<PassportDetails> parse(String passportDetailsText) {

        try {
            String[] keyval = passportDetailsText.split(" ");
            var passportDetails = new PassportDetails();
            Class c = passportDetails.getClass();

            for (String keyVal: keyval) {
                String[] parts = keyVal.split(":");
                Field field = c.getDeclaredField(parts[0]);
                field.setAccessible(true);
                field.set(passportDetails, parts[1]);
            }

            return Optional.of(passportDetails);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

public class Day4 {
    private static final String FILE_PATH = "/Users/in-varun.shrivastava/Code/src/github.com/java/Algorithms/src/main/java/com/bma/algorithms/adventofcode2020/inputs/day4.txt";
    public boolean isValid(String passportDetails) {
        return Parser.parse(passportDetails)
                .map(PassportDetails::isValid)
                .orElse(false);
    }

    public static void main(String[] args) throws IOException {
        var day4 = new Day4();
        var text = Files.readString(Path.of(FILE_PATH));
        String[] lines = text.split("\n\n");

        long count = Arrays.stream(lines)
                .map(line -> line.replace("\n", " "))
                .map(day4::isValid)
                .filter(isValid -> isValid)
                .count();

        System.out.println(count);

    }
}
