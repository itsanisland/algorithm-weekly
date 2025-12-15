import java.util.*;
import java.io.*;

class Main {

    public static class Edge implements Comparable<Edge> {
        int to, w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public final static int INF = Integer.MAX_VALUE;
    public static List<Edge>[] adj;
    public static int[] dist;

    public static void dijkstra(int c) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(c, 0));

        dist[c] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge next : adj[cur.to]) {
                if (cur.w + next.w < dist[next.to]) {
                    dist[next.to] = cur.w + next.w;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adj[b].add(new Edge(a, s));
            }

            dist = new int[n + 1];
            Arrays.fill(dist, INF);

            dijkstra(c);

            int max = 0, cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != INF) {
                    max = Math.max(max, dist[i]);
                    cnt++;
                }
            }
            
            System.out.println(cnt + " " + max);
        }
    }
}