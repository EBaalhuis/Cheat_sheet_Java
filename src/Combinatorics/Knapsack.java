package Combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Solve the Knapsack problem with O(n*c) time and O(c) space.
public class Knapsack {
	public static void main(String[] args) throws IOException {
		in.init(System.in);

		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int nItems = in.nextInt();
			int cap = in.nextInt();
			int[] v = new int[nItems];
			int[] c = new int[nItems];
			for (int j = 0; j < nItems; j++) {
				v[j] = in.nextInt();
			}
			for (int j = 0; j < nItems; j++) {
				c[j] = in.nextInt();
			}

			int[] dp = new int[cap + 1];

			for (int j = 0; j < nItems ; j++) {
				for (int j2 = cap; j2 >= c[j]; j2--) {
					dp[j2] = Math.max(dp[j2], dp[j2 - c[j]] + v[j]);
				}
			}

			System.out.println(dp[cap]); // shows the max possible value
		}
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
