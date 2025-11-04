import java.util.*;
import java.io.*;

class Main {

    private static final int INF = Integer.MAX_VALUE;
    
    private static List<Node>[] graph;
    private static int[] dist;
    private static PriorityQueue<Node> pq;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        graph = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            graph[i] = new ArrayList<>();

            if (i == d) break;
            graph[i].add(new Node(i + 1, 1));
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (to <= d) graph[from].add(new Node(to, w));
        }

        dist = new int[d + 1];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        dijkstra();

        int answer = dist[d];

        System.out.println(answer);
    }

    private static void dijkstra() {
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