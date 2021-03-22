package git.CSCI261.HW4;

import java.util.*;

public class TestDS {
	public static void main(String[] args) {
		// get the number of sets
		int n = Integer.parseInt(args[0]);

		// set up the disjoint sets
		System.out.println("Testing NO path compression...");
		DisjSets dSt = new DisjSets(n);
		DisjSets dHt = new DisjHeight(n);
		DisjSets dSz = new DisjSize(n);

		testDS(n, dSt, "Testing Disjoint Sets");
		testDS(n, dHt, "Testing Disjoint Sets by Height");
		testDS(n, dSz, "Testing Disjoint Sets by Size");

		System.out.println("\nTesting WITH path compression...");
		dSt = new DisjSets(n);
		dHt = new DisjHeight(n);
		dSz = new DisjSize(n);

		testDSPC(n, dSt, "Testing Disjoint Sets");
		testDSPC(n, dHt, "Testing Disjoint Sets by Height");
		testDSPC(n, dSz, "Testing Disjoint Sets by Size");

	}

	// tests 3 union policies on disjoint sets
	public static void testDS(int n, DisjSets d, String label) {
		// They should all join disjoint sets and get to size 1 at the
		// same time, if not ...
		// randomize
		int unions = 0;
		Random rand = new Random();
		rand.setSeed(55);
		System.out.println("\n" + label);
		if (n <= 8)
			System.out.println("Disjoint Sets: " + d);
		try {
			long start = System.currentTimeMillis();
			while (d.size() > 1) {
				int elt1 = rand.nextInt(n);
				int elt2 = rand.nextInt(n);
				int set1 = d.find(elt1);
				int set2 = d.find(elt2);
				if (set1 != set2) {
					unions++;
					d.union(set1, set2);
				}
			}
			long stop = System.currentTimeMillis();
			System.out.println("Unions = " + unions);
			if (n <= 8)
				System.out.println("Disjoint Sets: " + d);
			if (n > 1000)
				System.out.println("Time = " + (stop - start));
		} catch (Exception e) {
			System.out.println("Crashed");
			System.out.println(e);
		}
	}

	// tests 3 union policies along with path compression on disjoint sets
	public static void testDSPC(int n, DisjSets d, String label) {
		// They should all join disjoint sets and get to size 1 at the
		// same time, if not ...
		// randomize
		int unions = 0;
		Random rand = new Random();
		rand.setSeed(55);
		System.out.println("\n" + label);
		if (n <= 8)
			System.out.println("Disjoint Sets: " + d);
		try {
			long start = System.currentTimeMillis();
			while (d.size() > 1) {
				int elt1 = rand.nextInt(n);
				int elt2 = rand.nextInt(n);
				int set1 = d.findC(elt1);
				int set2 = d.findC(elt2);
				if (set1 != set2) {
					unions++;
					d.union(set1, set2);
				}
			}
			long stop = System.currentTimeMillis();
			System.out.println("Unions = " + unions);
			if (n <= 8)
				System.out.println("Disjoint Sets: " + d);
			if (n > 1000)
				System.out.println("Time = " + (stop - start));
		} catch (Exception e) {
			System.out.println("Crashed");
			System.out.println(e);
		}
	}

}
