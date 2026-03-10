import java.util.*;
import java.io.*;

class Main {
    // 0:상, 1:하, 2:좌, 3:우 (X ^ 1 연산으로 반대 방향을 찾기 위해 짝을 맞춤)
    static final int[] DY = {-1, 1, 0, 0}; 
    static final int[] DX = {0, 0, -1, 1};

    static int N, L, R;
    static int[][] A;
    static int[][] B;
    static boolean[][] visited;

    // 1. 모든 칸을 돌며 국경선을 미리 열어둠 (비트마스킹)
    static boolean openBorders() {
        boolean anyOpen = false;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                for (int d = 0; d < 4; d++) {
                    int ny = y + DY[d];
                    int nx = x + DX[d];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

                    int diff = Math.abs(A[y][x] - A[ny][nx]);
                    if (L <= diff && diff <= R) {
                        // 내 쪽 국경 열기
                        if ((B[y][x] & (1 << d)) == 0) {
                            B[y][x] |= (1 << d);
                            // 상대 쪽 국경도 열기 (정합성)
                            // 0(상)^1=1(하), 2(좌)^1=3(우)
                            B[ny][nx] |= (1 << (d ^ 1));
                            anyOpen = true;
                        }
                    }
                }
            }
        }
        return anyOpen;
    }

    // 2. 비트마스킹된 정보를 바탕으로 실질적인 인구 이동 수행
    static void move(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>(); // 연합에 속한 좌표들 저장
        
        int[] start = {y, x};
        q.offer(start);
        union.add(start);
        visited[y][x] = true;

        int sum = A[y][x];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];

            for (int d = 0; d < 4; d++) {
                // 현재 칸의 d방향 국경이 열려 있는지 확인
                if ((B[cy][cx] & (1 << d)) == 0) continue;

                int ny = cy + DY[d];
                int nx = cx + DX[d];

                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                int[] next = {ny, nx};
                q.offer(next);
                union.add(next);
                sum += A[ny][nx];
            }
        }

        // 인구 갱신 (전체 맵을 돌지 않고 연합 리스트만 순회)
        if (union.size() > 1) {
            int avg = sum / union.size();
            for (int[] pos : union) {
                A[pos[0]][pos[1]] = avg;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while (true) {
            B = new int[N][N];
            visited = new boolean[N][N];
            
            // 국경선 먼저 세팅
            if (!openBorders()) break;

            // 세팅된 국경선을 따라 BFS 수행
            boolean movedAnywhere = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 국경이 열려있고 아직 방문하지 않은 경우만 BFS
                    if (!visited[i][j] && B[i][j] > 0) {
                        move(i, j);
                        movedAnywhere = true;
                    }
                }
            }

            if (!movedAnywhere) break;
            ans++;
        }

        System.out.println(ans);
    }
}