import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int vertex;
		int distance;

		Node(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.distance, other.distance);
		}
	}

	private static final int INF = Integer.MAX_VALUE;
	private static int N, M;
	private static List<Node>[] adjList;
	private static int[] distance;
	private static boolean[] visited;
	private static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, distance));
			adjList[to].add(new Node(from, distance));
		}

		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		distance[1] = 0;

		visited = new boolean[N + 1];

		pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			if (visited[current.vertex]) {
				continue;
			}
			visited[current.vertex] = true;

			for (Node neighbor : adjList[current.vertex]) {
				if (!visited[neighbor.vertex]
						&& distance[neighbor.vertex] > distance[current.vertex] + neighbor.distance) {
					distance[neighbor.vertex] = distance[current.vertex] + neighbor.distance;
					pq.offer(new Node(neighbor.vertex, distance[neighbor.vertex]));
				}
			}
		}

		System.out.println(distance[N] != INF ? distance[N] : -1);
	}

}