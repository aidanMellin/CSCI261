package git.CSCI261.HW4;

public class DisjSize extends DisjSets {
    private int[] sets;

    DisjSize(int n) {
        super(n);
        sets = new int[n];
        for (int i = 0; i < sets.length; i++)
            sets[i] = -1;
    }

    public void union(int r1, int r2) {
        if (sets[r2] < sets[r1])
            sets[r1] = r2;
        else {
            if (sets[r1] == sets[r2])
                sets[r1]--;
            sets[r2] = r1;
        }
    }

}
