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
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>(); // Arrays.fill로 하면 절대 안됨!

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, w, 0));
            graph[to].add(new Node(from, w, 0));
        }

        int answer = dijkstra(a, b, c, n);
        
        System.out.println(answer == INF ? -1 : answer);
    }

    private static int dijkstra(int a, int b, int c, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(a, 0, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[a] = 0;

        int[] min = new int[n + 1];
        Arrays.fill(min, INF);
        
        while (!pq.isEmpty()) {
            Node from = pq.poll();

            if (from.w > c || from.w == INF) continue;

            if (from.idx == b) return min[b];

            for (Node to : graph[from.idx]) {
                int toW = from.w + to.w;
                int max = Math.max(from.max, to.w);

                if (toW <= c && max <= min[to.idx]) {
                    dist[to.idx] = toW;
                    min[to.idx] = max;
                    pq.offer(new Node(to.idx, toW, max));
                }
            }
        }

        return -1;
    }

    private static class Node implements Comparable<Node> {
        int idx, w, max;

        Node(int idx, int w, int max) {
            this.idx = idx;
            this.w = w;
            this.max = max;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}