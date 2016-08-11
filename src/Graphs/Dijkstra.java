// Find shortest path from one source to each other vertex.
// O(E + V logV)
package Graphs;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Dijkstra {

	static class V implements Comparable<V> {
		public final int name;
		public List<E> adj;
		public double dist = Double.POSITIVE_INFINITY;
		public V prev;

		public V(int _n) {
			name = _n;
			adj = new ArrayList<E>();
		}

		public int compareTo(V o) {
			return Double.compare(dist, o.dist);
		}
	}

	static class E {
		public final V end;
		public final double w;

		public E(V _e, double _w) {
			end = _e;
			w = _w;
		}
	}
	
	static void compute(V source) {
		source.dist = 0.;
		PriorityQueue<V> q = new PriorityQueue<>();
		q.add(source);

		while (!q.isEmpty()) {
			V v = q.poll();
			for (E e : v.adj) {
				V u = e.end;
				double uDist = v.dist + e.w;
				if (uDist < u.dist) {
					q.remove(u);
					u.dist = uDist;
					u.prev = v;
					q.add(u);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);
		int n = io.nextInt();
		
		for (int i = 0; i < n; i++) {

			int nV = io.nextInt();
			int nE = io.nextInt();
			int source = io.nextInt() - 1;
			V[] vertices = new V[nV];
			E[] edges = new E[2*nE]; // remove "2*" for directed graph

			for (int j = 0; j < nV; j++) {
				vertices[j] = new V(j);
			}

			for (int j = 0; j < nE; j++) {
				int start = io.nextInt() - 1;
				int end = io.nextInt() - 1;
				double w = io.nextDouble();
				edges[2*j] = new E(vertices[end], w); // remove "2*" for directed graph
				edges[2*j+1] = new E(vertices[start], w); // remove line for directed graph
				vertices[start].adj.add(edges[2*j]); // remove "2*" for directed graph
				vertices[end].adj.add(edges[2*j+1]); // remove line for directed graph
			}

			compute(vertices[source]);
		}
		io.close();
	}
	
	// Class io only for testing.
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
