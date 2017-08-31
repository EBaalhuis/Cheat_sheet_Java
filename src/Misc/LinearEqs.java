package Misc;

import java.io.*;
import java.util.*;

public class LinearEqs {

	static double EPS = 0.0000001;

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		double[][] A = new double[3][3];
		double[] b = new double[] { 3, 6, 2 };

		A[0][0] = 1;
		A[0][1] = 5;
		A[0][2] = 3;
		
		A[1][0] = 8;
		A[1][1] = 2;
		A[1][2] = 4;
		
		A[2][0] = 9;
		A[2][1] = 6;
		A[2][2] = 7;

		double[] x = gaussElim(A, b);

		io.close();
	}

	static double[] gaussElim(double[][] A, double[] b) {
		int N = b.length;
		for (int p = 0; p < N; p++) {
			int max = p;
			for (int i = p + 1; i < N; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}
			swap(A, p, max);
			swap(b, p, max);
			// (almost) singular
			if (Math.abs(A[p][p]) <= EPS) {
				return null;
			}
			// Pivot in A and b
			for (int i = p + 1; i < N; i++) {
				double alpha = A[i][p] / A[p][p];
				b[i] -= alpha * b[p];
				for (int j = p; j < N; j++) {
					A[i][j] -= alpha * A[p][j];
				}
			}
		}

		// substitute back
		double[] x = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++) {
				sum += A[i][j] * x[j];
			}
			x[i] = (b[i] - sum) / A[i][i];
		}
		return x;
	}
	
	static void swap(double[][] A, int x, int y) {
		double[] tmp = A[x];
		A[x] = A[y];
		A[y] = tmp;
	}

	static void swap(double[] b, int x, int y) {
		double tmp = b[x];
		b[x] = b[y];
		b[y] = tmp;
	}

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

		public int nextInt() throws IOException {
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