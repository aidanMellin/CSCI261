package git.CSCI261.HW4;

import java.io.*;

public class teste2 {

	public static void main(String[] args) {

		PrintStream ps = new PrintStream(System.out);

		ps.println("Make 10 disjoint sets");
		DisjSets d = new DisjSets(10);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 7 and 3");
		d.union(7, 3);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 7 and 6");
		d.union(7, 6);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 7 and 0");
		d.union(7, 0);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 3 and 1");
		d.union(3, 1);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 0 and 4");
		d.union(0, 4);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 4 and 9");
		d.union(4, 9);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 9 and 5");
		d.union(9, 5);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Find root of tree 9 is in");
		System.out.println(d.find(3));
		ps.println(d);

		ps.println("Find root of tree 9 is in do path compression");
		System.out.println(d.findC(3));
		ps.println(d);

	}
}
