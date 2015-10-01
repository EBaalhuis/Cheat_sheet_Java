package In_Out;

//Only use if you need to print many lines per test case.
public class FastOutput {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		//Append lines to sb
		sb.append(String.format("%d %f\n", 1, 0.5));
		
		//Print whole sb
		System.out.print(sb);
	}
}
