package Geometry;

public class Circle_Tangents {
	
	// Input two circles by [x, y, r]. Returns [phi, delta] such
	// that the angels of the outer tangents are phi +/- delta.
	static double[] cirleTan(double[] c1, double[] c2) {
		double[] res = new double[2];
		double dx = (c1[0]-c2[0]);
		double dy = (c1[1]-c2[1]);
		double d = Math.sqrt(dx*dx+dy*dy);
		res[0] = Math.atan(dy/dx);
		res[1] = Math.asin((c1[2]+c2[2])/d);
		return res;
	}
	
}