import java.util.ArrayList;

// Some miscellaneous useful methods
public class Basics {

	// Given a List<Integer> list remove an element with value i.
	// Note that list.remove(i) removes the element at index i.
	ArrayList<Integer> list = new ArrayList<Integer>();
	int i = 0;
	boolean res = list.remove(Integer.valueOf(i));

	// Given a String s with a hex number, return that number as a long.
	String hexNr = "1a8fd0c";
	long number = Long.parseLong(hexNr, 16);

}
