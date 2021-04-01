import java.util.*;
import java.io.*;

public class MergeSort {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(args[0]));
		// get the size of the array
		int n = sc.nextInt();

		// construct array and fill
		int[] a = new int[n];

		for (int i = 0; i < n; i++)
			a[i] = sc.nextInt();

		//Create a blank tmp array for merging values (same size as a)
		int tmp[] = new int[a.length];
		
		// mergeSort the array
		mergeSort(a,tmp, 0, a.length - 1);
		
		// if array length < 20, print it
		if (n < 20)
			System.out.println(Arrays.toString(a));
		// else for longer arrays, check the ordering
		else {
			for (int i = 0; i < a.length - 1; i++)
				if (a[i] > a[i + 1])
					System.out.println("UNSORTED");
			System.out.println("If working, this is the only output.");
		}
	}

	public static void merge(int a[], int tmp[], int start, int mid, int end) {
		int i, j, k;
		i = start; /*Index for start subarray (* -> mid)*/
        j = mid; /* Index for end subarray (mid + 1 -> * )*/
        k = start; /* Index for merged arr*/
        while ((i <= mid - 1) && (j <= end)) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            }
            else
                tmp[k++] = a[j++];
        }
  
        // Copy remaining start values
        while (i <= mid - 1)
            tmp[k++] = a[i++];
  
        // Copy remaining End values
        while (j <= end)
            tmp[k++] = a[j++];
  
		// Copy all back into original array
		for (i = start; i <= end; i++)
            a[i] = tmp[i];
  
	}

	public static int mergeSort(int[] a, int tmp[], int start, int end) {
		int mid, inv_count = 0;

		// Determine mid value
		if(start < end){
			mid = (start + end) / 2;

		// Call mergeSort on * -> mid
		mergeSort(a, tmp, start, mid);

		// Call mergeSort on mid + 1 -> * 
		mergeSort(a, tmp, mid+1, end);

		// Merge the values found
		merge(a, tmp, start, mid+1, end);
		}
		return inv_count;
	}
}
