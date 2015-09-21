// Efficient prime factorization of integer n.
// Returns a list of prime factors.
package NumberTheory;
import java.util.*;

public class PrimeDecomposition {
	
	public static List<Integer> primeFactors(int a) {
		List<Integer> res = new ArrayList<Integer>();
		while (a % 2 == 0) {
			res.add(2);
			a = a / 2;
		}
		for (int i = 3; i < Math.sqrt(a); i = i + 2) {
			while (a % i == 0) {
				res.add(i);
				a = a / i;
			}
		}
		if (a > 2) {
			res.add(a);
		}
		return res;
	}
}