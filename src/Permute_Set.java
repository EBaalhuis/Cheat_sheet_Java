import java.io.*;
import java.util.*;

// Generate all permutations of a set (of integers). Call with (list, 0).
public class Permute_Set {
	static HashSet<Integer[]> permute(List<Integer> arr, int k) {
		HashSet<Integer[]> set = new HashSet<>();
		for (int i = k; i < arr.size(); i++) {
			Collections.swap(arr, i, k);
			set.addAll(permute(arr, k + 1));
			Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			set.add((Integer[]) arr.toArray().clone());
		}
		return set;
	}
	
	public static void main(String[] args) throws IOException {
		in.init(System.in);

		HashSet<Integer[]> set = permute(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 0);
		System.out.println(1);
	}
	
	// Class in for testing
	static class in {
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		static void init(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
			tokenizer = new StringTokenizer("");
		}

		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		static long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}
