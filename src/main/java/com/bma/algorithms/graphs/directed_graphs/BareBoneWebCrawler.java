package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.stdlib.In;
import com.bma.algorithms.stdlib.StdOut;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BareBoneWebCrawler {

    private final List<String> websitesToCrawl;
    Queue<String> queue = new LinkedList<>();
    Set<String> discovered = new HashSet<>();
    Set<String> badLinks = new HashSet<>();


    public BareBoneWebCrawler(List<String> webPagesToCrawl) {
        this.websitesToCrawl = webPagesToCrawl;
        queue.addAll(webPagesToCrawl);
    }


    public Set<String> crawl() {
        try {
            while (!queue.isEmpty()) {
                String website = queue.remove();
                StdOut.println(website);

                In in = new In(website);

                String input = in.readAll();

                String regex = "https://(\\w+\\.)*(\\w+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);

                while (matcher.find()) {
                    String w = matcher.group();
                    if (!discovered.contains(w)) {
                        discovered.add(w);
                        queue.add(w);
                        // only queue the link if it is from the list of given websites
//                    if (websitesToCrawl.stream().anyMatch(w::contains))
//                        queue.add(w);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            badLinks.add(e.getMessage());
        }

        return discovered;
    }
}
