package git.CSCI261.HW3;

import java.util.*;

class Node implements Comparator<Node> {
    public int n;
    public int cost;
    public int path;

    public Node() {
    }

    public Node(int node, int cost) {
        this.n = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.cost, n2.cost);
    }
}

public class Djikstra {
    private int dst[];
    private Set<Integer> sets;
    private PriorityQueue<Node> queue;
    private int VERTICES;
    List<List<Node>> adj;

    public Djikstra(int v) {
        this.VERTICES = v;
        this.dst = new int[v];
        this.sets = new HashSet<Integer>();
        this.queue = new PriorityQueue<Node>(v, new Node());
    }

    private void edgeNeighbors(int w) {
        int eDist = -1;
        int nDist = -1;

        for (int i = 0; i < adj.get(w).size(); i++) {
            Node v = adj.get(w).get(i);
            if (!sets.contains(v.n)) {
                eDist = v.cost;
                nDist = dst[w] + eDist;

                // Ternary operation reassign dst if nDist is better pick
                // dst[v.n] = (nDist < dst[v.n]) ? nDist : dst[v.n];
                if (nDist < dst[v.n]) {
                    dst[v.n] = nDist;
                    v.path = w;
                }
                queue.add(new Node(v.n, dst[v.n]));
            }
        }
    }

    public void driver(int src, List<List<Node>> adj) {
        this.adj = adj;

        for (int i = 0; i < VERTICES; i++)
            dst[i] = Integer.MAX_VALUE;

        // Add src node to the queue
        queue.add(new Node(src, 0));

        // Initialize dist to 0
        dst[src] = 0;
        while (sets.size() != VERTICES) {
            // Remove minimum distance
            int u = queue.remove().n;
            sets.add(u);
            edgeNeighbors(u);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<List<Node>>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the djk graph
        adj.get(0).add(new Node(1, 3));
        adj.get(0).add(new Node(2, 6));
        adj.get(1).add(new Node(3, 2));
        adj.get(2).add(new Node(3, 7));
        adj.get(3).add(new Node(0, 8));

        adj.get(2).add(new Node(4, 3));
        adj.get(3).add(new Node(4, 6));

        // Calculate the single source shortest path
        Djikstra djk = new Djikstra(V);
        djk.driver(source, adj);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < djk.dst.length; i++) {
            if (i == source) {
                System.out.println(i + " dist: " + djk.dst[i] + " path: null");
                continue;
            }
            System.out.printf(i + " dist: " + djk.dst[i] + " path: ");
        }
        System.out.println("Completed");
    }
}