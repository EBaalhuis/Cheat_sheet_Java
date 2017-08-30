package Graphs;
import java.io.*;
import java.util.*;

public class ArticulationPoints {

	// Find articulation points - vertices whose removal disconnects the graph. O(V+E).
	static void dfs(int u, boolean[] vis, int[] disc, int[] low, int[] par, HashSet<Integer> res,
			ArrayList<Integer>[] g, Integer time) {
		vis[u] = true;
		int c = 0;
		disc[u] = low[u] = ++time;

		for (int v : g[u]) {
			if (!vis[v]) {
				c++;
				par[v] = u;
				dfs(v, vis, disc, low, par, res, g, time);
				low[u] = Math.min(low[u], low[v]);
				if (par[u] == -1 && c > 1) res.add(u);
				if (par[u] != -1 && low[v] >= disc[u]) res.add(u);
			} else if (v != par[u]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
	}
	
	static HashSet<Integer> AP(ArrayList<Integer>[] g) {
		HashSet<Integer> res = new HashSet<>();
		int n = g.length;
		boolean[] vis = new boolean[n];
		int[] disc = new int[n];
		int[] low = new int[n];
		int[] par = new int[n];
		Arrays.fill(par, -1);
		Integer time = 0;

		for (int i = 0; i < n; i++) {
			if (!vis[i])
				dfs(i, vis, disc, low, par, res, g, time);
		}

		return res;
	}
	
	// Example
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int n = io.nextInt();
		int m = io.nextInt();
		
		ArrayList<Integer>[] g = new ArrayList[n];
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			int u = io.nextInt();
			int v = io.nextInt();
			g[u].add(v);
			g[v].add(u);
		}
		
		HashSet<Integer> aps = AP(g);
		
		for (int x : aps) {
			io.println(x);
		}
		
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