package Combinatorics;

import java.util.Scanner;

public class Combinations {

	// Calculate n choose k, also knows as nCr(n,k)
	// Beware that n > 66 can give long overflow!
	static long ncr(int n, int k) {
		k = k > n / 2 ? n - k : k;
		long[] a = new long[k + 1];
		a[0] = 1;
		for (int i = 1; i < a.length; i++) {
			a[i] = (a[i - 1] * (n - i + 1) / i);
		}
		return a[k];
	}
	
	// main method only for testing
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			int k = in.nextInt();
			System.out.println(ncr(n, k));
		}
}
