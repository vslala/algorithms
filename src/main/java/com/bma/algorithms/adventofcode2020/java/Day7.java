package com.bma.algorithms.adventofcode2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day7 {
    public static final String IN_PATH = "/Users/in-varun.shrivastava/Code/src/github.com/java/Algorithms/src/main/java/com/bma/algorithms/adventofcode2020/inputs/day_7_input.txt";

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of(IN_PATH));
            String toFind = "shiny gold";

            Set<String> distinctBags = new HashSet<>();
            Queue<String> bags = new LinkedList<>();
            bags.add(toFind);

            while (!bags.isEmpty()) {
                toFind = bags.remove();
                for (String line : lines) {
                    String[] parts = line.split(" contain ");
                    String container = parts[0].trim();
                    String contains = parts[1].trim();

                    if (contains.contains(toFind) || contains.contains(toFind.substring(0, toFind.length() - 1))) {
                        System.out.println("To Find: " + toFind);
                        if (!distinctBags.contains(container))
                            bags.add(container);
                        distinctBags.add(container);
                    }
                }
            }


            System.out.println(distinctBags.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class A {
    public static void main(String[] args) throws FileNotFoundException {
        Map cmap = new HashMap<String, Set<String>>();

        Map staticentry = new HashMap<String, Set<String>>();
        staticentry.put("dotted black", new TreeSet<>());
        staticentry.put("faded blue", new TreeSet<>());


        Scanner sc = new Scanner(new File(Day7.IN_PATH));

        while (sc.hasNextLine()) {
            Set maincolors = new TreeSet<String>();
            String val = sc.nextLine();
            String[] texts = val.trim().split("contain");
            String colorbag = texts[0].split("bag")[0].trim();

            String fitbag[] = texts[1].split(",");

            for (String fitbg : fitbag) {
                if (fitbg.contains("bag")) {
                    String[] fitcolors = fitbg.split("bag")[0].trim().split("\\s+");
                    String maincolor = "";
                    if (fitcolors.length == 3) {
                        maincolor = fitcolors[1] + " " + fitcolors[2].trim();
                    } else {
                        maincolor = fitcolors[1].trim();
                    }
                    maincolors.add(maincolor);
                }
            }

            cmap.put(colorbag, maincolors);


        }

        Set<String> allkeys = new TreeSet<>();
        Set<String> totalkeys = new TreeSet<>();
        Set<String> keys1 = loopIterator("shiny gold", cmap, staticentry);
        System.out.println("keys1====" + keys1);
        allkeys.addAll(keys1);
        totalkeys.addAll(keys1);


        while (!keys1.isEmpty()) {

            keys1.clear();
            System.out.println("before sending==" + keys1.size());
            for (String key2 : allkeys) {
                System.out.println("sending==" + key2);
                keys1.addAll(loopIterator(key2, cmap, staticentry));
            }
            System.out.println("done sending==");
            System.out.println("total done sending==" + keys1.size());

            allkeys.clear();
            allkeys.addAll(keys1);
            totalkeys.addAll(keys1);
        }
        System.out.println(totalkeys.size());


    }


    private static Set<String> loopIterator(String mainkey, Map<String, Set<String>> cmap, Map<String, Set<String>> staticentry) {

        Set<String> keys = new TreeSet<>();

        for (Iterator it = cmap.keySet().iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            if (cmap.get(key).contains(mainkey) && !staticentry.containsKey(mainkey)) {
                keys.add(key);
            }

        }
        System.out.println("size of main key is==" + keys.size() + " for " + mainkey);

        return keys;

    }
}

