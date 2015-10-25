package NumberTheory;

import java.util.Scanner;

public class Modular_Inverse {

	// Compute inverse of a in Z/pZ, with p prime.
	// I.e. x such that ax = 1 (mod p).
	static int modInvPrime(int a, int p) {
		return (int) Math.pow(a, p - 2) % p;
	}

	// Compute inverse of a in Z/rZ, with a and r co-prime.
	// If they are not co-prime, inverse does not exist.
	static int modInv(int a, int r) {
		return (extgcd(a, r)[1] + r) % r;
	}

	// extgcd method only for testing, required by modInv.
	static int[] extgcd(int p, int q) {
		if (q == 0) {
			return new int[] { Math.abs(p), Integer.signum(p), 0 };
		}
		int[] vals = extgcd(q, p % q);
		int d = vals[0];
		int a = vals[2];
		int b = vals[1] - (p / q) * vals[2];
		return new int[] { d, a, b };
	}
	
	// main method only for testing.
			public static void main(String[] args) {
				Scanner in = new Scanner(System.in);
				int a = in.nextInt();
				int r = in.nextInt();
				System.out.println(modInv(a, r));
			}
}
