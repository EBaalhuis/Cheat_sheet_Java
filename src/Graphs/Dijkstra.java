//Find shortest path from one source to each other vertex.
//O(E + V logV)
package Graphs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra {

	static class V implements Comparable<V> {
		public final int name;
		public List<E> adj;
		public double dist = Double.POSITIVE_INFINITY;
		public V prev;

		public V(int nm) {
			name = nm;
			adj = new ArrayList<E>();
		}

		public int compareTo(V o) {
			return Double.compare(dist, o.dist);
		}
	}

	static class E {
		public final V end;
		public final double w;

		public E(V argEnd, double argW) {
			end = argEnd;
			w = argW;
		}
	}
	
	static void compute(V source) {
		source.dist = 0.;
		PriorityQueue<V> vQue = new PriorityQueue<V>();
		vQue.add(source);

		while (!vQue.isEmpty()) {
			V u = vQue.poll();

			for (E e : u.adj) {
				V v = e.end;
				double w = e.w;
				double uDist = u.dist + w;
				if (uDist < v.dist) {
					vQue.remove(v);
					v.dist = uDist;
					v.prev = u;
					vQue.add(v);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		in.init(System.in);
		int n = in.nextInt();
		
		for (int i = 0; i < n; i++) {

			int nV = in.nextInt();
			int nE = in.nextInt();
			int source = in.nextInt() - 1;
			V[] vertices = new V[nV];
			E[] edges = new E[2*nE]; // remove "2*" for directed graph

			for (int j = 0; j < nV; j++) {
				vertices[j] = new V(j);
			}

			for (int j = 0; j < nE; j++) {
				int start = in.nextInt() - 1;
				int end = in.nextInt() - 1;
				double w = in.nextDouble();
				edges[2*j] = new E(vertices[end], w); // remove "2*" for directed graph
				edges[2*j+1] = new E(vertices[start], w); // remove line for directed graph
				vertices[start].adj.add(edges[2*j]); // remove "2*" for directed graph
				vertices[end].adj.add(edges[2*j+1]); // remove line for directed graph
			}

			compute(vertices[source]);
		}
	}
	
	// Class in only for testing.
	static class in {
	    static BufferedReader reader;
	    static StringTokenizer tokenizer;

	    static void init(InputStream input) {
	        reader = new BufferedReader(
	                     new InputStreamReader(input) );
	        tokenizer = new StringTokenizer("");
	    }

	    static String next() throws IOException {
	        while ( ! tokenizer.hasMoreTokens() ) {
	            tokenizer = new StringTokenizer(
	                   reader.readLine() );
	        }
	        return tokenizer.nextToken();
	    }

	    static int nextInt() throws IOException {
	        return Integer.parseInt( next() );
	    }
		
	    static double nextDouble() throws IOException {
	        return Double.parseDouble( next() );
	    }
	}
}
