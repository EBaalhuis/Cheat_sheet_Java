package Graphs;

import java.util.*;
import java.io.*;

// Backtracking algorithm for coloring a graph. Only usable for small graphs (nV < 20),
// or if the number of colors is known/limited.
public class Color {
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int nV = io.nextInt();
		TreeSet<Integer>[] g = new TreeSet[nV];
		// Make graph here, dont forget to initialize all g[i]
		
		int maxCol = nV;
		for (int nCol = 1; nCol < maxCol; nCol++) {
			TreeSet<Integer> colors = new TreeSet<>();
			for (int i = 1; i <= nCol; i++) {
				colors.add(i);
			}
			boolean[] flag = new boolean[nV];
			int[] assign = new int[nV];
			if (solve(0, nV, flag, assign, g, colors)) {
				io.println(nCol);
				// continue cases;
			}
		}
		io.close();
	}

	static boolean solve(int index, int n, boolean[] flag, int[] assign, TreeSet[] g, TreeSet<Integer> colors) {
		if (index == n) {
			return true;
		}
		TreeSet<Integer> avail = (TreeSet<Integer>) colors.clone();
		if (index == 0) {
			avail.clear();
			avail.add(1);
		} else {
			TreeSet<Integer> adj = g[index];
			for (int a : adj) {
				if (flag[a]) {
					avail.remove(assign[a]);
				}
				if (avail.isEmpty()) {
					return false;
				}
			}
		}
		for (int col : avail) {
			assign[index] = col;
			flag[index] = true;
			boolean outp = solve(index + 1, n, flag, assign, g, colors);
			if (outp) {
				return true;
			}
		}
		assign[index] = 0;
		flag[index] = false;
		return false;
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
