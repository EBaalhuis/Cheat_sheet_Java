package NumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class Sieve {

	// return ArrayList<Integer> of all primes <= n.
	static ArrayList<Integer> sieve(int n) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		boolean[] notPrime = new boolean[n + 1];
		for (int i = 2; i <= n; i++) {
			if (!notPrime[i]) {
				res.add(i);
				for (int j = 2; j * i <= n; j++) {
					notPrime[i * j] = true;
				}
			}
		}
		return res;
	}

	// return BitSet(n+1) a where a.get(b) == true iff b is NOT prime.
	// !! boolean wrong way around to be faster !!
	// to get number of primes <= n: n - sieve2(n).cardinality();
	static BitSet sieve2(int n) {
		BitSet res = new BitSet(n + 1);
		res.set(1);
		for (int i = 2; i * i <= n; i++) {
			if (!res.get(i)) {
				for (int j = 2; j * i <= n; j++) {
					res.set(i * j);
				}
			}
		}
		return res;
	}
}
