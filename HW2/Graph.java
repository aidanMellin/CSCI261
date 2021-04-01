package git.CSCI261.HW2;

import git.CSCI261.HW2.Node;
//These Will have to be commented out ^^^^ and uncomment below
//import Node

import java.io.*;
import java.util.*;

public class Graph {
    /*
     * Read from file, assume undirected graph. Add all connections between nodes to
     * each adj (adjacency list) After adj matrix has been established, layers can
     * be determined by following from Node 1 on (ie Node[n+1].layer = Node[n].layer
     * + 1)
     */
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            BufferedReader fp = new BufferedReader(new FileReader(file));
            int config_size = Integer.parseInt(fp.readLine());
            String line;
            ArrayList<Node> nodeList = new ArrayList<Node>();
            Node root = null;
            HashMap<Integer, Node> nodeHash = new HashMap<Integer, Node>();

            while ((line = fp.readLine()) != null) {
                /*
                 * Read the lines (not the first).
                 */
                int id = (line.split(" ")[0].charAt(5)) - '0';
                Node tmpNode = new Node(id);
                Set<Node> tmpAdj = new LinkedHashSet<Node>();

                for (String item : line.split(" ")) {
                    if (!item.contains("Node[")) {
                        Node itemNode = new Node(Integer.parseInt(item));
                        tmpNode.addEdge(itemNode);
                        nodeHash.replace(tmpNode.id, tmpNode);
                        if (nodeHash.containsKey(itemNode)) {
                            nodeHash.replace(itemNode.id, itemNode);
                        } else {
                            nodeHash.put(itemNode.id, itemNode);
                        }
                    }
                }
            }

            Iterator itr = nodeHash.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry item = (Map.Entry) itr.next();
                System.out.println(item.getKey() + " = " + item.getValue());
            }

            fp.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
