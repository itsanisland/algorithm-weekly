import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]>[] boundary;
    
    static void attachNumber(int y, int x, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y, x});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            y = cur[0];
            x = cur[1];
            map[y][x] = num;

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;

                if (map[ny][nx] == 0) {
                    if (boundary[num] == null) boundary[num] = new ArrayList<>();
                    boundary[num].add(new int[] {y, x});
                    continue;
                }
                
                visited[ny][nx] = true;
                q.offer(new int[] {ny, nx});
            }
        }   
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        boundary = new ArrayList[10_000];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                attachNumber(i, j, num++);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                for (int[] yx0 : boundary[i]) {
                    for (int[] yx1 : boundary[j]) {
                        int dist = Math.abs(yx0[0] - yx1[0]) + Math.abs(yx0[1] - yx1[1]) - 1;
                        ans = Math.min(ans, dist);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}