import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    int[][] graph;
    int[] dist;
    boolean[][] visited;
    
    PriorityQueue<Node> pq;
    
    public int solution(int N, int[][] road, int K) {
        graph = new int[N + 1][N + 1];
        
        for (int i = 0; i < road.length; i++) {
            int v1 = road[i][0];
            int v2 = road[i][1];
            int w = road[i][2];

            graph[v1][v2] = graph[v1][v2] == 0 ? w : Math.min(graph[v1][v2], w);
            graph[v2][v1] = graph[v2][v1] == 0 ? w : Math.min(graph[v2][v1], w);
        }
        
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        
        visited = new boolean[N + 1][N + 1];
        
        pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        
        dijkstra(N);
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }
    
    private void dijkstra(int n) {
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int from = cur.v;
            int w = cur.w;

            for (int to = 1; to <= n; to++) {
                if (graph[from][to] == 0 || visited[from][to]) continue;
                
                if (w + graph[from][to] < dist[to]) {
                    visited[from][to] = true;
                    dist[to] = w + graph[from][to];
                    pq.offer(new Node(to, w + graph[from][to]));
                }
            }
        }
    }
    
    private class Node implements Comparable<Node> {
        int v, w;
        
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node e) {
            return this.w - e.w;
        }
    } 
}