import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    static class Road implements Comparable<Road> {
        int node, dist;
        
        Road(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        
        public int compareTo(Road o) {
            return this.dist - o.dist;
        }
    }
    
    public int solution(int N, int[][] road, int K) {        
        int[][] graph = new int[N + 1][N + 1];
        
        for (int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int dist = road[i][2];
            
            if (graph[from][to] > 0) {
                graph[from][to] = Math.min(graph[from][to], dist);
                graph[to][from] = Math.min(graph[to][from], dist);
            } else {
                graph[from][to] = dist;
                graph[to][from] = dist;
            }
        }
        
        return dijkstra(graph, N, K);
    }
    
    int dijkstra(int[][] graph, int N, int K) {
        int[] dist = new int[N + 1];
        for (int i = 0; i <= N; i++) dist[i] = INF;
        dist[1] = 0;
        
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));
        
        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            
            for (int next = 1; next <= N; next++) {
                if (graph[cur.node][next] > 0) {
                    if (dist[cur.node] + graph[cur.node][next] < dist[next]) {
                        dist[next] = dist[cur.node] + graph[cur.node][next];
                        pq.offer(new Road(next, dist[next]));
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) cnt++;
        }
        
        return cnt;
    }
}