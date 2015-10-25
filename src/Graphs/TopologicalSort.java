//Sort vertices such that if (u,v) is an edge, u comes before v.
//Only works on acyclic graph. Gives wrong output otherwise!
//O(E + V)
package Graphs;

import java.util.*;

public class TopologicalSort {

	static void dfs(List<Integer>[] graph, boolean[] used, List<Integer> res, int u) {
		used[u] = true;
		for (int v : graph[u])
			if (!used[v])
				dfs(graph, used, res, v);
		res.add(u);
	}

	static List<Integer> topologicalSort(List<Integer>[] graph) {
		int n = graph.length;
		boolean[] used = new boolean[n];
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < n; i++)
			if (!used[i])
				dfs(graph, used, res, i);
		Collections.reverse(res);
		return res;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			int nV = in.nextInt();
			int nE = in.nextInt();
			List<Integer>[] g = new List[nV];

			for (int j = 0; j < g.length; j++) {
				g[j] = new ArrayList<>();
			}

			for (int j = 0; j < nE; j++) {
				int start = in.nextInt();
				int end = in.nextInt();
				g[start].add(end);
				// g[end].add(start); //If the graph is not directed.
			}

			List<Integer> order = topologicalSort(g);
			// System.out.println(order); //Print array of components.
		}
	}
}