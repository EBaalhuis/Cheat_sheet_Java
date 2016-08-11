package Graphs;

import java.util.Arrays;

// All pairs shortest path. O(V^3).
// Negative weights allowed, but no negative cycles!
public class FloydWarshall {

	static int[][] floydWarshall(int[][] graph) {
		int nV = graph.length;
		int dist[][] = new int[nV][nV];

		for (int i = 0; i < nV; i++) {
			for (int j = 0; j < nV; j++) {
				dist[i][j] = graph[i][j];
			}
		}

		for (int k = 0; k < nV; k++) {
			for (int i = 0; i < nV; i++) {
				for (int j = 0; j < nV; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		return dist;
	}

	public static void main(String[] args) {
		int nV = 4;
		int[][] graph = new int[nV][nV];
		for (int i = 0; i < nV; i++) {
			Arrays.fill(graph[i], 100000);
		}
		for (int i = 0; i < nV; i++) {
			graph[i][i] = 0;
		}

		graph[0][1] = 1;
		graph[1][2] = 1;
		graph[2][3] = 1;

		int[][] res = floydWarshall(graph);
	}
}