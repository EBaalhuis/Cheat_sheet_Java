package NumberTheory;

public class Euclid_gcd {

	// Euclid's algorithm to find gcd.
	static int gcd(int p, int q) {
		return q == 0 ? Math.abs(p) : gcd(q, p % q);
	}

	// Extended Euclid's algorithm.
	// Returns array [d, a, b] such that d = gcd(p, q), ap + bq = d.
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
}