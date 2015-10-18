package Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Find the area of any polygon in 2d. Points must be in cw or ccw order.
public class Polygon_Area {

	public static double area(double[] xcoord, double[] ycoord) {
		double res = 0;
		for (int i = 0; i < xcoord.length - 1; i++) {
			res += xcoord[i] * ycoord[i + 1] - xcoord[i + 1] * ycoord[i];
		}
		res += xcoord[xcoord.length-1] * ycoord[0] - xcoord[0] * ycoord[xcoord.length-1];
		return Math.abs(res/2);
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
