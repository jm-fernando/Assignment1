
public class ModifiedMerge {
	
	static int RUN = 32;
	public static int minRunLength(int n) {
		assert n >= 0;
		int r = 0;
		while(n >= RUN) {
			r |= (n & 1);
			n >>= 1;
		}
		return n + r;
	}
	
	public static void sort(int[] a, int l, int r) {
		for(int i = l + 1; i <= r; i++) {
			int temp = a[i];
			int j = i - 1;
			while(a[j] > temp && j >= l) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = temp;
		}
	}
	
	public static void merge(int[] a, int l, int m, int r) {
		int len1 = m - l + 1;
		int len2 = r - m;
		int[] left = new int[len1];
		int[] right = new int[len2];
		
		for(int x = 0; x < len1; x++) {
			left[x] = a[l + x];
		}
		for(int x = 0; x < len2; x++) {
			right[x] = a[m + 1 + x];
		}
		
		int i = 0;
		int j = 0;
		int k = l;
		while(i < len1 && j < len2) {
			if(left[i] <= right[j]) {
				a[k] = left[i];
				i++;
			} else {
				a[k] = right[j];
				j++;
			}
			k++;
		}
		while(i < len1) {
			a[k] = left[i];
			k++;
			i++;
		}
		while(j < len2) {
			a[k] = right[j];
			k++;
			j++;
		}
		
	}
	
	public static void modSort(int[] a, int n) {
		int minRun = minRunLength(RUN);
		
		for(int i = 0; i < n; i += minRun) {
			sort(a, i, Math.min((i + RUN - 1), (n - 1)));
		}
		for(int size = minRun; size < n; size = 2 * size) {
			for(int l = 0; l < n; l += 2 * size) {
				int m = l + size - 1;
				int r = Math.min((l + 2 * size - 1), (n - 1));
				merge(a, l, m, r);
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[] a = {31, 60, 80, 76, 16, 79};
		int n = a.length;
		modSort(a, n);
		
		for (int i: a) {
			System.out.println(i + " ");
		}
	}
	
}
