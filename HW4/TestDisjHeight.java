package git.CSCI261.HW4;

import java.util.*;
import java.io.*;

public class TestDisjHeight {

	public static void main(String[] args) {

		PrintStream ps = new PrintStream(System.out);

		ps.println("Make 6 disjoint sets");
		DisjSets d = new DisjHeight(6);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 4 and 5");
		d.union(4, 5);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 2 and 3");
		d.union(2, 3);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 0 and 1");
		d.union(0, 1);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 2 and 4 after finding root 4 on find(5)");
		d.union(2, d.find(5));
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 0 and 2");
		d.union(0, 2);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

	}
}
