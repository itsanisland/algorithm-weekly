import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node implements Comparable<Node> {
		int vertex, dist;

		public Node(int vertex, int dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	private static int T, N, M, K;
	private static List<Node>[] adjList;
	private static int[] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				adjList[a].add(new Node(b, c));
				adjList[b].add(new Node(a, c));
			}
			
			K = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			sum = new int[N + 1];
			
			for (int i = 0; i < K; i++) {
				int k = Integer.parseInt(st.nextToken());
				
				int[] dist = dijkstra(k);
				
				for (int j = 1; j <= N; j++) {
					sum[j] += dist[j];
				}
			}
			
			int min = sum[1]; int ans = 1;
			
			for (int i = 1; i <= N; i++) {
				if (sum[i] < min) {
					min = sum[i];
					ans = i;
				}
			}

			System.out.println(ans);
		}
	}

	private static int[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.dist != dist[cur.vertex]) continue; // 오래된 상태 스킵
			
			for (Node neighbor : adjList[cur.vertex]) {
				int nd = dist[cur.vertex] + neighbor.dist;
				if (nd < dist[neighbor.vertex]) {
					dist[neighbor.vertex] = nd;
					pq.add(new Node(neighbor.vertex, nd));
				}
			}
		}
		
		return dist;
	}

}