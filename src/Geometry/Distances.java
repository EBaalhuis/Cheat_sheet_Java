package Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Distances {

	// Line given by p1 (x,y) and p2 (x,y).
	// Point given by p (x,y). Return distance between line and point.
	// Requires: Geometry/Basics/dotProduct
	static double distLinePoint(double[] p1, double[] p2, double[] p) {
		double[] q = new double[2];
		double[] q2 = new double[2];
		q[0] = p[0] - p1[0];
		q[1] = p[1] - p1[1];
		q2[0] = p2[0] - p1[0];
		q2[1] = p2[1] - p1[1];

		double paral = dotProduct(q2, q) / dotProduct(q2, q2);

		double[] res = new double[2];
		if (paral <= 0) {
			res = q;
		} else if (paral >= 1) {
			res[0] = q[0] - q2[0];
			res[1] = q[1] - q2[1];
		} else {
			res[0] = q[0] - q2[0] * paral;
			res[1] = q[1] - q2[1] * paral;
		}
		return Math.sqrt(dotProduct(res, res));
	}
	
	// Line 1 given by p1 (x,y) and p2 (x,y), line 2 given by p3 (x,y) and p4 (x,y).
	// Return distance between the two lines. ONLY FOR NON-INTERSECTING LINES!
	static double distLineLine(double[] p1, double[] p2, double[] p3, double[] p4) {
		double[] res = new double[4];
		res[0] = distLinePoint(p1, p2, p3);
		res[1] = distLinePoint(p1, p2, p4);
		res[2] = distLinePoint(p3, p4, p1);
		res[3] = distLinePoint(p3, p4, p2);
		Arrays.sort(res);
		return res[0];
	}

	// Methods for testing
	static double dotProduct(double[] u, double[] v) {
		double res = 0;
		for (int i = 0; i < v.length; i++) {
			res += u[i] * v[i];
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		in.init(System.in);
		double[] p1 = new double[2];
		double[] p2 = new double[2];
		double[] p = new double[2];
		double[] p3 = new double[2];
		p1[0] = in.nextDouble();
		p1[1] = in.nextDouble();
		p2[0] = in.nextDouble();
		p2[1] = in.nextDouble();
		p[0] = in.nextDouble();
		p[1] = in.nextDouble();
		p3[0] = in.nextDouble();
		p3[1] = in.nextDouble();
		
		System.err.println(distLineLine(p1, p2, p, p3));
	}
	
	static class in {
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		static void init(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
			tokenizer = new StringTokenizer("");
		}

		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		static long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}

}
