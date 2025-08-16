import java.io.*;
import java.util.*;

public class Main {

    static int N, M, D;
    static int[][] origin;
    static int[] archers = new int[3];
    static int answer = 0;

    static final int EMPTY = 0;
    static final int ENEMY = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                origin[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 위치 조합 (열 인덱스 0..M-1 중 3개)
        comb(0, 0);
        System.out.println(answer);
    }

    // 궁수 조합
    static void comb(int idx, int start) {
        if (idx == 3) {
            answer = Math.max(answer, simulate(archers));
            return;
        }
        for (int c = start; c < M; c++) {
            archers[idx] = c;
            comb(idx + 1, c + 1);
        }
    }

    // 시뮬레이션: 타깃 동시 제거 → 적 하강 반복
    static int simulate(int[] archers) {
        int[][] map = copy(origin);
        int kills = 0;

        while (!isEmpty(map)) {
            // 1) 세 궁수 타깃 선정 (동시성 위해 표기만)
            boolean[][] marked = new boolean[N][M];

            for (int a = 0; a < 3; a++) {
                int ax = archers[a];
                int[] target = findTargetBFS(map, ax);
                if (target != null) {
                    marked[target[0]][target[1]] = true;
                }
            }

            // 2) 동시 제거
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (marked[r][c] && map[r][c] == ENEMY) {
                        map[r][c] = EMPTY;
                        kills++;
                    }
                }
            }

            // 3) 적 하강
            moveDown(map);
        }
        return kills;
    }

    /**
     * 타깃 선정 BFS
     * - 궁수 위치는 (N, ax) (맵 바로 아래 한 줄)
     * - 거리 우선(깊이 증가), 같은 거리면 왼쪽 우선 보장 위해
     *   확장 순서를 "좌, 상, 우"로 한다.
     * - 거리 제한 D
     */
    static int[] findTargetBFS(int[][] map, int ax) {
        // (y, x, dist)
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M]; // y = N 허용
        
        q.offer(new int[]{N, ax, 0});
        visited[N][ax] = true;

        // 좌, 상, 우 (왼쪽 우선 보장)
        int[] dy = {0, -1, 0};
        int[] dx = {-1, 0, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], d = cur[2];

            for (int dir = 0; dir < 3; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                int nd = d + 1;

                if (nd > D) continue;
                if (nx < 0 || nx >= M || ny < 0 || ny > N) continue;
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;

                // 맵 내부 도달했고 적이면 타깃 확정
                if (ny >= 0 && ny < N && map[ny][nx] == ENEMY) {
                    return new int[]{ny, nx};
                }
                // 맵 내부가 아니어도(ny == N) 계속 탐색 가능
                q.offer(new int[]{ny, nx, nd});
            }
        }
        return null;
    }

    static void moveDown(int[][] map) {
        for (int r = N - 1; r >= 1; r--) {
            map[r] = Arrays.copyOf(map[r - 1], M);
        }
        Arrays.fill(map[0], EMPTY);
    }

    static boolean isEmpty(int[][] map) {
        for (int[] row : map) {
            for (int v : row) if (v == ENEMY) return false;
        }
        return true;
    }

    static int[][] copy(int[][] src) {
        int[][] dst = new int[N][M];
        for (int i = 0; i < N; i++) dst[i] = Arrays.copyOf(src[i], M);
        return dst;
    }
}