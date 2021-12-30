package com.bma.algorithms.graphs.shortest_paths;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;
import org.junit.jupiter.api.Test;

class ReferenceDijkstraSPTest {

    @Test
    void itShouldFindTheShortestPathFromTheSourceToAllOtherVertices() {
        EdgeWeightedDigraph digraph = EdgeWeightedDigraph.createDigraph(8);
        digraph.addEdge(new DirectedEdge(0,1, 5));
        digraph.addEdge(new DirectedEdge(0,7, 8));
        digraph.addEdge(new DirectedEdge(0,4, 9));
        digraph.addEdge(new DirectedEdge(4,7, 5));
        digraph.addEdge(new DirectedEdge(4,5, 4));
        digraph.addEdge(new DirectedEdge(4,6, 20));
        digraph.addEdge(new DirectedEdge(1,3, 15));
        digraph.addEdge(new DirectedEdge(1,7, 4));
        digraph.addEdge(new DirectedEdge(1,2, 12));
        digraph.addEdge(new DirectedEdge(7,2, 7));
        digraph.addEdge(new DirectedEdge(7,5, 6));
        digraph.addEdge(new DirectedEdge(5,2, 1));
        digraph.addEdge(new DirectedEdge(5,6, 13));
        digraph.addEdge(new DirectedEdge(2,3, 3));
        digraph.addEdge(new DirectedEdge(2,6, 11));

        ReferenceDijkstraSP referenceDijkstraSP = new ReferenceDijkstraSP(digraph, 0);
        referenceDijkstraSP.shortestPath(digraph, 0, referenceDijkstraSP);
    }

}