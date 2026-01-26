import java.util.*;
import java.io.*;

class Main {

    static int[][] A = new int[100][100];
    static int R = 3, C = 3;

    static void operateR() {
        int maxLen = C;
        
        for (int i = 0; i < R; i++) {
            // 등장 횟수 세기
            int[] cnt = new int[101];
    
            for (int j = 0; j < C; j++) {
                cnt[A[i][j]]++;
            }
            
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                if (cnt[j] == 0) continue;
                list.add(j);
            }

            // 정렬
            list.sort((a, b) -> {
                if (cnt[a] == cnt[b]) return a - b;
                return cnt[a] - cnt[b];
            });
            
            for (int j = 0; j < C; j++) A[i][j] = 0;
            
            for (int j = 0; j < list.size(); j++) {
                A[i][j * 2] = list.get(j);
                A[i][j * 2 + 1] = cnt[list.get(j)];
            }
            
            maxLen = Math.max(maxLen, list.size() * 2);
        }

        C = Math.max(C, maxLen);
    }

    static void operateC() {
        int maxLen = R;
        
        for (int i = 0; i < C; i++) {
            // 등장 횟수 세기
            int[] cnt = new int[101];
    
            for (int j = 0; j < R; j++) {
                cnt[A[j][i]]++;
            }
            
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                if (cnt[j] == 0) continue;
                list.add(j);
            }

            // 정렬
            list.sort((a, b) -> {
                if (cnt[a] == cnt[b]) return a - b;
                return cnt[a] - cnt[b];
            });
            
            for (int j = 0; j < R; j++) A[j][i] = 0;
            
            for (int j = 0; j < list.size(); j++) {
                A[j * 2][i] = list.get(j);
                A[j * 2 + 1][i] = cnt[list.get(j)];
            }
            
            maxLen = Math.max(maxLen, list.size() * 2);
        }

        R = Math.max(R, maxLen);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = 0;
        while (A[r][c] != k && t++ <= 100) {
            if (R >= C) operateR();
            else operateC();
        }
        
        System.out.println(t > 100 ? -1 : t);
    }
}