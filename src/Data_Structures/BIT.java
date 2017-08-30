package Data_Structures;

// Allows O(log n) updating and querying of cumulative frequency.
class BIT {
	private long[] s;

	public BIT(int n) {
		s = new long[n];
	}

	public void add(int pos, long dif) {
		for (int i = pos; i < s.length; i |= i + 1)
			s[i] += dif;
	}

	public long sum(int from, int to) {
		if (from > 0) return sum(0, to) - sum(0, from - 1);
		long res = 0;
		for (int i = to; i >= 0; i = (i & (i + 1)) - 1)
			res += s[i];
		return res;
	}
}

class BIT2D {
	private long[][] t;

	public BIT2D(int r, int c) {
		t = new long[r][c];
	}

	public void add(int r, int c, long dif) {
		for (int i = r; i < t.length; i |= i + 1) {
			for (int j = c; j < t[0].length; j |= j + 1) {
				t[i][j] += dif;
	}}}

	public long sum(int r1, int c1, int r2, int c2) {
		if (r1 != 0 || c1 != 0) {
			return sum(0,0, r2, c2) - sum(0,0, r1 - 1, c2) 
					- sum(0,0, r2, c1 - 1) + sum(0,0, r1 - 1, c1 - 1);
		}
		long res = 0;
		for (int i = r2; i >= 0; i = (i & (i + 1)) - 1) {
			for (int j = c2; j >= 0; j = (j & (j + 1)) - 1) {
				res += t[i][j];
		}}
		return res;
	}
}
