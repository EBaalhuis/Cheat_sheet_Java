package Geometry;

// Exact way of testing point on line
public class Exact {
	
	static class P {
		long x, y;
		
		P(long _x, long _y) {
			x = _x;
			y = _y;
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
	}
	
	static boolean PointOnLine(P p, L l) {
		if (l.seg && (p.x < Math.min(l.a.x, l.b.x) || p.x > Math.max(l.a.x, l.b.x))) {
			return false;
		}
		if (l.seg && (p.y < Math.min(l.a.y, l.b.y) || p.y > Math.max(l.a.y, l.b.y))) {
			return false;
		}
		long dx = l.b.x - l.a.x;
		long dy = l.b.y - l.a.y;

		long g = gcd(dx, dy);
		dx = dx / g;
		dy = dy / g;

		long x = p.x - l.b.x;
		long y = p.y - l.b.y;

		if (dx == 0) return p.x == l.a.x;
		if (x % dx != 0) return false;
		return (x / dx) * dy == y;
	}
	
	static long gcd(long p, long q) {
		return q == 0 ? Math.abs(p) : gcd(q, p % q);
	}
}
