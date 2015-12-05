package Graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Two_Color {
	
	// Given a graph, returns true if it is 2-colorable. O(E).
	static boolean twoColor(List<Integer>[] graph) {
		int size = graph.length;
		boolean[] visited = new boolean[size];
		int[] colors = new int[size];
		int nVisited = 0;

		for (int i = 0; i < size && nVisited < size; i++) {
			if (!visited[i]) {
				visited[i] = true;
				Queue<Integer> q = new LinkedList<Integer>();
				q.add(i);
				colors[i] = 1;
				nVisited++;

				while (!q.isEmpty()) {
					int u = q.poll();
					for (int v : graph[u]) {
						if (!visited[v]) {
							nVisited++;
							visited[v] = true;
							q.add(v);
						}
						if (colors[v] == 0) {
							colors[v] = 3 - colors[u];
						} else if (colors[v] == colors[u])
							return false;
					}
				}
			}
		}
		return true;
	}
}
