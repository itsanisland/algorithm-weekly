import java.util.*;
import java.io.*;

class Main {

    static class Road implements Comparable<Road> {
        int e, t;
        
        Road(int e, int t) {
            this.e = e;
            this.t = t;
        }

        public int compareTo(Road other) {
            return this.t - other.t;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static List<Road>[] roads;

    static void dijkstra(int start, int[] dist) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(start, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Road cur = pq.poll();

            for (Road next : roads[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.t) {
                    dist[next.e] = dist[cur.e] + next.t;
                    pq.offer(new Road(next.e, dist[next.e]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        roads = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) roads[i] = new ArrayList<>();

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            roads[s].add(new Road(e, t));
        }

        dijkstra(X, dist);

        int[] dist2 = new int[N + 1];
        int max = 0;

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist2, INF);
            dijkstra(i, dist2);
            max = Math.max(max, dist[i] + dist2[X]);
        }

        System.out.println(max);
    }
}