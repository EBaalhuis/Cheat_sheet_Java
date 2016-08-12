package NumberTheory;

import java.math.BigInteger;
import java.util.Scanner;

public class Faculty {

	// Calculate n! Works for n <= 5 000.
	static BigInteger fac(int n) {
		BigInteger a = BigInteger.ONE;
		for (int i = 2; i < n + 1; i++) {
			a = a.multiply(BigInteger.valueOf(i));
		}
		return a;
	}

	// main method only for testing
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(fac(n));
	}
}
