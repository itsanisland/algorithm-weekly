import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[][] fishes;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fishes = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fishes[0][i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        while (true) {
            if (checkDiff()) break;
            
            ans++;
            // 1. 최소 물고기 어항에 1마리 추가
            addOneToMin();
            
            // 2. 첫 번째 어항 쌓기 (90도 회전)
            stackFirst();
            
            // 3. 물고기 수 조절 및 평면화
            adjustAndFlatten();
            
            // 4. 두 번째 어항 쌓기 (180도 회전 2번)
            stackSecond();
            
            // 5. 다시 조절 및 평면화
            adjustAndFlatten();
        }

        System.out.println(ans);
    }

    // 종료 조건 체크
    static boolean checkDiff() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, fishes[0][i]);
            max = Math.max(max, fishes[0][i]);
        }
        return (max - min) <= K;
    }

    static void addOneToMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) min = Math.min(min, fishes[0][i]);
        for (int i = 0; i < N; i++) {
            if (fishes[0][i] == min) fishes[0][i]++;
        }
    }

    static void stackFirst() {
        int w = 1; // 공중 부양 뭉치 가로
        int h = 1; // 공중 부양 뭉치 세로
        int startCol = 0;

        while (startCol + w + h <= N) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 90도 회전 공식: (r, c) -> (c, h-1-r)
                    // 여기에 바닥 위(1층)부터 쌓아야 하므로 행에 w-j, 열에 startCol+w+i
                    fishes[w - j][startCol + w + i] = fishes[i][startCol + j];
                    fishes[i][startCol + j] = 0;
                }
            }
            startCol += w;
            int nextW = h;
            int nextH = w + 1;
            w = nextW;
            h = nextH;
        }
    }

    static void adjustAndFlatten() {
        // 1. 물고기 수 조절
        int[][] diff = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (fishes[r][c] == 0) continue;
                for (int d = 0; d < 2; d++) { // 오른쪽과 아래만 체크 (중복 방지)
                    int nr = r + dy[d];
                    int nc = c + dx[d];
                    if (nr < N && nc < N && fishes[nr][nc] > 0) {
                        int dVal = Math.abs(fishes[r][c] - fishes[nr][nc]) / 5;
                        if (dVal > 0) {
                            if (fishes[r][c] > fishes[nr][nc]) {
                                diff[r][c] -= dVal;
                                diff[nr][nc] += dVal;
                            } else {
                                diff[r][c] += dVal;
                                diff[nr][nc] -= dVal;
                            }
                        }
                    }
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) fishes[r][c] += diff[r][c];
        }

        // 2. 평면화 (Flatten)
        int[] temp = new int[N];
        int idx = 0;
        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {
                if (fishes[r][c] == 0) break;
                temp[idx++] = fishes[r][c];
                fishes[r][c] = 0;
            }
        }
        for (int i = 0; i < N; i++) fishes[0][i] = temp[i];
    }

    static void stackSecond() {
        // 1회차: N/2개를 180도 회전해서 쌓기 (1층 -> 2층)
        int w = N / 2;
        for (int j = 0; j < w; j++) {
            fishes[1][N - 1 - j] = fishes[0][j];
            fishes[0][j] = 0;
        }

        // 2회차: 왼쪽 N/4개 뭉치(2층짜리)를 180도 회전해서 오른쪽 위에 쌓기 (2층 -> 4층)
        int start = N / 2;
        int nextW = N / 4;
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < nextW; c++) {
                // 180도 회전: 상하반전(1-r), 좌우반전(start+nextW+ (nextW-1-c))
                fishes[3 - r][start + nextW + (nextW - 1 - c)] = fishes[r][start + c];
                fishes[r][start + c] = 0;
            }
        }
    }
}