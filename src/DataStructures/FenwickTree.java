package DataStructures;

// Allows O(log n) updating and querying of cumulative frequency.
class FenwickTree {
	private int n;
	private double[] s;

	public FenwickTree(int _n) {
		n = _n;
		s = new double[n];
	}

	public void update(int pos, double dif) {
		for (; pos < n; pos |= pos + 1) {
			s[pos] += dif;
		}
	}

	public int query(int pos) {
		int count = 0;
		for (; pos >= 0; pos = (pos & (pos + 1)) - 1) {
			count += s[pos];
		}
		return count;
	}
}
