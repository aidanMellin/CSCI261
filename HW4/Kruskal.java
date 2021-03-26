package git.CSCI261.HW4;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    private int v1, v2, weight;

    public Edge(int vertex1, int vertex2, int cost) {
        this.v1 = vertex1;
        this.v2 = vertex2;
        this.weight = cost;
    }

    public int getV1() {
        return this.v1;
    }

    public int getV2() {
        return this.v2;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.getWeight() - other.getWeight();
    }
}

public class Kruskal {
    static ArrayList<Edge> edgeList; // TODO
    static int nodeCount; // TODO

    public void minSpanTree() {
        Collections.sort(edgeList);
        ArrayList<Edge> mstEdges = new ArrayList<Edge>();
        DisjSets nodeSet = new DisjSets(nodeCount);

        for (int i = 0; i < edgeList.size() && mstEdges.size() < (nodeCount); i++) {
            Edge curr = edgeList.get(i);
            int r1 = nodeSet.find(curr.getV1());
            int r2 = nodeSet.find(curr.getV2());
            if (r1 != r2) {
                mstEdges.add(curr);
                nodeSet.union(r1, r2);
            }
        }
    }

    static ArrayList<Edge> file_process(String fn) {
        ArrayList<Edge> rtn = new ArrayList<Edge>();
        try {
            File f = new File(fn);
            BufferedReader fp = new BufferedReader(new FileReader(f));
            String line;
            nodeCount = Integer.parseInt(fp.readLine());
            System.out.println(nodeCount);
            while ((line = fp.readLine()) != null) {
                String splitLine[] = line.split(" ");
                int src = Integer.parseInt(splitLine[0].replaceAll("Node\\[(.*?)\\]:", "$1")) - 1;
                List<String> content = new ArrayList<String>(Arrays.asList(splitLine));
                content.remove(0);
                for (String s : content) {
                    String tmp[] = s.split(":");
                    rtn.add(new Edge(Integer.parseInt(tmp[1]), src, Integer.parseInt(tmp[0]) - 1));
                }
            }
            fp.close();
            return rtn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static void main(String[] args) {
        Kruskal krusk = new Kruskal();
        String fn = args[0];
        ArrayList<Edge> edges = file_process(fn);
        krusk.edgeList = edges;
        krusk.minSpanTree();
    }

}