import java.util.*;
import java.io.*;

class Main {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[] DY = { -1, 1, 0, 0 };
    private static final int[] DX = { 0, 0, -1, 1 };

    private static int K, W, H;
    private static char[][] map;
    private static Map<Character, Integer> dMap;
    private static int[][] dist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
    
            map = new char[H][W];
            dMap = new HashMap<>();
            dist = new int[H][W];
    
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int d = Integer.parseInt(st.nextToken());
                dMap.put(c, d);
            }
    
            int sy = 0, sx = 0;
            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                Arrays.fill(dist[i], INF);
                
                for (int j = 0; j < W; j++) {
                    map[i][j] = s.charAt(j);
    
                    if (map[i][j] == 'E') {
                        sy = i; sx = j;
                    }
                }
            }
            
            System.out.println(dijkstra(sy, sx));
        }
    }

    private static int dijkstra(int sy, int sx) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sy, sx, 0));

        boolean[][] visited = new boolean[H][W];
        visited[sy][sx] = true;
        
        dist[sy][sx] = 0;

        while (!pq.isEmpty()) {
            Node from = pq.poll();

            if (from.w > dist[from.y][from.x]) continue;

            // 경계 도달 시 조기 종료 : 가장 먼저 경계(탈출) 칸을 PQ에서 꺼냈을 때 그 비용이 최소
            if (from.y == 0 || from.y == H - 1 || from.x == 0 || from.x == W - 1) {
                return from.w;
            } 
            
            for (int i = 0; i < 4; i++) {
                int ny = from.y + DY[i], nx = from.x + DX[i];
                
                if (isValid(ny, nx) && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    
                    int nd = from.w + dMap.get(map[ny][nx]);

                    if (nd < dist[ny][nx]) {
                        dist[ny][nx] = nd;
                        pq.offer(new Node(ny, nx, nd));
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < H && 0 <= x && x < W;
    }

    private static class Node implements Comparable<Node> {
        int y, x, w;

        Node(int y, int x, int w) {
            this.y = y;
            this.x = x;
            this.w = w;
        }
        
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}