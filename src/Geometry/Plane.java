package Geometry;

public class Plane {

	// Find plane equation ax + by + cz + d = 0. {a,b,c,d} is returned.
	// Points should be in a double[3] {x,y,z}.
	static double[] pointsToPlane(double[] p1, double[] p2, double[] p3) {
		double[] v1 = { p1[0] - p2[0], p1[1] - p2[1], p1[2] - p2[2] };
		double[] v2 = { p1[0] - p3[0], p1[1] - p3[1], p1[2] - p3[2] };
		double[] n = crossProduct(v1, v2);
		double d = -n[0] * p1[0] - n[1] * p1[1] - n[2] * p1[2];
		return new double[] { n[0], n[1], n[2], d };
	}

	// Line given by p0 + t * p1, l = {x0, y0, z0, x1, y1, z1}.
	// Plane given by ax + by + cz + d = 0, p = {a, b, c, d}.
	static double[] intersectLinePlane(double[] l, double[] p) {
		double p1 = p[0] * l[0] + p[1] * l[1] + p[2] * l[2] + p[3];
		double p2 = p[0] * l[3] + p[1] * l[4] + p[2] * l[5];
		double t = -p1 / p2;
		return new double[]{l[0] + t * l[3], l[1] + t * l[4], l[2] + t * l[5]};
	}

	// From basics!
	static double[] crossProduct(double[] u, double[] v) {
		double[] res = new double[3];
		res[0] = u[1] * v[2] - u[2] * v[1];
		res[1] = u[2] * v[0] - u[0] * v[2];
		res[2] = u[0] * v[1] - u[1] * v[0];
		return res;
	}
}
