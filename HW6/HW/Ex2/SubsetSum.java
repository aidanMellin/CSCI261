import java.util.*;
import java.io.*;

public class SubsetSum {
    public static int[][] M;

    public static void main(String[] args) throws FileNotFoundException {
        // read input file
        Scanner sc = new Scanner(new File(args[0]));
        int W = Integer.parseInt(args[1]);
        int n = Integer.parseInt(sc.nextLine());
        int[] itemWts = new int[n + 1];
        itemWts[0] = 0;
        for (int i = 1; i <= n; i++)
            itemWts[i] = Integer.parseInt(sc.nextLine());

        long start = System.currentTimeMillis();
        int maxWeight = subsetSumMem(itemWts, W);
        long stop = System.currentTimeMillis();
        System.out.println("Memoized: Max Weight for " + n + " items = " + maxWeight);
        System.out.println("Time = " + (stop - start));
        System.out.println("\nSolution");
        showSolution(itemWts, W, itemWts.length - 1);

        start = System.currentTimeMillis();
        maxWeight = subsetSumR(itemWts, W, n);
        stop = System.currentTimeMillis();
        System.out.println("\nRecursive: Max Weight for " + n + " items = " + maxWeight);
        System.out.println("Time = " + (stop - start));
    }

    /**
     *
     * @ param int [] itemWts - an array of the weights of items, itemWts[i] is the
     * weight of item i int W - the capacity allowed @ return int - maximized sum of
     * weights from itemWts <= W that is compatible with jobs[j]
     */
    public static int subsetSumMem(int[] itemWts, int W) {
        int n = itemWts.length;
        M = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++)
            M[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (itemWts[i - 1] > j)
                    M[i][j] = M[i - 1][j];
                else
                    // Math.max(M[i - 1][j], M[i - 1][j - itemWts[i - 1]])
                    M[i][j] = (M[i - 1][j] == 1) ? 1 : (M[i - 1][j - itemWts[i - 1]] == 1) ? 1 : 0;
            }
        }
        return (M[n][W] == 1) ? W : 0;
    }

    /**
     *
     * @param int[] itemWts - an array of the weights of items, itemWts[i] is the
     *              weight of item i
     * @param int   w - the current capacity allowed
     * @param int   i - index of item under consideration
     * @return int - maximized sum of weights from itemWts <= W that is compatible
     *         with jobs[j]
     */
    public static int subsetSumR(int[] itemWts, int w, int i) {
        return 0;
    }

    /**
     *
     * @ param int [] itemWts - an array of the weights of items, itemWts[i] is the
     * weight of item i
     * 
     * @param int w - the current capacity allowed
     * @param int i - index of item under consideration
     * @return int - maximized sum of weights from itemWts <= W that is compatible
     *         with jobs[j]
     */
    public static void showSolution(int[] itemWts, int w, int i) {
        System.out.println((w > 0 && i > 0 && (M[i][w] == 1)) ? String.format("item %d wt: %d", i, itemWts[i]) : "");
        if (w - itemWts[i] > 0 && i - 1 > 0)
            showSolution(itemWts, w - itemWts[i], i - 1);
    }

}
