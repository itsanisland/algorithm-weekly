import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static int SIZE;
    static int[][] A;
    
    // 시계 방향 90도 회전
    static void rotate(int y, int x, int size) {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = A[y + size - j - 1][x + i];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[y + i][x + j] = tmp[i][j];
            }
        }
    }

    // 특정 칸에 인접해 있는 칸 중
    // 얼음이 있는 칸의 개수가 3 미만이면
    // 해당 칸의 얼음 양 1 줄어듦
    static void melt(int y, int x, int[][] m) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int ny = y + DY[d];
            int nx = x + DX[d];

            if (ny < 0 || ny >= SIZE || nx < 0 || nx >= SIZE || A[ny][nx] == 0) continue;
            cnt++;
        }
        if (cnt < 3) m[y][x] = 1;
    }

    static int getMaxCnt(int y, int x, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y, x});

        visited[y][x] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;

            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + DY[d];
                int nx = cur[1] + DX[d];
    
                if (ny < 0 || ny >= SIZE || nx < 0 || nx >= SIZE || visited[ny][nx] || A[ny][nx] == 0) continue;

                visited[ny][nx] = true;
                q.offer(new int[] {ny, nx});
            }
        }
        return cnt;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        SIZE = 1 << N;
        A = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {
            int L = Integer.parseInt(st.nextToken());
            int size = 1 << L;

            for (int i = 0; i < SIZE; i += size) {
                for (int j = 0; j < SIZE; j += size) {
                    rotate(i, j, size);
                }
            }

            int[][] m = new int[SIZE][SIZE];
            
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (A[i][j] > 0) {
                        melt(i, j, m);
                    }
                }
            }

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    A[i][j] -= m[i][j];
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sum += A[i][j];
            }
        }

        boolean[][] visited = new boolean[SIZE][SIZE];
        int max = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (A[i][j] == 0 || visited[i][j]) continue;
                max = Math.max(max, getMaxCnt(i, j, visited));
            }
        }
        
        System.out.println(sum);
        System.out.println(max);
    }
}