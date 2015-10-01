//Find strongly connected components of a graph.
//O(E + V)
package Graphs;
import java.util.*;

public class Tarjan {
	List<Integer>[] graph;
	boolean[] visited;
	Stack<Integer> stack;
	int time;
	int[] lowlink;
	List<List<Integer>> components;

	public List<List<Integer>> scc(List<Integer>[] graph) {
		int n = graph.length;
		this.graph = graph;
		visited = new boolean[n];
		stack = new Stack<>();
		time = 0;
		lowlink = new int[n];
		components = new ArrayList<>();

		for (int u = 0; u < n; u++)
			if (!visited[u])
				dfs(u);

		return components;
	}

	void dfs(int u) {
		lowlink[u] = time++;
		visited[u] = true;
		stack.add(u);
		boolean isComponentRoot = true;

		for (int v : graph[u]) {
			if (!visited[v])
				dfs(v);
			if (lowlink[u] > lowlink[v]) {
				lowlink[u] = lowlink[v];
				isComponentRoot = false;
			}
		}

		if (isComponentRoot) {
			List<Integer> component = new ArrayList<>();
			while (true) {
				int x = stack.pop();
				component.add(x);
				lowlink[x] = Integer.MAX_VALUE;
				if (x == u)
					break;
			}
			components.add(component);
		}
	}

//	public static void main(String[] args) {
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
//			List<List<Integer>> components = new Tarjan().scc(g);
//			//System.out.println(components); //Print array of components.
//			//System.out.println(components.size()); //Print number of components.
//		}
//	}
}