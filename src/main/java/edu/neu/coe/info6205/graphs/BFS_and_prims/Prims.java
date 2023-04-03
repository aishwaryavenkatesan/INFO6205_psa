/*  
*  For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
*  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
*
*  @author Robert Sedgewick
*  @author Kevin Wayne 
*/

package edu.neu.coe.info6205.graphs.BFS_and_prims;

import java.util.LinkedList;
import java.util.Queue;

public class Prims {
    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // crossing (and ineligible) edges
    public Prims(EdgeWeightedGraph G)
    {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        visit(G, 0); // assumes G is connected
        while (!pq.isEmpty())
        {
            Edge e = pq.delMin(); // Get lowest-weight
            int v = e.either(), w = e.other(v); // edge from pq.
            if (marked[v] && marked[w]) continue; // Skip if ineligible.
            mst.add(e); // Add edge to tree.
            if (!marked[v]) visit(G, v); // Add vertex to tree
            if (!marked[w]) visit(G, w); // (either v or w).
        }
    }
    private void visit(EdgeWeightedGraph G, int v)
    { // Mark v and add to pq all edges from v to unmarked vertices.
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges()
    {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(9);
        Edge e1 = new Edge(0,1,12);
        Edge e2 = new Edge(0,2,10);
        Edge e3 = new Edge(0,3,19);
        Edge e4 = new Edge(0,4,8);
        Edge e5 = new Edge(1,2,3);
        Edge e6 = new Edge(1,3,7);
        Edge e7 = new Edge(1,4,2);
        Edge e8 = new Edge(2,3,6);
        Edge e9 = new Edge(2,4,4);
        Edge e10 = new Edge(3,4,4);
//        Edge e11 = new Edge(5,3,14);
//        Edge e12 = new Edge(5,4,10);
//        Edge e13 = new Edge(3,4,9);
//        Edge e14 = new Edge(6,5,2);

        edgeWeightedGraph.addEdge(e1);
        edgeWeightedGraph.addEdge(e2);
        edgeWeightedGraph.addEdge(e3);
        edgeWeightedGraph.addEdge(e4);
        edgeWeightedGraph.addEdge(e5);
        edgeWeightedGraph.addEdge(e6);
        edgeWeightedGraph.addEdge(e7);
        edgeWeightedGraph.addEdge(e8);
        edgeWeightedGraph.addEdge(e9);
        edgeWeightedGraph.addEdge(e10);
//        edgeWeightedGraph.addEdge(e11);
//        edgeWeightedGraph.addEdge(e12);
//        edgeWeightedGraph.addEdge(e13);
//        edgeWeightedGraph.addEdge(e14);

        Prims prims = new Prims(edgeWeightedGraph);

        for (Edge edge : prims.mst) {
            System.out.println("printing edge " + edge);
        }

        System.out.println("inside main");

    }
}

