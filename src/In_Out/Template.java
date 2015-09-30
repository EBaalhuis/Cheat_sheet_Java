package In_Out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Template for every solution.
public class Template {
		
	public static void main(String[] args)  throws IOException{
		Reader.init(System.in);
	}
	
	static class Reader {
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

//Add class Reader here.