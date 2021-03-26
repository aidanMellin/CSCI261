package git.CSCI261.HW4;

import java.io.*;
import java.util.*;

import jdk.incubator.foreign.ValueLayout;

public class Kruskal {
    static Edge edgeList[];
    static int vCount, eCount;

    class Sub {
        int root, rank;

        public Sub() {
        }

        public Sub(int i) {
            this.root = i;
            this.rank = 0;
        }
    }

    class Edge implements Comparable<Edge> {
        public int src, dst, weight;

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    Kruskal(int e) {
        eCount = e;
        edgeList = new Edge[eCount];
        for (int i = 0; i < eCount; i++) {
            edgeList[i] = new Edge();
        }
    }

    static void file_process(String fn) {
        try {
            File f = new File(fn);
            BufferedReader fp = new BufferedReader(new FileReader(f));
            String line;
            vCount = Integer.parseInt(fp.readLine());
            int i = 0;
            edgeList = new Edge[vCount * (vCount - 1)];
            for (int j = 0; j < edgeList.length; j++)
                edgeList[j] = new Edge();

            while ((line = fp.readLine()) != null) {
                String splitLine[] = line.split(" ");
                int source = Integer.parseInt(splitLine[0].replaceAll("Node\\[(.*?)\\]:", "$1")) - 1;
                List<String> content = new ArrayList<String>(Arrays.asList(splitLine));
                content.remove(0);
                for (int s = 0; s < content.size(); s++) {
                    String tmp[] = content.get(s).split(":");
                    edgeList[i].src = source;
                    edgeList[i].dst = Integer.parseInt(tmp[0]) - 1;
                    edgeList[i].weight = Integer.parseInt(tmp[1]);
                    i++;
                }
            }
            fp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int findC(Sub set[], int index) { // Find with path compression
        if (set[index].root != index)
            set[index].root = findC(set, set[index].root);
        return set[index].root;
    }

    void union(Sub set[], int r1, int r2) { // Union by rank
        int Root1 = findC(set, r1);
        int Root2 = findC(set, r2);
        if (set[Root1].rank < set[Root2].rank)
            set[Root1].root = Root2;
        else {
            if (set[Root1] == set[Root2])
                set[Root1].rank++;
            set[Root2].root = Root1;
        }
    }

    void quicksort() {
        for (int i = 0; i < edgeList.length - 1; i++) {
            for (int j = 0; j < edgeList.length - 1; j++) {
                if (edgeList[j].weight > edgeList[j + 1].weight) {
                    Edge temp = edgeList[j];
                    edgeList[j] = edgeList[j + 1];
                    edgeList[j + 1] = temp;
                }
            }
        }
    }

    void constructMST() {
        Edge res[] = new Edge[vCount];
        int index = 0;
        int i = 0;
        for (i = 0; i < vCount; i++)
            res[i] = new Edge();
        Arrays.sort(edgeList);

        Sub set[] = new Sub[vCount];
        for (i = 0; i < vCount; i++)
            set[i] = new Sub(i);

        i = 0;
        while (index < vCount - 1) {
            Edge next = new Edge();
            next = edgeList[i++];

            int r1 = findC(set, next.src);
            int r2 = findC(set, next.dst);

            if (r1 != r2) {
                res[index++] = next;
                union(set, r1, r2);
            }

        }
    }

    public static void main(String[] args) {
        String fn = args[0];

        int e = edgeList.length;
        Kruskal krusk = new Kruskal(e);
        file_process(fn);
        krusk.constructMST();
    }

}