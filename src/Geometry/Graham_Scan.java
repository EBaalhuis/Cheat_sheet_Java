package Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

//Find the convex hull of a set of points in 2d.
//Given P[] of points, return Stack<P> with all points on convex hull
//in ccw order (from the bottom of the stack up).
public class Graham_Scan {
	static Stack<P> compute(P[] points) {
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;

		// Set base point with smallest x. Tie-breaker smaller y.
		for (int i = 0; i < points.length; i++) {
			if (points[i].x < minX) {
				minX = points[i].x;
				minY = points[i].y;
			} else if (points[i].x == minX) {
				if (points[i].y < minY) {
					minX = points[i].x;
					minY = points[i].y;
				}
			}
		}

		// Modify all points so that base point is origin.
		for (int i = 0; i < points.length; i++) {
			points[i].x -= minX;
			points[i].y -= minY;
			points[i].setSlope();
		}

		Arrays.sort(points);

		Stack<P> s = new Stack<P>();
		s.push(points[0]);
		s.push(points[1]);
		for (int i = 2; i < points.length; i++) {
			P a = s.elementAt(s.size() - 2);
			P b = s.elementAt(s.size() - 1);
			if (ccw(a, b, points[i]) < 0) { // use <= here to include co-linear
											// points
				s.pop();
				i--;
				continue;
			}
			s.push(points[i]);
		}

		return s;
	}

	static double ccw(P a, P b, P c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	static class P implements Comparable<P> {
		public int x;
		public int y;
		public double slope;

		public P(int a, int b) {
			x = a;
			y = b;
			setSlope();
		}

		public void setSlope() {
			if (x != 0) {
				slope = y / x;
			} else if (y > 0) {
				slope = Double.MAX_VALUE;
			} else {
				slope = -Double.MAX_VALUE;
			}
		}

		public int compareTo(P o) {
			return Double.compare(this.slope, o.slope);
		}
	}

	public static void main(String[] args) throws IOException {
		in.init(System.in);

		int nPoints = in.nextInt();
		P[] points = new P[nPoints];
		for (int i = 0; i < nPoints; i++) {
			points[i] = new P(in.nextInt(), in.nextInt());
		}

		Stack<P> st = compute(points);
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

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}
