package Dynamic_Programming;

public class LCSubstring {

	// Given two strings, returns a longest common substring (i.e. consecutive).
	// O(nm).
	static String LCSubstring(String s, String t) {
		int m = s.length();
		int n = t.length();
		int[][] L = new int[m][n];
		int z = 0;
		String res = "";

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (s.charAt(i) == t.charAt(j)) {
					if (i == 0 || j == 0) {
						L[i][j] = 1;
					} else {
						L[i][j] = L[i - 1][j - 1] + 1;
					}
					if (L[i][j] > z) {
						z = L[i][j];
						res = s.substring(i - z + 1, i + 1);
					}
				}
			}
		}
		return res;
	}
	
	// Main method only for testing.
	public static void main(String[] args) {
		String s = "ababaa";
		String t = "baab";
		System.out.println(LCSubstring(s, t));
	}
}
