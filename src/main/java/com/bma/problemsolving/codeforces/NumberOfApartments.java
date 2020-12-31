package com.bma.problemsolving.codeforces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberOfApartments {

    private Map<Integer, Integer[]> keyval = new HashMap<>();

    public static final Integer[] NOT_FOUND = {-1, -1, -1};

    {
        keyval.put(1, NOT_FOUND);
        keyval.put(2, NOT_FOUND);
    }

    private Integer[] buildingTypes = {3, 5, 7};

    public String guess(int windows) {
        for (int i = 3; i <= 1000; i++) {
            if (findVal(i)) continue;;

            int num = i;
            for (int j = 0; j < 3; j++) {
                num = num - buildingTypes[j];
                if (num < 3) num = 1;
                Integer[] val = keyval.get(num);
                if (val[0] == -1) {
                    keyval.put(i, NOT_FOUND);
                } else {
                    Integer[] newVal = Arrays.copyOfRange(val, 0, 3);
                    newVal[j] = newVal[j] + 1;
                    keyval.put(i, newVal);
                }
                break;
            }
        }
        Integer[] arr = keyval.get(windows);
        String result = Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(" "));
        return result;
    }

    private boolean findVal(int i) {
        boolean found = false;
        Integer[] value = {0, 0, 0};
        for (int j = 0; j < 3; j++) {
            if (i % buildingTypes[j] == 0) {
                value[j] = i / buildingTypes[j];
                keyval.put(i, value);
                found = true;
                break;
            }
        }

        return found;
    }
}
