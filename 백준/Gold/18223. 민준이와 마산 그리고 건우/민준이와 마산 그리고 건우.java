import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static class Edge implements Comparable<Edge> {
		int vertex, dist;
		
		public Edge(int vertex, int dist) {
			this.vertex = vertex;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	private static int V, E, P;
	private static List<Edge>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) adjList[i] = new ArrayList<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Edge(to, dist));
			adjList[to].add(new Edge(from, dist));
		}
		
		int[] dist1 = dijkstra(1);
		int[] dist2 = dijkstra(P);
		
		if (dist1[V] == dist1[P] + dist2[V]) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
	}
	
	private static int[] dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
			
			if (current.dist != dist[current.vertex]) continue; // 오래된 노드 스킵
			
			for (Edge neighbor : adjList[current.vertex]) {
				int nd = dist[current.vertex] + neighbor.dist;
				if (nd < dist[neighbor.vertex]) {
					dist[neighbor.vertex] = dist[current.vertex] + neighbor.dist;
					pq.offer(new Edge(neighbor.vertex, nd));
				}
			}
		}
		
		return dist;
	}

}