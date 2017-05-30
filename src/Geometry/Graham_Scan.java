package Geometry;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Graham_Scan {
	//Find the convex hull of a set of points in 2d.
	//Given List<P> of points, return List<P> with all points on convex hull
	//in ccw order. O(V log V).
    private static boolean leftTurn(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x) >= 0;
    }

    static ArrayList<Point> hull(ArrayList<Point> points) {
        int n = points.size();

        ArrayList<Point> pointsByX = (ArrayList<Point>) points.clone();
        Collections.sort(pointsByX, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                int r = new Integer(o1.x).compareTo(new Integer(o2.x));
                return r == 0 ? new Integer(o1.y).compareTo(new Integer(o2.y)) : r;
            }
        });

        Point[] up = new Point[pointsByX.size()];
        up[0] = pointsByX.get(0);
        up[1] = pointsByX.get(1);

        int upInd = 2;

        for (int i = 2; i < n; i++) {
            up[upInd] = pointsByX.get(i);
            upInd++;

            while (upInd > 2 && leftTurn(up[upInd - 3], up[upInd - 2], up[upInd - 1])) {
                up[upInd - 2] = up[upInd - 1];
                up[upInd - 1] = null;
                upInd--;
            }
        }

        Point[] low = new Point[n];
        low[0] = pointsByX.get(n - 1);
        low[1] = pointsByX.get(n - 2);

        int lowInd = 2;

        for (int i = 3; i <= n; i++) {
            low[lowInd] = pointsByX.get(n - i);
            lowInd++;

            while (lowInd > 2 && leftTurn(low[lowInd - 3], low[lowInd - 2], low[lowInd - 1])) {
                low[lowInd - 2] = low[lowInd - 1];
                low[lowInd - 1] = null;
                lowInd--;
            }
        }

        ArrayList<Point> hull = new ArrayList<Point>(upInd + lowInd);
        for (int i = 0; i < upInd; i++) {
            hull.add(up[i]);
        }

        for (int i = 1; i < lowInd - 1; i++) {
            hull.add(low[i]);
        }

        return hull;
    }

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int nPoints = io.nextInt();
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i < nPoints; i++) {
			points.add(new Point(io.nextInt(), io.nextInt()));
		}

		ArrayList<Point> st = hull(points);
		
		System.out.println(st.size());
	}

	static class IO extends PrintWriter {
		static BufferedReader r;
		static StringTokenizer t;

		public IO(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
			t = new StringTokenizer("");
		}

		public String next() throws IOException {
			while (!t.hasMoreTokens()) {
				t = new StringTokenizer(r.readLine());
			}
			return t.nextToken();
		}

		public int nextInt() throws IOException{
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}
