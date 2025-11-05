import java.util.*;
import java.io.*;

class Main {

    private static final int INF = Integer.MAX_VALUE;
    
    private static List<Node>[] graph;
    private static int[] dist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, w));
        }

        dist = new int[v + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        dijkstra(s);

        for (int i = 1; i <= v; i++) System.out.println(dist[i] == INF ? "INF" : dist[i]);
    }

    private static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node from = pq.poll();

            if (from.w > dist[from.idx]) continue;

            for (Node to : graph[from.idx]) {
                int nextW = from.w + to.w;

                if (nextW < dist[to.idx]) {
                    dist[to.idx] = nextW;
                    pq.offer(new Node(to.idx, nextW));
                }
            }
        }
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