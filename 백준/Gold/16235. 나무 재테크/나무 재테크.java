import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] land, A;
    static Deque<Integer>[][] trees;

    static final int[] dy = {-1,-1,-1,0,0,1,1,1};
    static final int[] dx = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        land = new int[N][N];
        A = new int[N][N];
        trees = new ArrayDeque[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                land[i][j] = 5;
                trees[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[y][x].add(age);
        }

        while (K-- > 0) {
            springAndSummer();
            fall();
            winter();
        }

        int ans = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                ans += trees[i][j].size();

        System.out.println(ans);
    }

    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].isEmpty()) continue;
    
                Deque<Integer> alive = new ArrayDeque<>();
                int nutrition = land[i][j];
                int deadSum = 0;
    
                while (!trees[i][j].isEmpty()) {
                    int age = trees[i][j].pollFirst();
                    if (nutrition >= age) {
                        nutrition -= age;
                        alive.addLast(age + 1);
                    } else {
                        deadSum += age / 2;
                    }
                }
    
                land[i][j] = nutrition + deadSum;
                trees[i][j] = alive;
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 != 0) continue;

                    for (int d = 0; d < 8; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                        trees[ny][nx].addFirst(1);
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                land[i][j] += A[i][j];
    }
}
