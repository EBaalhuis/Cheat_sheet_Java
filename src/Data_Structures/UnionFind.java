package Data_Structures;

//Stores disjoint sets, allowing you to combine two of them using union(x,y).
//Cost is almost constant (amortized).
class UnionFind {
	private int[] parent;
	private int[] rank;
	
	public UnionFind(int size) {
		parent = new int[size];
		rank = new int[size];
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
	}
	
	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
}
