package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Kruskal {
	// Given number of vertices nV and E[] edges,
	// returns a list of edges that form a minimum spanning tree.
	// O(E log E).
	static ArrayList<E> compute(int nV,E[] edges) {
		ArrayList<E> res = new ArrayList<E>();
		UnionFind uni = new UnionFind(nV);
		PriorityQueue<E> q = new PriorityQueue<E>();
		for (int i = 0; i < edges.length; i++) {
			q.add(edges[i]);
		}
		
		while (!q.isEmpty()) {
			E e = q.poll();
			int start = e.start.name;
			int end = e.end.name;
			if (uni.find(start) != uni.find(end)) {
				uni.union(start, end);
				res.add(e);
			}
		}
		return res;
	}
	
	static class V {
		public final int name;
		public List<E> adj;
		
		public V(int nm) {
			name = nm;
			adj = new ArrayList<E>();
		}
	}
	
	static class E implements Comparable<E>{
		public final int w;
		public final V end, start;
		
		public E(V argStart, V argEnd, int argW) {
			start = argStart;
			end = argEnd;
			w = argW;
		}
		
		public int compareTo(E other) {
			return Integer.compare(this.w, other.w);
		}
	}
	
	static class UnionFind {
		private int[] parent;
		private int[] rank;
		public int nSets;
		
		public UnionFind(int size) {
			parent = new int[size];
			rank = new int[size];
			nSets = size;
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}
		}
		
		public void union(int x, int y) {
			int xRoot = find(x);
			int yRoot = find(y);
			
			if (xRoot == yRoot) {
				return;
			}
			
			if (rank[xRoot] < rank[yRoot]) {
				parent[xRoot] = yRoot;
			} else if (rank[xRoot] > rank[yRoot]) {
				parent[yRoot] = xRoot;
			} else {
				parent[yRoot] = xRoot;
				rank[xRoot]++;
			}
			nSets--;
		}
		
		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}
	}
}
