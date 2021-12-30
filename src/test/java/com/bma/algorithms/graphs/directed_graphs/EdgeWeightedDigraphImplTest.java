package com.bma.algorithms.graphs.directed_graphs;

import com.bma.algorithms.graphs.model.DirectedEdge;
import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeWeightedDigraphImplTest {

    @Test
    void shouldCreateDirectedGraphWithWeights() {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraphImpl(Path.of("src/test/resources/graphs/shortestpath/directed_graph.csv"));
        assertEquals(8, digraph.totalVertices());
        assertEquals(16, digraph.totalEdges());

        Iterable<DirectedEdge> directedEdge = digraph.edges();
        Iterator<DirectedEdge> itr = directedEdge.iterator();
        PriorityQueue<DirectedEdge> minHeap = new PriorityQueue<>();
        while (itr.hasNext()) {
            DirectedEdge nextEdge = itr.next();
            Util.println(nextEdge);
            minHeap.add(nextEdge);
        }

        // check if edges are formed correctly
        assertEquals(new DirectedEdge(5, 2, 1.0), minHeap.remove());
        assertEquals(new DirectedEdge(2, 3, 3.0), minHeap.remove());
        assertEquals(new DirectedEdge(4, 5, 4.0), minHeap.remove());
    }

}