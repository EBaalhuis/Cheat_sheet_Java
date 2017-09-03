package NumberTheory;
import java.io.*;
import java.util.*;

public class FastMatrixExp {

	// Fast matrix exponentiation (must be square) O(l^3 * log(n))
	public static double[][] fme(double[][] a, long n, long mod) {
		int l = a.length;
		double[][] res = new double[l][l];
		for (int i = 0; i < l; i++) {
			res[i][i] = 1;
		}
		
		String bin = Long.toBinaryString(n);
		int exp = 0;
		while (Math.pow(2, exp) <= n) {
			if (bin.charAt(bin.length()-1-exp) == '1') {
				res = mult(res, a, mod);
			}
			a = mult(a,a,mod);
			exp++;
		}
		
		return res;
	}
	
	// Multiply 2 matrices
	public static double[][] mult(double[][] a, double[][] b, long mod) {
		int k = a.length;
		int n = a[0].length;
		int m = b[0].length;
		double[][] res = new double[k][m];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < m; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					res[i][j] = (res[i][j] + (a[i][j2] * b[j2][j])) % mod; 
				}
			}
		}
		return res;
	}
	
	// Print function for testing
	public static void printMatrix(double[][] a) {
		int k = a.length;;
		int n = a[0].length;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(a[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	// Driver function for testing
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		double[][] a = new double[2][3];
		a[0][1] = 1;
		a[0][2] = 2;
		a[1][0] = 1;
		a[1][1] = 2;
		a[1][2] = 3;

		double[][] b = new double[3][1];
		b[0][0] = 5;
		b[1][0] = 6;
		b[2][0] = 7;
		
		double[][] c = mult(a,b,10000);
		
		printMatrix(c);
		
		io.close();
	}

	// IO for testing
	static class IO extends PrintWriter {
		static BufferedReader r;
		static StringTokenizer t;

		public IO(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
			t = new StringTokenizer("");
		}

		public String next() throws IOException {
			while (!t.hasMoreTokens()) {
				t = new StringTokenizer(r.readLine());
			}
			return t.nextToken();
		}

		public int nextInt() throws IOException{
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}