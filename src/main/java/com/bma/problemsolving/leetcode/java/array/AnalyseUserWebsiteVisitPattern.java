package com.bma.problemsolving.leetcode.java.array;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * You are given two string arrays username and website and an integer array timestamp.
 * All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].
 *
 * A pattern is a list of three websites (not necessarily distinct).
 *
 * For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
 * The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
 *
 * For example,
 * if the pattern is ["home", "away", "love"],
 * the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
 *
 * Similarly, if the pattern is ["leetcode", "love", "leetcode"],
 * the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
 * Also, if the pattern is ["luffy", "luffy", "luffy"],
 * the score is the number of users x such that x visited "luffy" three different times at different timestamps.
 *
 * Return the pattern with the largest score.
 * If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 *
 * @author varun.shrivastava
 */
class AnalyseUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        var userHistory = new HashMap<String, List<Tuple>>();
        for (int i = 0; i < username.length; i++) {
            userHistory.computeIfAbsent(username[i], index -> new ArrayList<>())
                            .add(new Tuple(timestamp[i], website[i]));
        }

        var result = "";
        var patternFrequency = new HashMap<String, Integer>();
        for (String user: userHistory.keySet()) {
            var visitPattern = userHistory.get(user);
            visitPattern.sort(Comparator.comparingInt(a -> a.timestamp));

            var set = new HashSet<String>();
            for (int i = 0; i < visitPattern.size(); i++) {
                for (int j = i + 1; j < visitPattern.size(); j++) {
                    for (int k = j + 1; k < visitPattern.size(); k++) {
                        var pattern = visitPattern.get(i).website + " " + visitPattern.get(j).website + " " + visitPattern.get(k).website;
                        if (!set.contains(pattern)) {
                            patternFrequency.put(pattern, patternFrequency.getOrDefault(pattern, 0) + 1);
                            set.add(pattern);
                        }

                        if (result.isEmpty()) {
                            result = pattern;
                        } else if (patternFrequency.get(result) < patternFrequency.get(pattern)) {
                            result = pattern;
                        } else if (patternFrequency.get(result) == patternFrequency.get(pattern) && pattern.compareTo(result) < 0) {
                            result = pattern;
                        }
                    }
                }
            }
        }

        return Arrays.asList(result.split(" "));
    }

    @Data
    @AllArgsConstructor
    static class Tuple {
        int timestamp;
        String website;
    }
}
