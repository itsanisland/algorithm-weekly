import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {-1, 1, 0, 0};
    static final int[] DX = {0, 0, -1, 1};

    static final int[] TDY = {0, 1, 0, -1};
    static final int[] TDX = {-1, 0, 1, 0};

    static int N, sxy;
    static int[][] board;
    static int[] m;

    static void destroy(int d, int s) {
        int y = sxy, x = sxy;
        for (int i = 0; i < s; i++) {
            y += DY[d];
            x += DX[d];

            // m[board[y][x]]++;
            board[y][x] = 0;
        }
    }

    static List<Integer> flat() {
        List<Integer> ret = new ArrayList();
        ret.add(0);
        
        int y = sxy, x = sxy;
        int l = 1, d = 0;

        while (l <= N) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < l; j++) {
                    if (y == 0 && x == 0) break;
                    y += TDY[d];
                    x += TDX[d];              
                    if (board[y][x] > 0) ret.add(board[y][x]);
                }
                d = (d + 1) % 4;
            }
            l++;
        }
        
        return ret;
    }

    static boolean explode(List<Integer> list) {
        boolean exploded = false;
        int size = list.size();
        int s = 1;
        int l = 1;
        
        for (int i = 2; i < size; i++) {
            if (list.get(i) == list.get(s)) {
                l++;
            } else {
                if (l >= 4) {
                    m[list.get(s)] += l;

                    for (int j = 0; j < l; j++) {
                        list.remove(s);
                    }
                    i -= l;
                    size -= l;
                    exploded = true;
                }
                s = i;
                l = 1;
            }
        }

        // 마지막 그룹 처리
        if (l >= 4) {
            m[list.get(s)] += l;
            for (int j = 0; j < l; j++) {
                list.remove(s);
            }
            exploded = true;
        }

        return exploded;
    }

    static List<Integer> change(List<Integer> list) {
        List<Integer> ret = new ArrayList();
        ret.add(0);
        
        int size = list.size();
        int s = 1;
        int l = 1;
        
        for (int i = 2; i < size; i++) {
            if (list.get(i) == list.get(s)) {
                l++;
            } else {
                ret.add(l);
                ret.add(list.get(s));
                
                s = i;
                l = 1;
            }
        }

        // 마지막 그룹 처리
        if (list.size() > 1 && list.get(size - 1) == list.get(s)) {  // Index Out Of Bounds 주의
            ret.add(l);
            ret.add(list.get(s));
        }
        
        return ret;
    }

    static void unflat(List<Integer> list) {
        board = new int[N][N];
        
        int y = sxy, x = sxy;
        int l = 1, d = 0;
        int idx = 1;

        while (l <= N) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < l; j++) {
                    if (y == 0 && x == 0) break;
                    if (idx >= list.size()) break;
                    y += TDY[d];
                    x += TDX[d];
                    board[y][x] = list.get(idx++);
                }
                d = (d + 1) % 4;
            }
            l++;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        m = new int[4];
        sxy = (N - 1) / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 얼음 파편으로 구슬 파괴
            destroy(d, s);

            // 2차원 -> 1차원
            List<Integer> list = flat();

            // 연속 구슬 폭발 + 구슬 이동(없을 때까지)
            while (true) {
                boolean ck = explode(list);
                if (!ck) break;
            }
            
            // 구슬 변화
            list = change(list);

            // 1차원 -> 2차원
            unflat(list);
        }

        int ans = 0;
        for (int i = 1; i < 4; i++) {
            ans += i * m[i];
        }

        System.out.println(ans);
    }
}