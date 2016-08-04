package Dynamic_Programming;
import java.io.*;
import java.util.*;

public class Subsets {

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);
			int n = io.nextInt();
			boolean[] allowed = new boolean[1 << n];
			// Full set is allowed
			allowed[(1 << n) - 1] = true;
			for (int mask = (1 << n) - 1; mask > 0; --mask) {
				if (allowed[mask]) {
					for (int i = 0; i < n; ++i) {
						// Check if mask >> i is a subset
						if (((mask >> i) & 1) != 1) {
							continue;
						}
						// Some kind of computation for this subset
						if (true) {
							allowed[mask ^ (1 << i)] = true;
						}
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
