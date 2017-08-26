package Dynamic_Programming;

import java.io.*;
import java.util.*;

public class Subsets {

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int bits = 3;

		// Top down
		for (int item = 0; item < bits; item++) {
			for (int mask = (1 << (bits + 1) - 1); mask >= 0; mask--) {
				if ((mask & (1 << item)) == 0) {
					int superset = mask ^ (1 << item);
					// Do things
				}
			}
		}

		// Bottom up
		for (int item = 0; item < bits; item++) {
			for (int mask = 0; mask < (1 << bits); mask++) {
				if ((mask & (1 << item)) != 0) {
					int subset = mask ^ (1 << item);
					// Do things
				}
			}
		}

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
