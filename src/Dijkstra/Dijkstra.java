package Dijkstra;
import java.util.*;

class V implements Comparable<V> {
	public final int name;
	public List<E> adj;
	public double dist = Double.POSITIVE_INFINITY;
	public V prev;

	public V(int nm) {
		name = nm;
		adj = new ArrayList<E>();
	}

	public int compareTo(V other) {
		return Double.compare(dist, other.dist);
	}
}

class E {
	public final V end;
	public final double w;

	public E(V argEnd, double argW) {
		end = argEnd;
		w = argW;
	}
}

public class Dijkstra {

	public static void compute(V source) {
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

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		for (int i = 0; i < n; i++) {

			int nV = in.nextInt();
			int nE = in.nextInt();
			int source = in.nextInt() - 1;
			V[] vertices = new V[nV];
			E[] edges = new E[nE];

			for (int j = 0; j < nV; j++) {
				vertices[j] = new V(j);
			}

			for (int j = 0; j < nE; j++) {
				int end = in.nextInt() - 1;
				int start = in.nextInt() - 1;
				double w = in.nextDouble();
				edges[j] = new E(vertices[end], w);
				vertices[start].adj.add(edges[j]);
			}

			compute(vertices[source]);

		}
	}
}
