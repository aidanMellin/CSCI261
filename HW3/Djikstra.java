package git.CSCI261.HW3;

import java.io.*;
import java.util.*;

class Node implements Comparator<Node> {
    public int n;
    public int cost;
    public int path;
    public int dist;

    public Node() {
    }

    public Node(int node, int cost) {
        this.n = node;
        this.cost = cost;
        this.path = Integer.MAX_VALUE;
    }

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

    public void quickSort(ArrayList<Integer> arr, int b, int e) {
        if (b < e) {
            int partitionIndex = partition(arr, b, e);

            quickSort(arr, b, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, e);

        }
    }

    private int partition(ArrayList<Integer> arr, int b, int e) {
        int pivot = arr.get(e);
        int i = (b - 1);

        for (int j = b; j < e - 1; j++) {
            if (arr.get(j) < pivot) {
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, e);

        return i + 1;
    }

    public static void main(String[] args) {
        int V = 0;
        int source;

        if (args.length > 1) {
            if (Integer.parseInt(args[1]) == 0)
                source = 0;
            else
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
            V = Integer.parseInt(fp.readLine().replaceAll("\\s", ""));

            // Initialize list for every node
            for (int i = 0; i < V; i++) {
                List<Node> item = new ArrayList<Node>();
                adj.add(item);
            }
            // Read through the file, process inputs. Add all inputs to a blank adj list
            while ((line = fp.readLine()) != null) {
                String nodeLine[] = line.split(" ");
                int currNode = Integer.parseInt(nodeLine[0].replaceAll("Node\\[(.*?)\\]:", "$1")) - 1;
                List<String> edges = new ArrayList<String>(Arrays.asList(nodeLine));
                edges.remove(0);
                for (String s : edges)
                    adj.get(currNode)
                            .add(new Node(Integer.parseInt(s.split(":")[0]) - 1, Integer.parseInt(s.split(":")[1])));
            }
            fp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Populate a "blank" interpreted
        for (int i = 0; i < V; i++) {
            interpreted.put(i, new Node(i, Integer.MAX_VALUE));
        }

        // Calculate the single source shortest path
        Djikstra djk = new Djikstra(V);
        djk.driver(source, adj);

        // Populate the hashmap with the best paths
        for (List<Node> i : adj) {
            for (Node n : i) {
                n.dist = djk.dst[n.n];
                interpreted.replace(n.n, (n.cost < interpreted.get(n.n).cost) ? n : interpreted.get(n.n));
            }
        }

        ArrayList<Integer> distance = new ArrayList<Integer>();
        ArrayList<Node> arr = new ArrayList<Node>();

        // Print the shortest path to all the nodes
        // from the source node
        for (int i = 0; i < djk.dst.length; i++) {
            Node n = interpreted.get(i);
            distance.add(n.dist);
        }

        // Quicksort algorithm didn't like trying to use the Node, so I made an int list
        // of the distances, Sorted them, and then checked against interpreted to then
        // add all Nodes in the correct order
        djk.quickSort(distance, 0, V - 1);
        for (int d : distance) {
            for (int i = 0; i < djk.dst.length; i++) {
                Node n = interpreted.get(i);
                if (n.dist == d && !arr.contains(n))
                    arr.add(n);
            }
        }

        // Printing the ordered Nodes (by distance)
        for (Node n : arr) {
            if (n.dist == 0) {
                // Currently if the path is 0 it stays as MAX_INT, so I account for that here*
                System.out.println(n.n + 1 + " dist: " + n.dist + " path: null");
            } else {
                if (n.path > V + 1)
                    // *and here
                    n.path = 0;
                System.out.println((n.n + 1) + " dist: " + n.dist + " path: " + (n.path + 1));
            }

        }
    }
}