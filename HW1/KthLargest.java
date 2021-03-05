package git.CSCI261.HW1;
//Java

//Author: Aidan Mellin

public class KthLargest {
	/*
	 * Because no array manipulation is going to be allowed, given that is has a
	 * time complexity of O(log(N))
	 */

	// Structural class
	static class Node {
		int data;
		Node left, right;
	};

	// Counter class to substitute for global variable
	public static class Counter {
		int c = 0;
	}

	// Create a new node with a given data value
	static Node createNode(int data) {
		Node node = new Node();
		node.data = data;
		node.left = node.right = null;
		return node;
	}

	// Insert the node into the tree given a value and tree
	static Node insertNode(Node node, int data) {

		// Base case: If Tree empty, return new Node
		if (node == null)
			return createNode(data);
		if (data < node.data) // Recurse left
			node.left = insertNode(node.left, data);
		else // Recurse right
			node.right = insertNode(node.right, data);
		return node;
	}

	// Find the Kth Largest in a BST
	static int findKthLargest(Node n, int k, Counter count) {

		// Base case: Check c > k as well to account for redundant recurse
		if (n == null || k >= count.c)
			return 0;

		// Reversed traversal through tree to visit largest number first
		findKthLargest(n.right, k, count);

		count.c++; // increment count
		if (count.c == k) // if c is k, kth largest has been found
			return n.data;

		// Recurse left
		findKthLargest(n.left, k, count);

		return 0; // Acount for necessary return statment
	}

	/*
	 * This method creates the tree, inserts all components of given list a and then
	 * finds Kth largest value, and returns it for TestKthLargest
	 */
	public static int kthLargest(int[] a, int k) {
		Node root = null; // Initialize root
		root = insertNode(root, a[0]); // Populate with first value

		for (int i = 1; i < a.length; i++) { // Populate with the rest of the values
			insertNode(root, a[i]);
		}

		Counter count = new Counter(); // Initialize new counter
		int largest = findKthLargest(root, k, count); // The output
		return largest;

	}
}
