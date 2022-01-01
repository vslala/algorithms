package com.bma.algorithms.graphs.shortest_paths;

import com.bma.BMARuntimeException;
import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;
import com.bma.algorithms.priorityqueues.IndexMinPQ;
import com.bma.algorithms.stdlib.In;
import com.bma.algorithms.stdlib.StdOut;

import java.util.Stack;

/**
 *  The {@code ReferenceDijkstraSP} class represents a data type for solving the
 *  single-source shortest paths problem in IEdge-weighted digraphs
 *  where the IEdge weights are nonnegative.
 *  <p>
 *  This implementation uses <em>Dijkstra's algorithm</em> with a
 *  <em>binary heap</em>. The constructor takes
 *  &Theta;(<em>E</em> log <em>V</em>) time in the worst case,
 *  where <em>V</em> is the number of vertices and <em>E</em> is
 *  the number of edges. Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the
 *  IEdge-weighted digraph).
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class ReferenceDijkstraSP implements SingleSourceShortestPath {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last IEdge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the IEdge-weighted digraph {@code G}.
     *
     * Steps to compute
     * ----------------
     * 1. Verify if all the edges have positive weights
     * 2. Set all vertex distance to infinity
     * 3. Set source vertex dist to 0
     * 4. Relax Vertices in order of distance from S
     *
     * @param  G the IEdge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an IEdge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public ReferenceDijkstraSP(EdgeWeightedDigraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("IEdge " + e + " has negative weight");
        }

        distTo = new double[G.totalVertices()];
        edgeTo = new DirectedEdge[G.totalVertices()];

        validateVertex(s);

        for (int v = 0; v < G.totalVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<>(G.totalVertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }

        // check optimality conditions
        assert check(G, s);
    }

    // relax IEdge e and update pq if changed
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public double farthestReachableDistance() {
        double farthest = -1;
        for (double to : distTo) {
            if (to == Double.POSITIVE_INFINITY) continue;
            farthest = Math.max(to, farthest);
        }

        return farthest;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all IEdge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()

    private boolean check(EdgeWeightedDigraph G, int s) {

        // check that IEdge weights are nonnegative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative IEdge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.totalVertices(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.totalVertices(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("IEdge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.totalVertices(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("IEdge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }
    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    /**
     * Unit tests the {@code ReferenceDijkstraSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = EdgeWeightedDigraph.createDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        ReferenceDijkstraSP sp = new ReferenceDijkstraSP(G, s);


        shortestPath(G, s, sp);
    }

    public static void shortestPath(EdgeWeightedDigraph g, int s, ReferenceDijkstraSP sp) {
        // print shortest path
        for (int t = 0; t < g.totalVertices(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }

    @Override
    public boolean hasNegativeCycle() {
        throw new BMARuntimeException("Dijsktra Algorithm cannot detect negative weight cycles in a graph. Try Bellmand-Ford.");
    }

    @Override
    public Iterable<DirectedEdge> negativeCycle() {
        throw new BMARuntimeException("Dijsktra Algorithm cannot detect negative weight cycles in a graph. Try Bellmand-Ford.");
    }
}
