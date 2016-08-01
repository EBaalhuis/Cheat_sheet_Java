package NumberTheory;

public class Simultaneous_Cong {
	
	// Input is modular equations x = r[i] mod m[i].
	// The m[i] must be rel. prime. Returns x.
	static long SC(long[] r, long[] mods) {
		int n = r.length;
		long p = 1;
		for (int i = 0; i < n; i++) {
			p *= mods[i];
		}
		long[] m = new long[n];
		for (int i = 0; i < n; i++) {
			m[i] = p / mods[i];
		}
		
		long[] N = new long[n];
		for (int i = 0; i < n; i++) {
			N[i] = (r[i] * modInv(m[i], mods[i])) % mods[i];
		}
		
		long x = 0;
		for (int i = 0; i < n; i++) {
			x += m[i] * N[i];
		}
		
		return x % p;
	}
	
	// Required by SC
	static long modInv(long a, long r) {
		if (extgcd(a, r)[0] != 1) {
			return -1;
		}
		return (extgcd(a, r)[1] + r) % r;
	}

	static long[] extgcd(long p, long q) {
		if (q == 0) {
			return new long[] { Math.abs(p), Long.signum(p), 0 };
		}
		long[] vals = extgcd(q, p % q);
		long d = vals[0];
		long a = vals[2];
		long b = vals[1] - (p / q) * vals[2];
		return new long[] { d, a, b };
	}
	
	public static void main(String[] args) {
		long[] r = new long[] {3, 2, 1};
		long[] mods = new long[] {7, 5, 3};
		
		long x = SC(r, mods);
		System.out.println(x);
	}
	
}
