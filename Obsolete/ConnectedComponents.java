import java.util.*;
//Tarjan is better.

class V implements Comparable<V> {
	public final int name;
	public List<E> adj;
	public boolean checked;

	public V(int nm) {
		name = nm;
		adj = new ArrayList<E>();
		checked = false;
	}

	public int compareTo(V other) {
		return Boolean.compare(checked, other.checked);
	}
}

class E {
	public final V end;

	public E(V argEnd) {
		end = argEnd;
	}
}

public class ConnectedComponents {

	public static void connect(PriorityQueue<V> vQue) {
		while (!vQue.isEmpty()) {
			V u = vQue.poll();

			for (E e : u.adj) {
				V v = e.end;
				if (!v.checked) {
					v.checked = true;
					nChecked++;
					vQue.add(v);
				}
			}
		}
	}

	public static int nChecked = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			nChecked = 0;
			int count = 0;
			int nV = in.nextInt();
			int nE = in.nextInt();
			V[] vertices = new V[nV];
			E[] edges = new E[nE * 2];
			PriorityQueue<V> vQue = new PriorityQueue<V>();

			for (int j = 0; j < nV; j++) {
				vertices[j] = new V(j);
			}

			for (int j = 0; j < nE; j++) {
				int end = in.nextInt() - 1;
				int start = in.nextInt() - 1;
				edges[j] = new E(vertices[end]);
				vertices[start].adj.add(edges[j]);
				edges[2 * j] = new E(vertices[start]);
				vertices[end].adj.add(edges[2 * j]);
			}

			while (nChecked < nV) {
				for (int j = 0; j < vertices.length; j++) {
					if (!vertices[j].checked) {
						vertices[j].checked = true;
						nChecked++;
						count++;
						vQue.add(vertices[j]);
						connect(vQue);
						break;
					}
				}
			}
			
			System.out.println(count);
		}
	}
}
