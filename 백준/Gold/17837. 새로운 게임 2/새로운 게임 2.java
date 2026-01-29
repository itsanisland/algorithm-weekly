import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {0, 0, -1, 1};
    static final int[] DX = {1, -1, 0, 0};
    
    static int N, K;
    static int[][] map;
    static Deque<Integer>[][] dq;
    static Piece[] pieces;

    static class Piece {
        int y, x, d;
        Piece(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    static int reverse(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    static int play() {
        int turn = 0;

        while (++turn <= 1000) {
            boolean finished = false;
            
            for (int i = 1; i <= K; i++) {
                Piece cur = pieces[i];

                int ny = cur.y + DY[cur.d];
                int nx = cur.x + DX[cur.d];
    
                if (ny < 1 || ny > N || nx < 1 || nx > N || map[ny][nx] == 2) {
                    cur.d = reverse(cur.d);
                    ny = cur.y + DY[cur.d];
                    nx = cur.x + DX[cur.d];
                
                    if (ny < 1 || ny > N || nx < 1 || nx > N || map[ny][nx] == 2)
                        continue;
                }

                Deque<Integer> cq = dq[cur.y][cur.x];
                Deque<Integer> nq = dq[ny][nx];
                Deque<Integer> tq = new ArrayDeque<>();
                List<Integer> movedStack = new ArrayList<>();
                
                switch (map[ny][nx]) {
                    case 0:
                        while (cq.peekLast() != i) tq.offerFirst(cq.pollLast());
                        tq.offerFirst(cq.pollLast());
                        
                        while (!tq.isEmpty()) {
                            int idx = tq.pollFirst();
                            nq.offer(idx);
                            movedStack.add(idx);
                        }
                        break;
                    case 1:
                        while (cq.peekLast() != i) tq.offer(cq.pollLast());
                        tq.offer(cq.pollLast());
 
                        while (!tq.isEmpty()) {
                            int idx = tq.pollFirst();
                            nq.offer(idx);
                            movedStack.add(idx);
                        }
                        break;
                }

                if (nq.size() >= 4) {
                    finished  = true;
                    break;
                }

                for (int idx : movedStack) {
                    pieces[idx].y = ny;
                    pieces[idx].x = nx;
                }
            }

            if (finished) break;
        }
        
        return turn > 1000 ? -1 : turn;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        dq = new ArrayDeque[N + 1][N + 1];
        pieces = new Piece[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                dq[i][j] = new ArrayDeque<>();
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pieces[i] = new Piece(y, x, d - 1);
            dq[y][x].offer(i);
        }
        
        System.out.println(play());
    }
}