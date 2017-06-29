package Geometry;

public class Basics_old {

	// Sine rule: a / sin(alpha) = b / sin(beta) = c / sin(gamma)
	// Cos rule: c^2 = a^2 + b^2 - 2bc cos(gamma)
	
	static double[] crossProduct(double[] u, double[] v) {
		double[] res = new double[3];
		res[0] = u[1] * v[2] - u[2] * v[1];
		res[1] = u[2] * v[0] - u[0] * v[2];
		res[2] = u[0] * v[1] - u[1] * v[0];
		return res;
	}

	static double dotProduct(double[] u, double[] v) {
		double res = 0;
		for (int i = 0; i < v.length; i++) {
			res += u[i] * v[i];
		}
		return res;
	}

	// Line given by point 1 (p1x,p1y) and point2 (p2x,p2y).
	// Return double[3] with a, b, c such that l : ax + by = c.
	static double[] lineParam(double p1x, double p1y, double p2x, double p2y) {
		double[] res = new double[3];
		res[0] = p2y - p1y;
		res[1] = p1x - p2x;
		res[2] = res[0] * p1x + res[1] * p1y;
		return res;
	}

	// Line given by two points (l1x, l1y) and (l2x,l2y).
	// Check if point1 (p1x,p1y) and point2 (p2x,p2y) are on the same side.
	static boolean sameSide(double p1x, double p1y, double p2x, double p2y, double l1x, double l1y, double l2x,
			double l2y) {
		double[] u = new double[] { l2x - l1x, l2y - l1y, 0 };
		double[] v = new double[] { p1x - l1x, p1y - l1y, 0 };
		double[] w = new double[] { p2x - l1x, p2y - l1y, 0 };
		double[] cp1 = crossProduct(u, v);
		double[] cp2 = crossProduct(u, w);
		return dotProduct(cp1, cp2) >= 0;
	}

	// Triangle given by 3 points (ax, ay), (bx, by), (cx, cy). Return area.
	static double areaTriangle(double ax, double ay, double bx, double by, double cx, double cy) {
		return Math.abs(ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2.;
	}

	// Triangle given by 3 points (ax, ay), (bx, by), (cx, cy).
	// Check if point (px, py) is inside the triangle. Points on edge are
	// counted in.
	static boolean pointInTriangle(double px, double py, double ax, double ay, double bx, double by, double cx,
			double cy) {
		return (sameSide(px, py, ax, ay, bx, by, cx, cy) && sameSide(px, py, bx, by, ax, ay, cx, cy)
				&& sameSide(px, py, cx, cy, ax, ay, bx, by));
	}
}
