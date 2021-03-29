import java.util.*;
import java.io.*;

public class Inversions {
    
    public static long inversions(int a[]){
        mergeSort(a, 0, a.length-1);
        return 0;
    }

	public static void merge(int a[], int sA[], int eA[], int sSize, int eSize) {
		int i = 0, sIndex = 0, eIndex = 0, count = 0; // Establish indices
		while (sIndex < sSize && eIndex < eSize) {
			if (sA[sIndex] < eA[eIndex]){
				a[i++] = sA[sIndex++];
                count+=1;
            }
			else{
				a[i++] = eA[eIndex++];
                count+=1;
		    }
        }
		// Add the remaining values (if any)
		while (sIndex < sSize){
			a[i++] = sA[sIndex++];
            count+=1;
        }
		while (eIndex < eSize){
			a[i++] = eA[eIndex++];
            count+=1;
        }
        System.out.println(count);
	}

	public static void mergeSort(int[] a, int start, int end) {
		if (a.length < 2) {return;}
		int mid = a.length / 2; // Find the middle of the array
		int startA[] = new int[mid]; // Left part of array (Start -> mid)
		int endA[] = new int[a.length - mid]; // Right part of array (Mid+1 -> end)
		int mergeIndex = 0; // The new index for the values
		for (int i = 0; i < a.length; i++) {
			// Asign values into new arrays based around value of mid
			if (i < mid)
				startA[i] = a[i];
			else {
				endA[mergeIndex] = a[i];
				mergeIndex += 1;
			}
		}
		// Recursively call until there are two values per arr being compared and then
		// merge the result
		mergeSort(startA, start, mid);
		mergeSort(endA, mid + 1, end);
		merge(a, startA, endA, mid, a.length - mid);


	}

}
