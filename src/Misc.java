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

	// To get the actual modulo of negative number (being a positive result).
	int a, b;
	int modulo = (a % b + b) % b;
}
