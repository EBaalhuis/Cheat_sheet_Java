package Geometry;

public class Line_Intersect {

	// Given two lines according to lineParam, returns their point of intersection.
	static double[] intersect(double[] lineParam1, double[] lineParam2) {
		double[] res = new double[2];
		double a1 = lineParam1[0];
		double b1 = lineParam1[1];
		double c1 = lineParam1[2];
		double a2 = lineParam2[0];
		double b2 = lineParam2[1];
		double c2 = lineParam2[2];

		double det = a1 * b2 - a2 * b1;
		if (det == 0) {
			res[0] = Integer.MAX_VALUE;
			res[1] = Integer.MAX_VALUE;
		} else {
			res[0] = (b2 * c1 - b1 * c2) / det;
			res[1] = (a1 * c2 - a2 * c1) / det;
		}

		return res;
	}
}