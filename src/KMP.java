import java.io.*;
import java.util.*;

public class KMP {

	static int KMP(String needle, String haystack) {
		int n = needle.length();
		int h = haystack.length();

		int[] prefix = new int[n+1];
		prefix[0] = -1;
		int q = -1;
		for (int i = 0; i < n; i++) {
			while (q >= 0 && needle.charAt(q) != needle.charAt(i)) {
				q = prefix[q];
			}
			q++;
			prefix[i+1] = q;
		}
		
		q = 0;
		for (int i = 0; i < h; i++) {
			while (q >= 0 && needle.charAt(q) != haystack.charAt(i)) {
				q = prefix[q];
			}
			q++;
			if (q == n) {
				return i - n + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);
		String pat = io.next();
		String txt = io.next();

		int offset = KMP(pat, txt);

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