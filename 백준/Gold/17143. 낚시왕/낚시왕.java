import java.util.*;
import java.io.*;

class Main {

    static class Shark {
        int s, d, z;

        Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static final int[] DY = {0, -1, 1, 0, 0};
    static final int[] DX = {0, 0, 0, 1, -1};
    
    static int R, C, ans;
    static Shark[][] sharks;

    static void catchSharks(int c) {
        for (int r = 1; r <= R; r++) {
            if (sharks[r][c] != null) {
                ans += sharks[r][c].z;
                sharks[r][c] = null;
                break;
            }
        }
    }

    static void moveSharks() {
        // 새로운 상어 배열 필요(아직 안 움직인 상어들과 충돌, 동시 이동 처리)
        Shark[][] tmp = new Shark[R + 1][C + 1];
        
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                Shark shark = sharks[r][c];
                
                if (shark == null) continue;

                int nr = r;
                int nc = c;

                int speed = shark.s;

                // 1 → 2 → 3 → 4 → 3 → 2 → 1
                // 총 이동 횟수 = (R - 1) × 2 일 때, 제자리로 돌아옴 -> 주기
                // 실제 의미 있는 이동은 주기를 넘고 남은 횟수만큼만 이동
                if (shark.d == 1 || shark.d == 2) {
                    speed %= (R - 1) * 2;
                } else {
                    speed %= (C - 1) * 2;
                }

                for (int i = 0; i < speed; i++) {
                    if (nr + DY[shark.d] < 1 || nr + DY[shark.d] > R) { // 상 <-> 하
                        shark.d = (shark.d == 1) ? 2 : 1;
                    }
                    
                    if (nc + DX[shark.d] < 1 || nc + DX[shark.d] > C) { // 좌 <-> 우
                        shark.d = (shark.d == 3) ? 4 : 3;
                    }

                    nr += DY[shark.d];
                    nc += DX[shark.d];
                }

                if (tmp[nr][nc] == null) {
                    tmp[nr][nc] = shark;
                } else {
                    if (tmp[nr][nc].z < shark.z) {
                        tmp[nr][nc] = shark;
                    }
                }
            }
        }

        sharks = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        sharks = new Shark[R + 1][C + 1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[r][c] = new Shark(s, d, z);
        }

        for (int c = 1; c <= C; c++) {
            catchSharks(c);
            moveSharks();
        }
        
        System.out.println(ans);
    }
}