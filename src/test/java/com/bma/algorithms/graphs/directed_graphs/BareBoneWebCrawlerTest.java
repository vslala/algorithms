package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.stdlib.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

class BareBoneWebCrawlerTest {

    private static final List<String> websitesToCrawl = List.of("https://www.bemyaficionado.com");
    private BareBoneWebCrawler webCrawler;

    @BeforeEach
    public void setup() {
        webCrawler = new BareBoneWebCrawler(websitesToCrawl);
    }

    @Test
    public void itShouldVisitAllTheLinksInTheWebsite() {
        Set<String> discoveredLinks = webCrawler.crawl();

        StdOut.println(discoveredLinks.stream().limit(100).collect(Collectors.joining(", ")));
        assertFalse(discoveredLinks.isEmpty());
    }
}