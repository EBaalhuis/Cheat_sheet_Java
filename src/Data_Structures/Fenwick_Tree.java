package Data_Structures;

// Allows O(log n) updating and querying of cumulative frequency.
class Fenwick_Tree {
	private int n;
	private long[] s;

	public Fenwick_Tree(int _n) {
		n = _n;
		s = new long[n];
	}

	public void update(int pos, long dif) {
		for (; pos < n; pos |= pos + 1) {
			s[pos] += dif;
		}
	}

	public long query(int pos) {
		long count = 0;
		for (; pos >= 0; pos = (pos & (pos + 1)) - 1) {
			count += s[pos];
		}
		return count;
	}
}
