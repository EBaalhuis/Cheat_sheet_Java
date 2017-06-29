package Geometry;

public class Polygon_Area {

	//Find the area of any polygon in 2d. Points must be in cw or ccw order.
	static double area(double[] x, double[] y) {
		double res = 0;
		for (int i = 0; i < x.length - 1; i++) {
			res += x[i] * y[i + 1] - x[i + 1] * y[i];
		}
		res += x[x.length-1] * y[0] - x[0] * y[x.length-1];
		return Math.abs(res/2);
	}

}
