import java.util.*;
import java.io.*;

class Main {

    public static class Edge implements Comparable<Edge> {
        int to, w;
        boolean dropped;
        Edge rev;   // 반대 방향 간선

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
            this.dropped = false;
            this.rev = null;
        }

        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public final static int INF = Integer.MAX_VALUE;
    public static List<Edge>[] adj, reverseAdj;
    public static int[] dist;

    public static void dijkstra(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(s, 0));

        Arrays.fill(dist, INF);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.w > dist[cur.to]) continue;
            
            for (Edge next : adj[cur.to]) {
                if (cur.w + next.w < dist[next.to] && !next.dropped) {
                    dist[next.to] = cur.w + next.w;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }

    // 도착지에서부터 역으로 탐색
    public static void trace(int d, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(d);

        boolean[] visited = new boolean[n];
        visited[d] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Edge prev : reverseAdj[cur]) {
                if (dist[cur] == dist[prev.to] + prev.w) {
                    // 간선 제거는 무조건
                    if (!prev.dropped) {
                        prev.dropped = true;
                        prev.rev.dropped = true;
                    }
    
                    // 정점 방문만 제한
                    if (!visited[prev.to]) {
                        visited[prev.to] = true;
                        q.offer(prev.to);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        while (n != 0 && m != 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            adj = new ArrayList[n];
            reverseAdj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
                reverseAdj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                Edge e1 = new Edge(v, p);
                Edge e2 = new Edge(u, p);
                
                e1.rev = e2;
                e2.rev = e1;
                
                adj[u].add(e1);
                reverseAdj[v].add(e2);
            }

            dist = new int[n];

            dijkstra(s);

            trace(d, n);

            dijkstra(s);

            System.out.println(dist[d] == INF ? -1 : dist[d]);

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }
    }
}