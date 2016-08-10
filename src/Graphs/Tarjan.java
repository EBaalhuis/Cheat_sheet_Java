package Graphs;
import java.io.*;
import java.util.*;

//Find strongly connected components of a graph.
//O(E + V)
public class Tarjan {

	static List<List<Integer>> scc(List<Integer>[] g) {
		int n = g.length;
		boolean [] visited = new boolean[n];
		Stack<Integer> st = new Stack<>();
		int t = 0;
		int[] link = new int[n];
		List<List<Integer>> comp = new ArrayList<>();

		for (int u = 0; u < n; u++) {
			if (!visited[u]) {
				dfs(u, link, t, visited, st, g, comp);
			}
		}
		return comp;
	}

	static void dfs(int u, int[] link, int t, boolean[] visited, Stack<Integer> st, List<Integer>[] g, List<List<Integer>> comp) {
		link[u] = t++;
		visited[u] = true;
		st.add(u);
		boolean isComponentRoot = true;

		for (int v : g[u]) {
			if (!visited[v])
				dfs(v, link, t, visited, st, g, comp);
			if (link[u] > link[v]) {
				link[u] = link[v];
				isComponentRoot = false;
			}
		}

		if (isComponentRoot) {
			List<Integer> component = new ArrayList<>();
			while (true) {
				int x = st.pop();
				component.add(x);
				link[x] = Integer.MAX_VALUE;
				if (x == u)
					break;
			}
			comp.add(component);
		}
	}

	// Example driver main
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);
		int n = io.nextInt();

		for (int i = 0; i < n; i++) {
			int nV = io.nextInt();
			int nE = io.nextInt();
			List<Integer>[] g = new List[nV];
			
			for (int j = 0; j < g.length; j++) {
				g[j] = new ArrayList<>();
			}
			
			for (int j = 0; j < nE; j++) {
				int start = io.nextInt();
				int end = io.nextInt();
				g[start].add(end);
				//g[end].add(start); //If the graph is not directed.
			}
			
			List<List<Integer>> components = scc(g);
		}
	}
	
	// Class in only for testing.
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