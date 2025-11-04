import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    List<Node>[] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int[] fare : fares) {
            int v1 = fare[0];
            int v2 = fare[1];
            int cost = fare[2];
            
            graph[v1].add(new Node(v2, cost));
            graph[v2].add(new Node(v1, cost));
        }
        
        int[] distFromS = dijkstra(s, n);
        int answer = distFromS[a] + distFromS[b];
        
        for (int k = 1; k <= n; k++) {
            int[] distFromK = dijkstra(k, n);
            answer = Math.min(answer, distFromS[k] + distFromK[a] + distFromK[b]);
        }
        
        return answer;
    }
    
    private int[] dijkstra(int s, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        
        while (!pq.isEmpty()) {
            Node from = pq.poll();
            
            if (from.cost > dist[from.idx]) continue;
            
            for (Node to : graph[from.idx]) {
                int nextCost = from.cost + to.cost;
                if (nextCost < dist[to.idx]) {
                    dist[to.idx] = nextCost;
                    pq.offer(new Node(to.idx, nextCost));
                }
            }
        }
        
        return dist;
    }
    
    private class Node implements Comparable<Node> {
        int idx, cost;
        
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}