package Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BFS {

	public static int bfs(List<Integer>[] graph, int start) {
		int size = graph.length;
		boolean[] visited = new boolean[size];
		visited[start] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		
		if (true) { // check if start is what you are looking for.
			return start;
		}

		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph[u]) {
				if (!visited[v]) {
					if (true) { // check if v is what you are looking for.
						return v;
					}
					visited[v] = true;
					q.add(v);
				}
			}
		}
		
		return -1;
	}
	
	
//	public static void main(String[] args) throws IOException {
//		Reader.init(System.in);
//		int n = Reader.nextInt();
//
//		for (int i = 0; i < n; i++) {
//			int nV = Reader.nextInt();
//			int nE = Reader.nextInt();
//			List<Integer>[] g = new List[nV];
//
//			for (int j = 0; j < g.length; j++) {
//				g[j] = new ArrayList<>();
//			}
//
//			for (int j = 0; j < nE; j++) {
//				int start = Reader.nextInt();
//				int end = Reader.nextInt();
//				g[start].add(end);
//				//g[end].add(start); //If the graph is not directed.
//			}
//			
//			int res = bfs(g,0); 
//			System.out.println(res);
//		}
//	}

}
