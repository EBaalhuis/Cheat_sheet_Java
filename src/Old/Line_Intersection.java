package Old;

public class Line_Intersection {

	// Given two lines according to lineParam, returns their point of intersection.
	static double[] intersect(double[] l1, double[] l2) {
		double[] res = new double[2];

		double det = l1[0] * l2[1] - l2[0] * l1[1];
		if (det == 0) {
			res[0] = Integer.MAX_VALUE;
			res[1] = Integer.MAX_VALUE;
		} else {
			res[0] = (l2[1] * l1[2] - l1[1] * l2[2]) / det;
			res[1] = (l1[0] * l2[2] - l2[0] * l1[2]) / det;
		}
		return res;
	}

	// Checks if a point p is on the line segment ab.
	static boolean onSegment(double[] a, double[] b, double[] p) {
		return ((p[0] <= Math.max(a[0], b[0]) && p[0] >= Math.min(a[0], b[0]) && p[1] <= Math.max(a[1], b[1])
				&& p[1] >= Math.min(a[1], b[1])) && orientation(a, b, p) == 0);
	}

	// Returns the orientation of the triplet (p,q,r). 0 = colinear,
	// 1 = clockwise, 2 = ccw.
	static int orientation(double[] p, double[] q, double[] r) {
		double val = (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
		if (val == 0) {
			return 0;
		} else if (val > 0) {
			return 1;
		} else {
			return 2;
		}
	}

	// For two line segments ab and pq, determines wether they intersect.
	static boolean segmentIntersect(double[] a, double[] b, double[] p, double[] q) {
		int o1 = orientation(a, b, p);
		int o2 = orientation(a, b, q);
		int o3 = orientation(p, q, a);
		int o4 = orientation(p, q, b);

		if (o1 != o2 && o3 != o4)
			return true;
		if (o1 == 0 && onSegment(a, b, p))
			return true;
		if (o2 == 0 && onSegment(a, b, q))
			return true;
		if (o3 == 0 && onSegment(p, q, a))
			return true;
		if (o4 == 0 && onSegment(p, q, b))
			return true;
		return false;
	}
}
