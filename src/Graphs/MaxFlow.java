package Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;



public class MaxFlow {
	// Dinics max flow
	// O(V^2 * E)
	static class Edge {
		int end, rev, cap, flow;

		public Edge(int t, int rev, int cap) {
			this.end = t;
			this.rev = rev;
			this.cap = cap;
		}
	}

	public static ArrayList<ArrayList<Edge>> createGraph(int nodes) {
		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>(nodes);
		for (int i = 0; i < nodes; i++) {
			graph.add(new ArrayList<Edge>());
		}
		return graph;
	}

	public static void addEdge(ArrayList<ArrayList<Edge>> graph, int s, int t, int cap) {
		graph.get(s).add(new Edge(t, graph.get(t).size(), cap));
		graph.get(t).add(new Edge(s, graph.get(s).size() - 1, 0));
	}

	static boolean dinicBfs(ArrayList<ArrayList<Edge>> graph, int src, int dest, int[] dist) {
		Arrays.fill(dist, -1);
		dist[src] = 0;
		int[] Q = new int[graph.size()];
		int sizeQ = 0;
		Q[sizeQ++] = src;
		for (int i = 0; i < sizeQ; i++) {
			int u = Q[i];
			for (Edge e : graph.get(u)) {
				if (dist[e.end] < 0 && e.flow < e.cap) {
					dist[e.end] = dist[u] + 1;
					Q[sizeQ++] = e.end;
				}
			}
		}
		return dist[dest] >= 0;
	}

	static int dinicDfs(ArrayList<ArrayList<Edge>> graph, int[] ptr, int[] dist, int dest, int u, int f) {
		if (u == dest)
			return f;
		for (; ptr[u] < graph.get(u).size(); ++ptr[u]) {
			Edge e = graph.get(u).get(ptr[u]);
			if (dist[e.end] == dist[u] + 1 && e.flow < e.cap) {
				int df = dinicDfs(graph, ptr, dist, dest, e.end, Math.min(f, e.cap - e.flow));
				if (df > 0) {
					e.flow += df;
					graph.get(e.end).get(e.rev).flow -= df;
					return df;
				}
			}
		}
		return 0;
	}

	public static int maxFlow(ArrayList<ArrayList<Edge>> graph, int src, int dest) {
		int flow = 0;
		int[] dist = new int[graph.size()];
		while (dinicBfs(graph, src, dest, dist)) {
			int[] ptr = new int[graph.size()];
			while (true) {
				int df = dinicDfs(graph, ptr, dist, dest, src, Integer.MAX_VALUE);
				if (df == 0)
					break;
				flow += df;
			}
		}
		return flow;
	}

	// Main method for testing and example.
	public static void main(String[] args) {
		ArrayList<ArrayList<Edge>> graph = createGraph(4);
		addEdge(graph, 0, 1, 3);
		addEdge(graph, 0, 2, 1);
		addEdge(graph, 1, 2, 1);
		addEdge(graph, 1, 3, 1);
		addEdge(graph, 2, 3, 4);

		System.out.println(maxFlow(graph, 0, 3));
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

		static long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}