import java.util.*;

class Solution {
    
    private static final int INF = Integer.MAX_VALUE;
    
    private static class Road implements Comparable<Road> {
        int to, dist;
        
        Road(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Road other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
    
    public int solution(int N, int[][] road, int K) {        
        List<Road>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        for (int[] r : road) {
            int from = r[0];
            int to = r[1];
            int dist = r[2];
            graph[from].add(new Road(to, dist));
            graph[to].add(new Road(from, dist));
        }
        
        return dijkstra(graph, N, K);
    }
    
    private int dijkstra(List<Road>[] graph, int N, int K) {
        int[] dist = new int[N + 1];
        for (int i = 0; i <= N; i++) dist[i] = INF;
        dist[1] = 0;
        
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));
        
        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            
            // 우선순위 큐에서 꺼낸 값보다 이미 더 짧은 거리로 갱신된 경우
            // 불필요한 연산을 줄이는 최적화 코드
            if (cur.dist > dist[cur.to]) continue;
            
            for (Road next : graph[cur.to]) {
                if (dist[cur.to] + next.dist < dist[next.to]) {
                    dist[next.to] = dist[cur.to] + next.dist;
                    pq.offer(new Road(next.to, dist[next.to]));
                }
            }
        }
        
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) count++;
        }
        
        return count;
    }
}