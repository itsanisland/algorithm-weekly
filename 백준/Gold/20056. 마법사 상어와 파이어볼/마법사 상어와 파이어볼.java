import java.io.*;
import java.util.*;

public class Main {

    static class Fireball {
        int m, s, d;
        Fireball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int SIZE = N * N;
        List<Fireball>[] fireballs = new ArrayList[SIZE];
        for (int i = 0; i < SIZE; i++) fireballs[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken()); // 질량
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 방향
            fireballs[r * N + c].add(new Fireball(m, s, d));
        }

        while (K-- > 0) {
            List<Fireball>[] next = new ArrayList[SIZE];
            
            for (int i = 0; i < SIZE; i++) next[i] = new ArrayList<>();

            // 이동
            for (int i = 0; i < SIZE; i++) {
                if (fireballs[i].isEmpty()) continue;

                int r = i / N;
                int c = i % N;
                
                for (Fireball fb : fireballs[i]) {
                    int nr = r + DR[fb.d] * (fb.s % N);
                    int nc = c + DC[fb.d] * (fb.s % N);

                    nr = (nr + N) % N;
                    nc = (nc + N) % N;

                    next[nr * N + nc].add(fb);
                }
            }
            
            fireballs = next;

            // 같은 칸에 여러 파이어볼 있을 경우 합치기
            for (int i = 0; i < SIZE; i++) {
                if (fireballs[i].size() <= 1) continue;

                int r = i / N;
                int c = i % N;

                int size = fireballs[i].size();
                int m = 0, s = 0;
                int evenCnt = 0;
                
                for (Fireball fb : fireballs[i]) {
                    m += fb.m;
                    s += fb.s;
                    if (fb.d % 2 == 0) evenCnt++;
                }

                m /= 5;
                s /= size;

                fireballs[i].clear(); // 반드시 기존 파이어볼 지우기

                if (m == 0) continue;

                int start = 1;
                if (evenCnt == 0 || evenCnt == size) start = 0;
                
                for (int d = start; d < 8; d+=2) {
                    fireballs[i].add(new Fireball(m, s, d));
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < SIZE; i++) {
            if (fireballs[i].size() == 0) continue;
            for (Fireball fb : fireballs[i]) ans += fb.m;
        }

        System.out.println(ans);
    }
}