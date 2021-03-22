package git.CSCI261.HW4;

import java.util.*;
import java.io.*;

public class TestDisjSets {

	public static void main(String[] args) {

		PrintStream ps = new PrintStream(System.out);

		ps.println("Make 4 disjoint sets");
		DisjSets d = new DisjSets(4);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 0 and 1");
		d.union(0, 1);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 2 and 3");
		d.union(2, 3);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Union 0 and 2");
		d.union(0, 2);
		ps.println(d);

		ps.println("Number of disjoint sets");
		ps.println(d.size());

		ps.println("Find root of tree 3 is in");
		System.out.println(d.find(3));
		ps.println(d);

		ps.println("Find root of tree 3 is in do path compression");
		System.out.println(d.findC(3));
		ps.println(d);

	}
}
