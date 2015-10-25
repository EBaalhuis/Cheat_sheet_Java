package Graphs;

import java.util.*;

public class DFS {
	static int dfs(List<Integer>[] graph, int start) {
		int n = graph.length;
		boolean[] used = new boolean[n];
		List<Integer> res = new ArrayList<>();	
		dfsrec(graph, used, res, start);

		for (int i : res) {
			if (true) { // check if i is what you are looking for.
				return i;
			}
		}
		return -1; // did not find any node that satisfies the requirement.
	}

	// Used internally for dfs.
	static void dfsrec(List<Integer>[] graph, boolean[] used,
	List<Integer> res, 	int u) {
		used[u] = true;
		for (int v : graph[u])
			if (!used[v])
				dfsrec(graph, used, res, v);
		res.add(u);
	}
}
