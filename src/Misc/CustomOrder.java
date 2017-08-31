package Misc;
import java.util.Arrays;
import java.util.Comparator;

// Make a custom order for existing type, e.g. int, to sort with.
public class CustomOrder {

	static class Order implements Comparator<Integer> {
		public int compare(Integer x, Integer y) {
			return -1 * Integer.compare(x, y);
		}
	}
	
	public static void main(String[] args) {

		Integer[] arr = new Integer[4];
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 5;
		arr[3] = -3;

		Arrays.sort(arr, new Order());
	}
}