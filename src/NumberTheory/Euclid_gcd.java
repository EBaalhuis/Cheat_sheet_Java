package NumberTheory;

public class Euclid_gcd {
	
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
}