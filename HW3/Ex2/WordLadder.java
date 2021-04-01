//Aidan Mellin
package git.CSCI261.HW3.Ex2;

import java.io.*;
import java.util.*;

class Node implements Comparator<Node> {
    public int n;
    public int cost;
    public int path;
    public int dist;
    public String name;

    public Node() {
    }

    public Node(int node, int cost, String name) {
        this.n = node;
        this.cost = cost;
        this.path = Integer.MAX_VALUE;
        this.name = name;
    }

    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.cost, n2.cost);
    }
}

public class WordLadder {

    private int dst[];
    private Set<Integer> sets;
    private PriorityQueue<Node> queue;
    private int VERTICES;
    public String name;
    List<List<Node>> adj;

    public WordLadder(int v) {
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

                if (nDist < dst[v.n]) {
                    dst[v.n] = nDist;
                    v.path = w;
                }
                queue.add(new Node(v.n, dst[v.n], v.name));
            }
        }
    }

    public void driver(int src, List<List<Node>> adj) {
        this.adj = adj;

        for (int i = 0; i < VERTICES; i++)
            dst[i] = Integer.MAX_VALUE;

        // Add src node to the queue
        queue.add(new Node(src, 0, ""));

        // Initialize dist to 0
        dst[src] = 0;
        while (sets.size() != VERTICES) {
            // Remove minimum distance
            if (queue.peek() != null) {
                int u = queue.remove().n;
                sets.add(u);
                edgeNeighbors(u);
            } else {
                break;
            }
        }
    }

    public void bubblesort(ArrayList<Node> arr) {
        int len = arr.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr.get(j).dist > arr.get(j + 1).dist) {
                    Collections.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int V = 0;
        int source;
        String sourceName;

        if (args.length > 1) {
            source = Integer.parseInt(args[1]) - 1;
        } else
            source = 0;

        // Interpreted is a non-adj Hashmap of Nodes that contains final data
        // (specifically dist & path)
        HashMap<Integer, Node> interpreted = new HashMap<Integer, Node>();

        // Adjacency list representation of the connected edges
        List<List<Node>> adj = new ArrayList<List<Node>>();

        try {
            File file = new File(args[0]);
            BufferedReader fp = new BufferedReader(new FileReader(file));

            String line;

            // Total number of vertices
            V = Integer.parseInt(fp.readLine().replaceAll(" # number of words", ""));

            // Initialize list for every node
            for (int i = 0; i < V; i++) {
                List<Node> item = new ArrayList<Node>();
                adj.add(item);
                interpreted.put(i, new Node(i, Integer.MAX_VALUE, ""));
            }
            // Read through the file, process inputs. Add all inputs to a blank adj list
            while ((line = fp.readLine()) != null) {
                String nodeLine[] = line.split(" ");
                if (!(nodeLine.length < 2)) {
                    int currNode = Integer.parseInt(nodeLine[0].replaceAll("Node\\[(.*?)\\]:", "$1")) - 1;
                    String currNodeName = nodeLine[1];
                    System.out.printf("currNode num: %d\tname: %s\n", currNode, currNodeName);
                    List<String> edges = new ArrayList<String>(Arrays.asList(nodeLine));
                    edges.remove(0);
                    edges.remove(0);
                    for (String s : edges)
                        adj.get(currNode).add(new Node(Integer.parseInt(s.split(":")[0]) - 1,
                                Integer.parseInt(s.split(":")[1]), currNodeName));
                }
            }
            fp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Calculate the single source shortest path
        WordLadder wld = new WordLadder(V);
        wld.driver(source, adj);

        // Populate the hashmap with the best paths
        for (List<Node> i : adj) {
            for (Node n : i) {
                n.dist = wld.dst[n.n];
                if (n.name != interpreted.get(n.n).name)
                    interpreted.replace(n.n, (n.cost < interpreted.get(n.n).cost) ? n : interpreted.get(n.n));
            }
        }

        ArrayList<Node> arr = new ArrayList<Node>();

        // Print the shortest path to all the nodes
        // from the source node
        for (int i = 0; i < wld.dst.length; i++) {
            Node n = interpreted.get(i);
            if (!arr.contains(n))
                arr.add(n);
        }

        // wld.bubblesort(arr);
        // Printing the ordered Nodes (by distance)
        for (Node n : arr) {

            // if (n.path > V + 1)
            // // *and here
            // n.path = 0;
            if (interpreted.get(n.path) != null)
                System.out.println((n.n) + " name: " + n.name + " dist: " + n.dist + " path from: "
                        + interpreted.get(n.path).name);
            else
                System.out.println(interpreted.get(source).name);

        }
    }
}