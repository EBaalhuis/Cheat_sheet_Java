package Graphs;

import java.io.*;
import java.util.*;

import Graphs.Dijkstra.E;
import Graphs.Dijkstra.V;

public class BellmanFord {

	// Single source shortest path, negative weights allowed. O(V*E).
	// Return true if there is no negative weight cycle, false otherwise.
	static void BF (V source, int n, ArrayList<E> edges) {
		source.dist=0;
		
		for (int i = 0; i < n-1; i++) {
			for (E e : edges) {
				if (e.start.dist!= Long.MAX_VALUE && e.start.dist + e.w < e.end.dist) {
					e.end.dist = e.start.dist + e.w;
					e.end.prev = e.start;
				}
			}
		}
		
		for (E e : edges) {
			if (e.start.dist!= Long.MAX_VALUE && e.start.dist + e.w < e.end.dist) {
				dfs(e.end);
			}
		}
	}
	
	// dfs to mark vertices reachable from negative cycles
	static void dfs(V start) {
		start.neg_inf = true;
		for (E e : start.adj) {
			if (!e.end.neg_inf) dfs(e.end);
		}
	}
	
	static class V {
		public ArrayList<E> adj = new ArrayList<>();
		public long dist = Long.MAX_VALUE;
		public V prev = null;
		public boolean neg_inf = false;
	}
	
	static class E {
		public final V start;
		public final V end;
		public final long w;

		public E(V _s, V _e, long _w) {
			start = _s;
			end = _e;
			w = _w;
		}
	}
	
	// Example
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int n = io.nextInt();
		int m = io.nextInt();
		int q = io.nextInt();
		int s = io.nextInt();
		
		V[] vert = new V[n];
		for (int i = 0; i < vert.length; i++) {
			vert[i] = new V();
		}
		ArrayList<E> edges = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			int a = io.nextInt();
			int b = io.nextInt();
			int w = io.nextInt();
			E e = new E(vert[a],vert[b],w);
			edges.add(e);
			vert[a].adj.add(e);
		}
		
		BF(vert[s], n, edges);
		
		for (int i = 0; i < q; i++) {
			V tar = vert[io.nextInt()];
			
			if (tar.neg_inf) {
				io.println("-Infinity");
			} else if (tar.dist == Long.MAX_VALUE) {
				io.println("Impossible");
			} else {
				io.println(tar.dist);
			}
		}
		
		io.close();
	}

	static class IO extends PrintWriter {
		static BufferedReader r;
		static StringTokenizer t;

		public IO(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
			t = new StringTokenizer("");
		}

		public String next() throws IOException {
			while (!t.hasMoreTokens()) {
				t = new StringTokenizer(r.readLine());
			}
			return t.nextToken();
		}

		public int nextInt() throws IOException{
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}