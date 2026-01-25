import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, 1, -1};
    
    static int N, M, ans;
    static List<int[]> empty, virus; // 빈 칸(조합 선택 가능), 바이러스 따로 관리(전체 맵 탐색 X)
    static int[][] map;

    static void spreadVirus() {
        boolean[][] visited = new boolean[N][M];
        
        Queue<int[]> q = new ArrayDeque<>();
        for (int[] v : virus) q.offer(v);

        int cnt = 0; // 감염 수
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited[cur[0]][cur[1]] = true;
            
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + DY[i];
                int nx = cur[1] + DX[i];
                
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (map[ny][nx] != 0 || visited[ny][nx]) continue;
                
                q.offer(new int[] {ny, nx});
                visited[ny][nx] = true;
                cnt++;
            }
        }

        cnt = empty.size() - 3 - cnt;
        ans = Math.max(ans, cnt);
    }

    static void dfs(int start, int cnt) {
        if (cnt == 3) {
            spreadVirus();
            return;
        }

        for (int i = start; i < empty.size(); i++) {
            int[] selected = empty.get(i);

            map[selected[0]][selected[1]] = 1; // 벽 설치
            dfs(i + 1, cnt + 1);
            map[selected[0]][selected[1]] = 0; // 원상복구
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        empty = new ArrayList<>();
        virus = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) empty.add(new int[] {i, j});
                if (map[i][j] == 2) virus.add(new int[] {i, j});
            }
        }

        dfs(0, 0);
        
        System.out.println(ans);
    }
}