public class Inversions {
    
    public static long inversions(int a[]){
		int tmp[] = new int[a.length];
        return mergeSort(a, tmp, 0, a.length-1);
    }

	public static int merge(int a[], int tmp[], int start, int mid, int end) {
		int i, j, k;
		int inv_count = 0;
		i = start; /* i is index for start subarray*/
        j = mid; /* j is index for end subarray*/
        k = start; /* k is index for resultant merged subarray*/
        while ((i <= mid - 1) && (j <= end)) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            }
            else {
                tmp[k++] = a[j++];
  
                /*this is tricky -- see above explanation/diagram for merge()*/
                inv_count = inv_count + (mid - i);
            }
        }
  
        /* Copy the remaining elements of start subarray
       (if there are any) to tmp*/
        while (i <= mid - 1)
            tmp[k++] = a[i++];
  
        /* Copy the remaining elements of end subarray
       (if there are any) to tmp*/
        while (j <= end)
            tmp[k++] = a[j++];
  
        /*Copy back the merged elements to original aay*/
        for (i = start; i <= end; i++)
            a[i] = tmp[i];
  
        return inv_count;
	}

	public static int mergeSort(int[] a, int tmp[], int start, int end) {
		int mid, inv_count = 0;
		if(start < end){
			mid = (start + end) / 2;
			inv_count = mergeSort(a, tmp, start, mid);
			inv_count += mergeSort(a, tmp, mid+1, end);

			inv_count += merge(a, tmp, start, mid+1, end);
		}
		return inv_count;
	}

}
