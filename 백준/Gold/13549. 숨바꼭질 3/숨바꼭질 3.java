import java.util.*;
import java.io.*;

class Main {

    private static final int INF = Integer.MAX_VALUE;

    private static int[] dist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        dist = new int[200_001];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        System.out.println(dijkstra(s, e));
    }

    private static int dijkstra(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        boolean[][] visited = new boolean[200_001][3];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.w > dist[cur.pos]) continue;
            
            if (cur.pos == e) return cur.w;
            
            for (int i = -1; i <= 1; i++) {
                int next = i == 0 ? 2 * cur.pos : cur.pos + i;
                
                if (0 <= next && next <= 200_000 && !visited[next][i + 1]) {
                    visited[next][i + 1] = true;
                
                    int nd = cur.w + Math.abs(i);
                    if (nd < dist[next]) {
                        dist[next] = nd;
                        pq.offer(new Node(next, nd));
                    }
                }
            }
        }

        return -1;
    }

    private static class Node implements Comparable<Node> {
        int pos, w;

        Node(int pos, int w) {
            this.pos = pos;
            this.w = w;
        }
        
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}