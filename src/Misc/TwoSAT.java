package Misc;

import java.util.*;

class TwoSAT {
    private List<Integer>[] g;

    public TwoSAT(int n) {
        g = new List[2 * n];
        for (int i = 0; i < g.length; i++)
            g[i] = new ArrayList<>();
    }

    public boolean[] solve() {
        int n = g.length;
        List<List<Integer>> comps = scc(g);
        int[] comp = new int[n];
        for (int i = 0; i < comps.size(); i++) {
			for (int x : comps.get(i)) {
				comp[x] = i;
			}
		}
        for (int i = 0; i < n; ++i)
            if (comp[i] == comp[i ^ 1])
                return null;
        boolean[] res = new boolean[n / 2];
        for (int i = 0; i < n; i += 2)
            res[i / 2] = comp[i] < comp[i ^ 1];
        return res;
    }

    private static int b(boolean b) {
        return b ? 1 : 0;
    }

    private void ae(int i, int j) {
        g[i + 1].add(j + 1);
    }

    public void force(int i, boolean v) {
        if (v) ae(i * 2, i * 2 - 1);
        else ae(i * 2 - 1, i * 2);
    }

    public void not(int i, boolean bi, int j, boolean bj) {
        ae(i * 2 - b(bi), j * 2 - b(!bj));
        ae(j * 2 - b(bj), i * 2 - b(!bi));
    }
    
    // ONLY FOR TESTING from here
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
}