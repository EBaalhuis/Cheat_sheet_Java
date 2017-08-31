package Misc;
import java.io.*;
import java.util.*;

public class KMP {

	// Find all occurences of p within s
	static ArrayList<Integer> KMP(String p, String s) {
		int n = p.length();
		int h = s.length();
		ArrayList<Integer> res = new ArrayList<>();

		int[] prefix = new int[n + 1];
		prefix[0] = -1;
		int q = -1;
		for (int i = 0; i < n; i++) {
			while (q >= 0 && p.charAt(q) != p.charAt(i)) {
				q = prefix[q];
			}
			q++;
			prefix[i + 1] = q;
		}

		q = 0;
		for (int i = 0; i < h; i++) {
			while (q >= 0 && p.charAt(q) != s.charAt(i)) {
				q = prefix[q];
			}
			q++;
			if (q == n) {
				res.add(i - n + 1);
				q = prefix[q];
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);
		String pat = io.next();
		String txt = io.next();

		ArrayList<Integer> offset = KMP(pat, txt);

		io.println(offset);
		io.close();
	}

	// IO class for testing
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