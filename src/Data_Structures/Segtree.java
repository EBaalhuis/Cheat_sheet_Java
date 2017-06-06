package Data_Structures;

class Segtree {
	private long[] arr;
	private long[] tree;
	private long[] lazy;

	public Segtree(long[] a) {
		arr = a.clone();
		tree = new long[4*a.length];
		lazy = new long[4*a.length];
		build(1,0,arr.length-1);
	}
	
	private void u(int node, int a, int b) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node];
			if (a != b) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	private void build(int node, int a, int b) {
		if (a > b)
			return;

		if (a == b) {
			tree[node] = arr[a];
			return;
		}

		build(node * 2, a, (a + b) / 2);
		build(node * 2 + 1, 1 + (a + b) / 2, b);
		tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]); // operation
	}

	// Update range [i,j] with val
	private void update(int node, int a, int b, int i, int j, long val) {
		u(node,a,b);

		if (a > b || a > j || b < i)
			return;

		if (a >= i && b <= j) {
			tree[node] += val;

			if (a != b) {
				lazy[node * 2] += val;
				lazy[node * 2 + 1] += val;
			}
			return;
		}

		update(node * 2, a, (a + b) / 2, i, j, val);
		update(node * 2 + 1, 1 + (a + b) / 2, b, i, j, val);
		tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]); // operation
	}

	private long query(int node, int a, int b, int i, int j) {
		if (a > b || a > j || b < i)
			return Long.MIN_VALUE; // operation

		u(node,a,b);

		if (a>=i && b <= j) {
			return tree[node];
		}
		
		long q1 = query(node*2,a,(a+b)/2,i,j);
		long q2 = query(node*2+1,1+(a+b)/2,b,i,j);
		long res = Math.max(q1,q2); // operation
		return res;
	}
	
	public void update(int i, int j, int val) {
		update(1,0,arr.length-1,i,j,val);
	}
	
	public long query(int i, int j) {
		return query(1,0,arr.length-1,i,j);
	}
}