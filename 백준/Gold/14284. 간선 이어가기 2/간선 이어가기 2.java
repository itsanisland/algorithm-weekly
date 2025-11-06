import java.util.*;
import java.io.*;

class Main {

    private final static int INF = Integer.MAX_VALUE;
    private static List<Node>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, w));
            graph[to].add(new Node(from, w));
        }

        st = new StringTokenizer(br.readLine());
        
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra(s, e, n));
    }

    private static int dijkstra(int s, int e, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        
        while (!pq.isEmpty()) {
            Node from = pq.poll();

            if (from.w > dist[from.idx]) continue;

            for (Node to : graph[from.idx]) {
                int toW = from.w + to.w;
                if (toW < dist[to.idx]) {
                    dist[to.idx] = toW;
                    pq.offer(new Node(to.idx, toW));
                }
            }
        }

        return dist[e];
    }

    private static class Node implements Comparable<Node> {
        int idx, w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}