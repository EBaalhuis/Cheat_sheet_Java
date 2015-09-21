import java.util.*;

public class Euclid_primes {
	
	//Euclid's algorithm to find gcd.
	public static int gcd(int p, int q) {
		return q == 0 ? p : gcd(q, p % q);
	}
	
	//Extended Euclid's algorithm. 
	//Returns array [d, a, b] such that d = gcd(p, q), ap + bq = d.
	public static int[] extgcd(int p, int q) {
	      if (q == 0)
	         return new int[] { p, 1, 0 };

	      int[] vals = extgcd(q, p % q);
	      int d = vals[0];
	      int a = vals[2];
	      int b = vals[1] - (p / q) * vals[2];
	      return new int[] { d, a, b };
	   }
	
	//Efficient prime factorization of integer n.
	//Returns a list of prime factors.
	public static List<Integer> primeFactors(int n) {
		List<Integer> res = new ArrayList<Integer>();
		while (n%2 == 0) {
			res.add(2);
		}
		for (int i=3; i < Math.sqrt(n); i=i+2) {
			while (n%i == 0) {
	            res.add(i);
	            n = n/i;
	        }
		}
		if (n>2) {
			res.add(n);
		}
		return res;
	}
	
	
}