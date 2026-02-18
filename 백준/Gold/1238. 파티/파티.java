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
    static int N, X;

    static int[] dijkstra(List<Road>[] roads) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;
        
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(X, 0));

        while (!pq.isEmpty()) {
            Road cur = pq.poll();

            for (Road next : roads[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.t) {
                    dist[next.e] = dist[cur.e] + next.t;
                    pq.offer(new Road(next.e, dist[next.e]));
                }
            }
        }

        return dist;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        List<Road>[] roads = new ArrayList[N + 1];
        List<Road>[] reverseRoads = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) roads[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) reverseRoads[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            roads[s].add(new Road(e, t));
            reverseRoads[e].add(new Road(s, t));
        }

        int[] dist1 = dijkstra(roads);
        int[] dist2 = dijkstra(reverseRoads);

        int max = 0;

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dist1[i] + dist2[i]);
        }

        System.out.println(max);
    }
}