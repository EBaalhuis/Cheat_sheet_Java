package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Lowest Common Ancestor in a rooted tree. O(n log n) preprocess and O(log n) per query
class LCA {
	int N, level[], par[], P[][];

	public LCA(ArrayList<Integer>[] g, int root) {
		N = g.length;
		prep(g, root);
	}

	void prep(ArrayList<Integer>[] g, int root) {
		par = new int[N];
		par[root] = -1;
		level = new int[N];
		Arrays.fill(level, -1);
		level[root] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(root);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int x : g[cur]) {
				if (level[x] == -1) {
					level[x] = level[cur] + 1;
					par[x] = cur;
					q.add(x);
				}
			}
		}

		int k = 0;
		while (1 << k + 1 < N) {
			k++;
		}

		P = new int[N][k + 1];

		for (int i = 0; i < N; i++) {
			Arrays.fill(P[i], -1);
		}

		for (int i = 0; i < N; ++i) {
			P[i][0] = par[i];
		}

		for (int j = 1; j <= k; j++) {
			for (int i = 0; i < N; i++) {
				if (P[i][j - 1] != -1) {
					P[i][j] = P[P[i][j - 1]][j - 1];
				}
			}
		}
	}

	public int query(int p, int q) {
		if (level[p] < level[q]) {
			p ^= q;
			q ^= p;
			p ^= q;
		}

		int k = 0;
		while (1 << k + 1 <= level[p])
			k++;

		for (int i = k; i >= 0; --i) {
			if (level[p] - (1 << i) >= level[q]) {
				p = P[p][i];
			}
		}
		if (p == q)
			return p;

		for (int i = k; i >= 0; --i) {
			if (P[p][i] != -1 && P[p][i] != P[q][i]) {
				p = P[p][i];
				q = P[q][i];
			}
		}

		return P[p][0];
	}
}