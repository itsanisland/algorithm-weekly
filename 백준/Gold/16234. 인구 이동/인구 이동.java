import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0}; // 1:상, 2:하, 3:좌, 4:우
    static final int[] DX = {0, 0, -1, 1};

    static int N, L, R;
    static int[][] A;
    static int[][] B;
    static boolean[][] visited;

    static boolean openBorders() {
        boolean anyOpen = false;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                for (int d = 0; d < 4; d++) {
                    int ny = y + DY[d];
                    int nx = x + DX[d];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || (B[y][x] & (1 << d)) > 0) continue;

                    int diff = Math.abs(A[y][x] - A[ny][nx]);
                    if (L <= diff && diff <= R) {
                        // 내 쪽 국경 열기
                        B[y][x] |= (1 << d);
                        
                        // 상대 쪽 국경도 열기 (정합성)
                        // 0(상)^1=1(하), 2(좌)^1=3(우)
                        B[ny][nx] |= (1 << (d ^ 1));
                        anyOpen = true;
                    }
                }
            }
        }
        return anyOpen;
    }

    static void move(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>(); // 연합에 속한 좌표들 저장
        
        int[] start = {y, x};
        q.offer(start);
        union.add(start);
        visited[y][x] = true;

        int sum = A[y][x];
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            y = cur[0]; x = cur[1];

            for (int d = 0; d < 4; d++) {
                if ((B[y][x] & (1 << d)) == 0) continue;
                
                int ny = y + DY[d];
                int nx = x + DX[d];

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
            B = new int[N][N]; // 각 나라의 국경선 open 여부(상하좌우 비트마스킹)
            visited = new boolean[N][N];

            // 국경선 열기
            if (!openBorders()) break;

            boolean isMoved = false;
    
            // 연합별 인구 이동
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] && B[i][j] > 0) continue;
                    move(i, j);
                    isMoved = true;
                }
            }

            if (!isMoved) break;

            ans++;
        }
        
        System.out.println(ans);
    }
}