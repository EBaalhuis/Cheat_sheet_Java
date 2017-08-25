package NumberTheory;

public class FastExponentiation {
	static long modpow(long x, long n, long mod) {
		if (n == 0)
			return 1;
		long y = 1;
		while (n > 1) {
			if (n % 2 == 0) {
				x = (x * x) % mod;
				n = n / 2;
			} else {
				y = (x * y) % mod;
				x = (x * x) % mod;
				n = (n - 1) / 2;
			}
		}
		return (x * y) % mod;
	}
}