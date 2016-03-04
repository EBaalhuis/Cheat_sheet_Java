import java.util.ArrayList;

// Some miscellaneous useful methods
public class Misc {

	// Given a List<Integer> list remove an element with value i.
	// Note that list.remove(i) removes the element at index i.
	ArrayList<Integer> list = new ArrayList<Integer>();
	int i = 0;
	boolean res = list.remove(Integer.valueOf(i));

	// Convert between hex and decimal.
	String hexNr = "a02f";
	long number = Long.parseLong(hexNr, 16);
	String hex = Long.toHexString(number);
	
	// Decimal to binary and back.
	int x = 100;
	String binary = Integer.toBinaryString(x);
	int y = Integer.parseInt(binary, 2);

	// To get the actual modulo of negative number (being a positive result).
	int a, b;
	int modulo = (a % b + b) % b;
	
	// Distance between two binary numbers (number of bits which are different)
	public static int difference(int num1, int num2) {
		int count = 0;
		int xor = num1 ^ num2;
		while (xor != 0) {
			count++;
			xor &= xor - 1;
		}
		return count;
	}
}
