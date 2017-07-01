package Geometry;

public class Basics {

	static final double EPS = 0.000000001;

	static class P {
		double x, y;

		P(double _x, double _y) {
			x = _x;
			y = _y;
		}

		public P add(P o) {
			return new P(x + o.x, y + o.y);
		}

		public P sub(P o) {
			return new P(x - o.x, y - o.y);
		}

		public double dist(P o) {
			return Math.sqrt((x - o.x) * (x - o.x) + (y - o.y) * (y - o.y));
		}

		public double abs() {
			return Math.sqrt(x * x + y * y);
		}

		public P sc(double t) {
			return new P(t * x, t * y);
		}
	}

	static class L {
		P a, b;
		boolean seg;

		L(P _a, P _b, boolean s) {
			a = _a;
			b = _b;
			seg = s;
		}

		public boolean degen() {
			return Math.abs(a.x - b.x) < EPS && Math.abs(a.y - b.y) < EPS;
		}
	}

	static double dot(P a, P b) {
		return a.x * b.x + a.y * b.y;
	}

	static double cross(P a, P b) {
		return a.x * b.y - a.y * b.x;
	}

	static double angle(P a, P b, P c) {
		return Math.acos(dot(b.sub(a), c.sub(b)) / b.sub(a).abs() / c.sub(b).abs());
	}

	static P proj(P p, L l) {
		if (l.degen()) {
			return l.a;
		}
		if (l.seg) {
			if (dot(l.b.sub(l.a), p.sub(l.b)) > 0)
				return l.b;
			if (dot(l.a.sub(l.b), p.sub(l.a)) > 0)
				return l.a;
		}
		double t = dot(p.sub(l.a), l.b.sub(l.a)) / l.b.sub(l.a).abs();
		P dir = l.b.sub(l.a).sc(1 / l.b.sub(l.a).abs());
		return l.a.add(dir.sc(t));
	}

	static double distLinePoint(P p, L l) {
		P q = proj(p, l);
		return q.dist(p);
	}

	static double ccw(P a, P b, P c) {
		return cross(b.sub(a), b.sub(c));
	}

	static boolean collinear(P a, P b, P c) {
		return Math.abs(ccw(a, b, c)) < EPS;
	}

	// Parallel lines give null results!
	static P intersect(L l, L m) {
		double A0 = l.b.y - l.a.y;
		double B0 = l.a.x - l.b.x;
		double C0 = A0 * l.a.x + B0 * l.a.y;
		double A1 = m.b.y - m.a.y;
		double B1 = m.a.x - m.b.x;
		double C1 = A1 * m.a.x + B1 * m.a.y;
		double D = A0 * B1 - A1 * B0;
		if (D == 0)
			return null;

		double x = (B1 * C0 - B0 * C1) / D;
		double y = (A0 * C1 - A1 * C0) / D;

		if (!l.seg && !m.seg) {
			return new P(x, y);
		} else {
			P p = new P(x, y);
			if (l.seg && distLinePoint(p, l) > EPS) {
				return null;
			}
			if (m.seg && distLinePoint(p, m) > EPS) {
				return null;
			}
			return p;
		}
	}

	// Should test with line segment intersect / manhattan positioning system
	static L segment_intersect(L l, L m) {
		if (!collinear(l.a, l.b, m.a) || !collinear(l.a, l.b, m.b)) {
			P p = intersect(l, m);
			return p == null ? null : new L(p, p, true);
		} else {
			P[] pt = new P[] { l.a, l.b, m.a, m.b };
			double[] d = new double[] { distLinePoint(l.a, m), distLinePoint(l.b, m), distLinePoint(m.a, l),
					distLinePoint(m.b, l), 0, 0 };
			if (d[0] < EPS && d[1] < EPS) {
				return new L(pt[0],pt[1],true);
			}
			if (d[2] < EPS && d[3] < EPS) {
				return new L(pt[2],pt[3],true);
			}
			if (d[0] > EPS && d[1] > EPS && d[2] > EPS && d[3] > EPS) {
				return null;
			}
			if (d[0] < EPS) {
				if (d[2] < EPS) {
					return new L(pt[0],pt[2],true);
				} else {
					return new L(pt[0],pt[3],true);
				}
			} else {
				if (d[2] < EPS) {
					return new L(pt[1],pt[2],true);
				} else {
					return new L(pt[1],pt[3],true);
				}
			}
		}
	}

	static boolean sameSide(L l, P p, P q) {
		P u = l.b.sub(l.a);
		P v = p.sub(l.a);
		P w = q.sub(l.a);
		return cross(u, v) * cross(u, w) > -EPS;
	}

	
}
