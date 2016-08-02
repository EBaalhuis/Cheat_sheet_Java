package NumberTheory;

import java.math.BigInteger;
import java.util.Scanner;

public class Combinations {

	// Calculate n choose k, also knows as n nCr k, or n above k
	// Works for n <= 10 000.
	static BigInteger ncr(int n, int k) {
		k = k > n / 2 ? n - k : k;
		BigInteger a = BigInteger.ONE;
		for (int i = 1; i < k + 1; i++) {
			a = a.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
		}
		return a;
	}
	
	// Calculate the n-th Catalan number
	static BigInteger cat(int n) {
		return ncr(2*n,n).divide(BigInteger.valueOf(n+1));
	}

	// main method only for testing
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		System.out.println(ncr(n, k));
	}
}
