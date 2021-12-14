import java.util.Random;

public class ModifiedMerge {
	
	private static void merge(int[] a, int[] b, int l, int r, int end) {
		
		int left = l;
		int right = r;
		int target = l;
		
		while(left < r && right < end) {
			int leftVal = a[left];
			int rightVal = a[right];
			
			if(leftVal <= rightVal) {
				b[target++] = leftVal;
				left++;
			} else {
				b[target++] = rightVal;
				right++;
			}
		}
		
		while(left < r) {
			b[target++] = a[left++];
		}
		
		while(right < end) {
			b[target++] = a[right++];
		}
	}
	
	public static void modSort(int[] a, int n) {
		
		int[] temp = new int[n];
		int[] arr = new int[n + 1];
		int runs = 0;
		
		//Identifying the runs
		arr[0] = 0;
		for(int i = 1; i <= n; i++) {
			if(i == n || a[i] < a[i - 1]) {
				arr[++runs] = i;
			}
		}
		
		//Merging the runs
		int[] firstRun = a;
		int[] nextRun = temp;
		
		while(runs > 1) {
			int newRunCount = 0;
			
			for(int i = 0; i < runs - 1; i += 2) {
				merge(firstRun, nextRun, arr[i], arr[i + 1], arr[i + 2]);
				arr[newRunCount++] = arr[i];
			}
			
			//If runs are odd-numbered, copy last one using arraycopy method
			if(runs % 2 == 1) {
				int i = arr[runs - 1];
				System.arraycopy(firstRun, i, nextRun, i, n - i);
			}
			
			arr[newRunCount] = n;
			runs = newRunCount;
			
			//Swapping runs
			int[] holder = firstRun;
			firstRun = nextRun;
			nextRun = holder;
		}
		
		//If last run is not in 'a' array, copy it there using arraycopy method
		if(firstRun != a) {
			System.arraycopy(firstRun, 0, a, 0, n);
		}
	}
	
	//Utility function to print the array
	public static void printArray(int[] a, int n) {
		for(int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.print("\n");
	}
	
	//Main function
	public static void main(String[] args) {
		
		int[] a = {31, 60, 80, 76, 16, 79, 47, 47, 23, 67};
		int n = a.length;
		
		//Printing array before sorting
		System.out.println("Before: ");
		printArray(a, n);
		
		//Establishing runtime of sorting method in nanoseconds for much more readable results
		double start = System.nanoTime();
		modSort(a, n);
		double end = System.nanoTime();
		double time = end - start;
		
		//Printing out array after sorting
		System.out.println("\nAfter: ");
		printArray(a, n);
		
		//Printing out time in nanoseconds
		System.out.print(time);
		
		//Creating random arrays for further testing
		Random rand = new Random();
		int[] randomArr = new int[10];
		int m = randomArr.length;
		for(int i = 0; i < m; i++) {
			randomArr[i] = rand.nextInt();
		}
		
		double randStart = System.nanoTime();
		modSort(randomArr, m);
		double randEnd = System.nanoTime();
		double randTime = randEnd - randStart;
		System.out.print("\n");
		System.out.print(randTime);
		
		
		
	}
}
