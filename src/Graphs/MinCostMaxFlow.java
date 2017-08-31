package Graphs;
import java.io.*;
import java.util.*;

public class MinCostMaxFlow {
	// Find flow of value at least k for minimum cost. k=MAX_VALUE for max flow.
	static long[] MCMF(ArrayList<E>[] g, long k, int s, int t) {
		long flow = 0, cost = 0, INF = Long.MAX_VALUE/1000;
		int n = g.length;

		while (flow < k) {
			int[] id = new int[n];
			long[] d = new long[n];
			Arrays.fill(d, INF);
			int[] q = new int[n];
			int[] p = new int[n];
			int[] p_rib = new int[n];

			int qh = 0, qt = 0;
			q[qt++] = s;
			d[s] = 0;
			while (qh != qt) {
				int v = q[qh++];
				id[v] = 2;
				if (qh == n) qh = 0;
				for (int i = 0; i < g[v].size(); i++) {
					E r = g[v].get(i);
					if (r.f < r.cap && d[v] + r.cost < d[r.b]) {
						d[r.b] = d[v] + r.cost;
						if(id[r.b] == 0) {
							q[qt++] = r.b;
							if (qt == n) qt = 0;
						} else if (id[r.b] == 2) {
							if (--qh == -1) qh = n-1;
							q[qh] = r.b;
						}
						id[r.b] = 1;
						p[r.b] = v;
						p_rib[r.b] = i;
					}
				}
			}
			
			if (d[t] == INF) break;
			long addflow = k - flow;
			for (int v = t; v != s; v = p[v]) {
				int pv = p[v];
				int pr = p_rib[v];
				addflow = Math.min(addflow, g[pv].get(pr).cap - g[pv].get(pr).f);
			}
			for (int v = t; v != s; v = p[v]) {
				int pv = p[v];
				int pr = p_rib[v], r = g[pv].get(pr).back;
				g[pv].get(pr).f += addflow;
				g[v].get(r).f -= addflow;
				cost += g[pv].get(pr).cost * addflow;
			}
			flow += addflow;
		}
		return new long[] {flow, cost};
	}
	
	static class E {
		int b;
		long cap, cost, f; //end, capacity, cost, flow
		int back;
		
		E (int _b, long _cap, long _cost, int _back) {
			b = _b;
			cap = _cap;
			cost = _cost;
			f = 0;
			back = _back;
		}
	}
	
	static void addEdge(ArrayList<E>[] g, int a, int b, long cap, long cost) {
		E e1 = new E(b, cap, cost, g[b].size());
		E e2 = new E(a, 0, -cost, g[a].size());
		g[a].add(e1);
		g[b].add(e2);
	}
	
	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);
		
		int n = io.nextInt();
		int m = io.nextInt();
		long k = Long.MAX_VALUE;
		int s = io.nextInt();
		int t = io.nextInt();

		ArrayList<E>[] g = new ArrayList[n];
		for (int i = 0; i < g.length; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			int a = io.nextInt();
			int b = io.nextInt();
			long cap = io.nextLong();
			long cost = io.nextLong();
			addEdge(g, a, b, cap, cost);
		}
		
		long[] res = MCMF(g, k, s, t);

		io.println(res[0] + " " + res[1]);
		
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