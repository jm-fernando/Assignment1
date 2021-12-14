public class ModifiedMerge {

    static int RUN = 32;
 
    static int min(int n)
    {
        assert n >= 0;
        int r = 0;
        while (n >= RUN)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
 
    //Insertion sort function to be called later
    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
 
    //Merge sort function that merges the sorted runs, to be called later
    public static void merge(int[] arr, int left, int mid, int right) {
    	
        int len1 = mid - left + 1;
        int len2 = right - mid;
        
        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];
        
        for (int x = 0; x < len1; x++) {
            leftArr[x] = arr[left + x];
        }
        
        for (int x = 0; x < len2; x++) {
            rightArr[x] = arr[mid + 1 + x];
        }
 
        int i = 0;
        int j = 0;
        int k = left;
 
        //Merging the two runs into a larger run
        while (i < len1 && j < len2) {
        	
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            }
            
            else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
 
        // Copy remaining element/s of left run (if there are any left)
        while (i < len1) {
            arr[k] = leftArr[i];
            k++;
            i++;
        }
 
        //Copy remaining element/s of right run (if there are any left)
        while (j < len2) {
            arr[k] = rightArr[j];
            k++;
            j++;
        }
    }
 
    //modSort function to sort the array
    public static void modSort(int[] arr, int n) {

        int minRun = min(RUN);
       
        // Sort individual sub-arrays of size RUN
        for (int i = 0; i < n; i += minRun)
        {
            insertionSort(arr, i,
                          Math.min((i + RUN - 1), (n - 1)));
        }
 
        //Merging from size RUN
        for (int size = minRun; size < n; size = 2 * size) {
 
            for (int left = 0; left < n; left += 2 * size) {
            	
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
 
                  if(mid < right) {
                    merge(arr, left, mid, right);
                  }
            }
        }
    }
    
    //Original merge sort function included for running time testing
    public static void origMergeSort(int arr[], int l, int r) {
    	
        if (l < r) {
            int m = (l + r) / 2;
 
            // Sort first and second halves
            origMergeSort(arr, l, m);
            origMergeSort(arr , m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
 
    //Utility function to print the Array
    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }
    
    //Main
    public static void main(String[] args) {
		
		int[] a = {31, 60, 80, 76, 16, 79, 47, 47, 23, 67};
		int n = a.length;
		
		//Testing running time for the modSort fnc using given array
		System.out.println("Before: ");
		printArray(a, n);
		long msStart = System.nanoTime();
		modSort(a, n);
		double msEnd = System.nanoTime();
		double msTime = msEnd - msStart;
		System.out.println("\nAfter: ");
		printArray(a, n);
		System.out.println(msTime);
	}
}
