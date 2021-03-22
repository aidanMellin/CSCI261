package git.CSCI261.HW4;

public class DisjSets {
    private int[] sets;

    public DisjSets(int n) {
        sets = new int[n];
        for (int i = 0; i < sets.length; i++)
            sets[i] = -1;
    }

    public void union(int r1, int r2) {
        sets[r2] = r1;
    }

    public int find(int node) {
        if (sets[node] < 0)
            return node;
        else
            return find(sets[node]);
    }

    int findC(int elt) {
        /*
         * Returns the root of the set that this element is in, roots have negative
         * values, performs path compression
         */
        return this.sets[elt];
    }

    public String toString() {
        /*
         * Returns a String representation of a set of disjoint sets. Example: For the
         * following disj sets {{0,1},{2},{3}} if 1 is the child of 0, the String
         * returned would print out as [-1, 0, -1, -1]
         */
        String rtn = "[";
        for (int i = 0; i < sets.length - 1; i++)
            rtn += sets[i] + ",";
        rtn += sets[sets.length - 1] + "]";
        return rtn;
    }

    int size() {
        /* Returns the number of dijoint sets currently in this object */
        return this.sets.length;
    }

    // public static void main(String[] args) {
    // int n = 5;
    // DisjSets dis = new DisjSets(n);
    // dis.union(0, 2);
    // dis.union(4, 2);
    // dis.union(3, 1);
    // if (dis.find(4) == dis.find(0))
    // System.out.println("yes");
    // else
    // System.out.println("No");
    // if (dis.find(1) == dis.find(0))
    // System.out.println(true);
    // else
    // System.out.println(false);
    // }

}
