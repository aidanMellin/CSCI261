//Author Aidan Mellin
package git.CSCI261.HW4;

public class DisjHeight extends DisjSets {

    /*
     * performs unions based on tree height, always making the tree with less height
     * the child of the tree with greater height. If the trees have the same height,
     * the second tree becomes the child of the first. The root of a tree is the
     * negative of the tree height – 1.
     * 
     * @param n
     */

    private int sets[];

    DisjHeight(int n) {
        super(n);
        sets = new int[n];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = -1;
        }
    }

    public void union(int r1, int r2) {
        if (sets[r1] <= sets[r2]) {
            if (-sets[r1] < (-sets[r2] + 1))
                sets[r1]--;
            sets[r2] = r1;
        } else {
            if (-sets[r2] < (-sets[r1] + 1))
                sets[r2]--;
            sets[r1] = r2;
        }
    }

    public int find(int node) {
        if (sets[node] <= -1)
            return node;
        else
            return find(sets[node]);
    }

    int size() {
        /* Returns the number of dijoint sets currently in this object */
        int count = 0;
        for (int i : this.sets)
            count = (i < 0) ? count + 1 : count;
        return count;
    }

    public String toString() {
        /*
         * Returns a String representation of a set of disjoint sets. Example: For the
         * following disj sets {{0,1},{2},{3}} if 1 is the child of 0, the String
         * returned would print out as [-1, 0, -1, -1]
         */
        String rtn = "[";
        for (int i = 0; i < sets.length - 1; i++)
            rtn += sets[i] + ", ";
        rtn += sets[sets.length - 1] + "]";
        return rtn;
    }

}
