import java.util.*;
import java.io.*;

class Main {

    public static int N, L;
    public static int[][] map;

    public static boolean checkR(int idx) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (map[i][idx] == map[i + 1][idx]) continue;
            else if (map[i][idx] - map[i + 1][idx] == 1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || map[i + 1][idx] != map[j][idx] || used[j]) return false;
                    used[j] = true;
                }
            } else if (map[i][idx] - map[i + 1][idx] == -1) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || map[i][idx] != map[j][idx] || used[j]) return false;
                    used[j] = true;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean checkC(int idx) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (map[idx][i] == map[idx][i + 1]) continue;
            else if (map[idx][i] - map[idx][i + 1] == 1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || map[idx][i + 1] != map[idx][j] || used[j]) return false;
                    used[j] = true;
                }
            } else if (map[idx][i] - map[idx][i + 1] == -1) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || map[idx][i] != map[idx][j] || used[j]) return false;
                    used[j] = true;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (checkR(i)) cnt++;
            if (checkC(i)) cnt++;
        }

        System.out.println(cnt);
    }
}