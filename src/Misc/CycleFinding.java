package Misc;

import java.io.*;
import java.util.*;

public class CycleFinding {

	// Find shortest cycle of function f. Return length and time to enter
	static int[] TH(int x0) {
		int power = 1, len = 1;
		int t = x0;
		int h = f(x0);
		while (t!= h) {
			if (power==len) {
				t = h;
				power *= 2;
				len = 0;
			}
			h = f(h);
			len++;
		}
		
		int start = 0;
		t = h = x0;
		for (int i = 0; i < len; i++) {
			h = f(h);
		}
		while (t!=h) {
			t = f(t);
			h = f(h);
			start++;
		}
		
		return new int[] {len, start};
	}
	
	// Mapping f for testing
	static int f(int x) {
		int[] y = {6,6,0,1,4,3,3,4,0};
		return y[x];
	}
	
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int[] arr = TH(1);
		io.println("len = " + arr[0]);
		io.println("start = " +  arr[1]);
		
		io.close();
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