package Misc;
import java.util.PriorityQueue;

// Make a custom class with compareTo, e.g. to put in PriorityQueue.
public class CustomComparable {
	
	static class test implements Comparable<test> {
		public int x;
		
		test(int _x) {
			x = _x;
		}
		
		public int compareTo(test o) {
			return Integer.compare(o.x, x);
		}
	}
	
	public static void main(String[] args) {
		test a1 = new test(1);
		test a2 = new test(2);
		
		PriorityQueue<test> q = new PriorityQueue<test>();
		q.add(a1);
		q.add(a2);
	}
}