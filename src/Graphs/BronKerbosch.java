package Graphs;

import java.io.*;
import java.util.*;

public class BronKerbosch {
	
	static TreeSet<Integer> r;
	static TreeSet<Integer> p;
	static TreeSet<Integer> x;
	static HashSet<TreeSet<Integer>> maxCliques;
	
	// Find all maximal cliques in g. WARNING exponential complexity, this is NP-complete! n <= 40 roughly.
	public static void bk(TreeSet<Integer> r, TreeSet<Integer> p, TreeSet<Integer> x, ArrayList<Integer>[] g, HashSet<TreeSet<Integer>> max) {
		if (p.isEmpty() && x.isEmpty()) {
			maxCliques.add((TreeSet<Integer>) r.clone());
			return;
		}
		int u = p.isEmpty() ? x.first() : p.first();
		TreeSet<Integer> removing = new TreeSet<>();
		for (int v : p) {
			if (g[u].contains(v)) {
				continue;
			}
			TreeSet<Integer> r2 = (TreeSet<Integer>) r.clone();
			r2.add(v);
			TreeSet<Integer> p2 = new TreeSet<>();
			TreeSet<Integer> x2 = new TreeSet<>();
			for (int a : g[v]) {
				if (p.contains(a)) {
					p2.add(a);
				}
				if (x.contains(a)) {
					x2.add(a);
				}
			}
			
			bk(r2, p2, x2, g, max);
			removing.add(v);
		}
		p.removeAll(removing);
		x.addAll(removing);
	}

	// Driver for testing (shibuyacrossing)
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int n = io.nextInt();
		int m = io.nextInt();
		
		ArrayList<Integer>[] g = new ArrayList[n];
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			int a = io.nextInt() - 1;
			int b = io.nextInt() - 1;
			g[a].add(b);
			g[b].add(a);
		}
		
		x = new TreeSet<>();
		r = new TreeSet<>();
		p = new TreeSet<>();
		maxCliques = new HashSet<>();
		
		for (int i = 0; i < n; i++) {
			p.add(i);
		}
		
		bk(r, p, x, g, maxCliques);
		
		int max = 0;
		for (TreeSet<Integer> c : maxCliques) {
			max = Math.max(max, c.size());
		}
		
		io.println(max);
		
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