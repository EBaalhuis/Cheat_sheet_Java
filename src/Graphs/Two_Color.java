package Graphs;

import java.util.*;

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
						} else if (colors[v] == colors[u]) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	// Example driver function
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nV = in.nextInt();
		int nE = in.nextInt();
		
		List<Integer>[] g = new List[nV];
		for (int i = 0; i < nV; i++) {
			g[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < nE; i++) {
			int start = in.nextInt();
			int end = in.nextInt();
			g[start].add(end);
			g[end].add(start); // This line for undirected graphs
		}
	}
}
