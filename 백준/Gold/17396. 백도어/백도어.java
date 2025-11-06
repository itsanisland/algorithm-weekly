import java.util.*;
import java.io.*;

class Main {

    private final static long INF = Long.MAX_VALUE / 4;
    private static List<Node>[] graph;
    private static int[] a;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, w));
            graph[to].add(new Node(from, w));
        }

        long answer = dijkstra(0, n - 1, n);
        
        System.out.println(answer == INF ? -1 : answer);
    }

    private static long dijkstra(int s, int e, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        
        while (!pq.isEmpty()) {
            Node from = pq.poll();

            if (from.w > dist[from.idx]) continue;

            if (from.idx == e) return from.w;

            for (Node to : graph[from.idx]) {
                if (to.idx != n - 1 && a[to.idx] == 1) continue;
                
                long toW = from.w + to.w;
                if (toW < dist[to.idx]) {
                    dist[to.idx] = toW;
                    pq.offer(new Node(to.idx, toW));
                }
            }
        }

        return -1;
    }

    private static class Node implements Comparable<Node> {
        int idx;
        long w;

        Node(int idx, long w) {
            this.idx = idx;
            this.w = w;
        }

        public int compareTo(Node o) {
            return Long.compare(this.w, o.w);
        }
    }
}