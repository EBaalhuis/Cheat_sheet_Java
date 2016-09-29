package NumberTheory;

import java.util.*;

public class EulerPhi {
	
	static long phi(long n) {
		double res = n;
		// Use Set instead of List!
		HashSet<Long> factors = primeFactors(n);
		for (long f : factors) {
			res *= (1.-1./f);
		}
		return Math.round(res);
	}
	
	// Required
	static HashSet<Long> primeFactors(long a) {
		HashSet<Long> res = new HashSet<Long>();
		while (a % 2 == 0) {
			res.add((long) 2);
			a = a / 2;
		}
		// Careful! Loop variable is a long
		for (long i = 3; i <= Math.sqrt(a); i = i + 2) {
			while (a % i == 0) {
				res.add(i);
				a = a / i;
			}
		}
		if (a > 2) {
			res.add((long) a);
		}
		return res;
	}
}
