package In_Out;

import java.io.*;

//Only use if you need to print many lines per test case.
public class FastOutput {
	
	public static void main(String[] args) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		out.write("A line\n");
		out.flush();
		}
}
