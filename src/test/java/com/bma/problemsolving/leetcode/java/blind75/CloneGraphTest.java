package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CloneGraphTest {

    private CloneGraph sol;

    @Test
    void should_return_the_clone_of_the_provided_graph() {
        CloneGraph.Node firstNode = new CloneGraph.Node(1, new ArrayList<>());
        CloneGraph.Node secondNode = new CloneGraph.Node(2, new ArrayList<>());
        CloneGraph.Node thirdNode = new CloneGraph.Node(3, new ArrayList<>());
        CloneGraph.Node fourthdNode = new CloneGraph.Node(4, new ArrayList<>());

        firstNode.addNeighbour(secondNode);
        firstNode.addNeighbour(fourthdNode);

        secondNode.addNeighbour(thirdNode);

        thirdNode.addNeighbour(fourthdNode);

        sol = new CloneGraph();
        CloneGraph.Node output = sol.cloneGraph(firstNode);
        printAll(output, new HashSet<>());
        assertEquals(2, output.neighbors.getFirst().val);
        assertEquals(4, output.neighbors.get(1).val);
    }

    void printAll(CloneGraph.Node node, HashSet<CloneGraph.Node> visited) {
        visited.add(node);
        if (node != null) {
            for (CloneGraph.Node neighbour : node.neighbors) {
                Util.print("""
                        %s -> %s
                        """.formatted(node.val, neighbour.val));
                if (!visited.contains(neighbour))
                    printAll(neighbour, visited);
            }
        }
    }
}