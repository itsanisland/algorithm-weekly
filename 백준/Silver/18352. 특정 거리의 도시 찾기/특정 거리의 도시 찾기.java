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
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, 1));
        }

        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[x] = 0;

        pq = new PriorityQueue<>();
        pq.offer(new Node(x, 0));

        dijkstra();

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) list.add(i);
        }

        Collections.sort(list);

        if (list.size() == 0) System.out.println(-1);
        else {
            for (int i = 0; i < list.size(); i++) System.out.println(list.get(i));
        }
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