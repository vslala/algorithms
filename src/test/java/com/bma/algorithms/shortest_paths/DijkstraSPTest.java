package com.bma.algorithms.shortest_paths;

import com.bma.algorithms.stdlib.StdOut;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraSPTest {

    @Test
    void itShouldFindTheShortestPathFromTheSourceToAllOtherVertices() {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(8);
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

        DijkstraSP dijkstraSP = new DijkstraSP(digraph, 0);
        dijkstraSP.shortestPath(digraph, 0, dijkstraSP);
    }

}