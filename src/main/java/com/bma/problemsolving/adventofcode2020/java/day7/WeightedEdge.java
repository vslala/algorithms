package com.bma.problemsolving.adventofcode2020.java.day7;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeightedEdge {
    private String v1;
    private String v2;
    private double w;

    public WeightedEdge(String from, String vertex) {
        if (vertex.matches("no other bags.")) {
            this.v1 = from;
            this.v2 = "";
            this.w  = 0;
        } else {
            this.v1 = from;
            this.v2 = vertex.substring(vertex.indexOf(" "), vertex.indexOf("bag")).trim();
            this.w = Double.parseDouble(vertex.substring(0, vertex.indexOf(" ")));
        }
    }

    public String other(String vertex) {
        return vertex.equals(v1) ? v2 : v1;
    }
}
