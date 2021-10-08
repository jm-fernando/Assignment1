import java.util.Random;
import java.lang.*;

public class SortingMethods {

	//merge and mergeSort functions
	private static void merge(int[] a, int[] left, int[] right, int l, int r) {
		int i = 0;
		int j = 0;
		int k = 0;
		
		while(i < l && j < r) {
			if(left[i] <= right[j]) {
				a[k++] = right[j++];
			} else {
				a[k++] = left[i++];
			}
		}
		
		while(i < l) {
			a[k++] = left[i++];
		}
		while(j < r) {
			a[k++] = right[j++];
		}
	}
	
	public static void mergeSort(int[] a, int n) {
		if(n < 2) {
			return;
		}
		int mid = n / 2;
		int[] left = new int[mid];
		int[] right = new int[n - mid];
		
		for(int i = 0; i < mid; i++) {
			left[i] = a[i];
		}
		for(int i = mid; i < n; i++) {
			right[i - mid] = a[i];
		}
		mergeSort(left, mid);
		mergeSort(right,n - mid);
		
		merge(a, left, right, mid, n - mid);
	}

	//selectionSort function
	public static void selectionSort(int[] a, int n) {

		for(int i = 0; i < n; i++) {
			int index = i;
			for(int j = i + 1; j < n; j++) {
				if(a[j] < a[index]) {
					index = j;
				}
			}
			int smallNum = a[index];
			a[index] = a[i];
			a[i] = smallNum;
		}
	}
	
	//bubbleSort function
	public static void bubbleSort(int[] a, int n) {
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < n - i - 1; j++) {
				if(a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		
	}
	
	//insertionSort function
	public static void insertionSort(int[] a, int n) {
		for(int i = 1; i < n; i++) {
			int temp = a[i];
			int j = i - 1;
			while(j >= 0 && a[j] > temp) {
				a[j + 1] = a[j];
				--j;
			}
			a[j + 1] = temp;
		}
	}
	
	//partition and quickSort functions
	private static int partition(int[] a, int left, int right) {
		int pivot = a[right];
		int i = (left - 1);
		for(int j = left; j <= right - 1; j++) {
			if(a[j] < pivot) {
				i++;
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		int t = a[i + 1];
		a[i + 1] = a[right];
		a[right] = t;
		return(i + 1);
	}
	
	public static void quickSort(int[] a, int left, int right) {
		if(left < right) {
			int p = partition(a, left, right);
			quickSort(a, left, p - 1);
			quickSort(a, p + 1, right);
		}
	}
	
	//test function to see if algorithms work
	public static void main(String[] args) {
		
		/* Printing to test if arrays are sorted properly
		for (int i: a) {
			System.out.println(i + " ");
		}
		*/
		
		/*This part only shows one particular array length and one particular sorting method
		Array lengths and sorting methods are manually swapped out in order to test all values */
		Random rand = new Random();
		int[] a = new int[500000];
		int n = a.length;
		
		for(int i = 0; i < n; i++) {
			a[i] = rand.nextInt();
		}
		
		long start = System.currentTimeMillis();
		quickSort(a, 0, n - 1);
		double end = System.currentTimeMillis();
		double time = end - start;
		System.out.println(time);
	}
	
	
}
	

