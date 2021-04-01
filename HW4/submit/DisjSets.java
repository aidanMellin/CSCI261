//Author Aidan Mellin
public class DisjSets {
    public int[] sets;

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
        return find(sets[node]);
    }

    int findC(int elt) {
        /*
         * Returns the root of the set that this element is in, roots have negative
         * values, performs path compression
         */
        if (sets[elt] < 0)
            return elt;
        else
            return sets[elt] = find(sets[elt]);
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

    int size() {
        /* Returns the number of dijoint sets currently in this object */
        int count = 0;
        for (int i : this.sets)
            count = (i == -1) ? count + 1 : count;
        return count;
    }
}
