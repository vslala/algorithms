package com.bma.problemsolving.leetcode.java.contest;

import java.util.*;
import java.util.stream.Collectors;

public class DailyChallenge {
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    static class RandomizedSet {
        private Map<Integer, Integer> dict;
        private List<Integer> ls;

        public RandomizedSet() {
            ls = new ArrayList<>();
            dict = new HashMap<>();
        }

        public boolean insert(int val) {
            if (dict.containsKey(val)) {
                return false;
            } else {
                ls.add(val);
                dict.put(val, ls.size() - 1);

                return true;
            }
        }

        public boolean remove(int val) {
            if (dict.containsKey(val)) {
                var index = dict.get(val);
                swap(ls, index, ls.size() - 1);
                dict.put(ls.get(index), index);
                ls.remove(ls.size() - 1);
                dict.remove(val);

                return true;
            } else {
                return false;
            }
        }

        private void swap(List<Integer> ls, int i, int j){
            if (i == j) return;
            var t = ls.get(i);
            ls.set(i, ls.get(j));
            ls.set(j, t);
        }

        private void printLs() {
            var output = ls.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println(output);
        }

        public int getRandom() {
            Random rand = new Random();
            var randIndex = rand.nextInt() % ls.size();
            return ls.get(Math.abs(randIndex));
        }
    }

    static class UniqueNumberOfOccurrences {
        public boolean uniqueOccurrences(int[] arr) {
            var dict = new HashMap<Integer, Integer>();
            for (int num : arr) {
                if (dict.containsKey(num)) {
                    dict.put(num, dict.get(num) + 1);
                } else {
                    dict.put(num, 1);
                }
            }

            var ls = dict.values();
            var set = new HashSet<Integer>();

            for (Integer i : ls) {
                if (set.contains(i)) {
                    return false;
                } else {
                    set.add(i);
                }
            }

            return true;
        }
    }
}
