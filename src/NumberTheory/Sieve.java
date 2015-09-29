package NumberTheory;

import java.util.ArrayList;

public class Sieve {

	// return ArrayList<Integer> of all primes <= n.
	public static ArrayList<Integer> sieve(int n) {
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

	// return boolean[n+1] a where a[b] = true iff b is prime.
	public static boolean[] sieve2(int n) {
		boolean[] res = new boolean[n + 1];
		for (int i = 2; i < res.length; i++) {
			res[i] = true;
		}
		for (int i = 2; i * i <= n; i++) {
			if (res[i]) {
				for (int j = 2; j * i <= n; j++) {
					res[i * j] = false;
				}
			}
		}
		return res;

	}
}
