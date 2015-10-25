package NumberTheory;

import java.util.Scanner;

public class Fast_Exponentiation {

	// Fast exponentiation of a^b.
	static long exp(long a, long b) {
		if (b == 1) {
			return a;
		}
		if (b == 2) {
			return a*a;
		}
		if (b% 2 == 0) {
			return exp(exp(a,b/2),2);
		} else {
			return a*exp(exp(a,(b-1)/2),2);
		}
	}
	
	// Fast exponentiation of a^b (mod r).
	static long expMod(long a, long b, long r) {
		if (b == 1) {
			return a % r;
		}
		if (b == 2) {
			return a*a % r;
		}
		if (b% 2 == 0) {
			return exp(exp(a,b/2),2) % r;
		} else {
			return a*exp(exp(a,(b-1)/2),2) % r;
		}
	}

	// main method only for testing.
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		
		long time1 = System.nanoTime();
		long e = exp(a, b);
		long time2 = System.nanoTime();
		System.out.printf("Used exp: result %d in %d nanosec\n", e, time2-time1);
		
		long time5 = System.nanoTime();
		long e3 = expMod(a, b, 1000);
		long time6 = System.nanoTime();
		System.out.printf("Used expMod: result %d in %d nanosec\n", e3, time6-time5);
		
		long time3 = System.nanoTime();
		long e2 = (long) Math.pow(a,b);
		long time4 = System.nanoTime();
		System.out.printf("Used Math.pow: result %d in %d nanosec\n", e2, time4-time3);
	}
}