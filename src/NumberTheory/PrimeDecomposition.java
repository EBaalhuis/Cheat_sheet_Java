// Efficient prime factorization of integer n.
// Returns a list of prime factors.
package NumberTheory;

import java.util.*;

public class PrimeDecomposition {

	static HashMap<Long, Integer> primeFactors(long a) {
		HashMap<Long, Integer> res = new HashMap<Long, Integer>();
		while (a % 2 == 0) {
			res.put(2L, res.get(2L) == null ? 1 : res.get(2L) + 1);
			a = a / 2;
		}
		// Careful! Loop variable is a long
		for (long i = 3; i * i <= a; i = i + 2) {
			while (a % i == 0) {
				res.put(i, res.get(i) == null ? 1 : res.get(i) + 1);
				a = a / i;
			}
		}
		if (a > 2) {
			res.put(a, res.get(a) == null ? 1 : res.get(a) + 1);
		}
		return res;
	}

}