// Alternative implementation, appears to be no faster.
// Not yet using PriorityQueue for vertices (this is important for speed).
package Graphs;

import java.io.*;
import java.util.*;

public class Dijkstra2 {

	static void compute(int source, int[] dist, List<int[]>[] g) {
		dist[source] = 0;
		PriorityQueue<Integer> vQue = new PriorityQueue<>();
		vQue.add(source);
		int[] prev = new int[dist.length];

		while (!vQue.isEmpty()) {
			int u = vQue.poll();

			for (int[] e : g[u]) {
				int v = e[0];
				int w = e[1];
				int uDist = dist[u] + w;
				if (uDist < dist[v]) {
					vQue.remove(v);
					dist[v] = uDist;
					prev[v] = u;
					vQue.add(v);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		in.init(System.in);
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {

			int nV = in.nextInt();
			int nE = in.nextInt();
			int source = in.nextInt() - 1;

			List<int[]>[] g = new List[nV];
			for (int j = 0; j < g.length; j++) {
				g[j] = new ArrayList<>();
			}
			for (int j = 0; j < nE; j++) {
				int start = in.nextInt() - 1;
				int end = in.nextInt() - 1;
				int w = in.nextInt();
				int[] e1 = { end, w };
				int[] e2 = { start, w };
				g[start].add(e1);
				g[end].add(e2); // If the graph is not directed.
			}
			int[] dist = new int[nV];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			compute(source, dist, g);
		}
	}

	// Class in only for testing.
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
