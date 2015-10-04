package Combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {
	public static void main(String[] args)  throws IOException{
		in.init(System.in);
		
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			int cap = in.nextInt();
			int[] v = new int[n];
			int[] c = new int[n];
			for (int j = 0; j < n; j++) {
				v[j] = in.nextInt();
			}
			for (int j = 0; j < n; j++) {
				c[j] = in.nextInt();
			}
			
			int[] dp = new int[cap+1];
			
			for (int j = 1; j < n+1; j++) {
				for (int j2 = cap; j2 >= 0; j2--) {
					if (c[j-1] <= j2) {
						dp[j2] = Math.max(dp[j2], dp[j2-c[j-1]]+v[j-1]);
					} else
						dp[j2] = dp[j2];
				}
			}
			
			System.out.println(dp[cap]);
		}
	}
	
	static class in {
	    static BufferedReader reader;
	    static StringTokenizer tokenizer;

	    static void init(InputStream input) {
	        reader = new BufferedReader(
	                     new InputStreamReader(input) );
	        tokenizer = new StringTokenizer("");
	    }

	    static String next() throws IOException {
	        while ( ! tokenizer.hasMoreTokens() ) {
	            tokenizer = new StringTokenizer(
	                   reader.readLine() );
	        }
	        return tokenizer.nextToken();
	    }

	    static int nextInt() throws IOException {
	        return Integer.parseInt( next() );
	    }
		
	    static double nextDouble() throws IOException {
	        return Double.parseDouble( next() );
	    }
	}
}
