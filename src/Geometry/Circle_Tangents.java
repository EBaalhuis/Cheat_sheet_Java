package Geometry;

public class Circle_Tangents {
	
	// Input two circles by [x, y, r]. Returns [phi, delta] such
	// that the angels of the outer tangents are phi +/- delta.
	static double[] circleTan(double[] c1, double[] c2) {
		double dx = (c1[0]-c2[0]);
		double dy = (c1[1]-c2[1]);
		double d = Math.sqrt(dx*dx+dy*dy);
		return new double[] {
		Math.atan(dy/dx),
		Math.asin((c1[2]+c2[2])/d) };
	}
	
}