import java.util.*;
import java.io.*;

class Main {

    static int[][] A = new int[200][200];
    static int R = 3, C = 3;

    static void operateR() {
        // 등장 횟수 세기
        Map<Integer, Integer>[] map = new HashMap[R];
        for (int i = 0; i < R; i++) map[i] = new HashMap<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int num = A[i][j];
                if (num == 0) continue;
                map[i].put(num, map[i].getOrDefault(num, 0) + 1);
            }
        }

        // 정렬
        for (int i = 0; i < R; i++) {
            Map<Integer, Integer> m = map[i];
            List<Integer> keySet = new ArrayList<>(m.keySet());
            
            Collections.sort(keySet, new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    if (m.get(o1) == m.get(o2)) return o1 - o2;
                    return m.get(o1) - m.get(o2);
                }
            });

            for (int j = 0; j < C; j++) A[i][j] = 0;
            
            for (int j = 0; j < keySet.size(); j++) {
                int key = keySet.get(j);
                A[i][j * 2] = key;
                A[i][j * 2 + 1] = m.get(key);
            }

            C = Math.max(C, keySet.size() * 2);
        }
    }

    static void operateC() {
        // 등장 횟수 세기
        Map<Integer, Integer>[] map = new HashMap[C];
        for (int i = 0; i < C; i++) map[i] = new HashMap<>();

        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                int num = A[j][i];
                if (num == 0) continue;
                map[i].put(num, map[i].getOrDefault(num, 0) + 1);
            }
        }

        // 정렬
        for (int i = 0; i < C; i++) {
            Map<Integer, Integer> m = map[i];
            List<Integer> keySet = new ArrayList<>(m.keySet());
            
            Collections.sort(keySet, new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    if (m.get(o1) == m.get(o2)) return o1 - o2;
                    return m.get(o1) - m.get(o2);
                }
            });

            for (int j = 0; j < R; j++) A[j][i] = 0;
            
            for (int j = 0; j < keySet.size(); j++) {
                int key = keySet.get(j);
                A[j * 2][i] = key;
                A[j * 2 + 1][i] = m.get(key);
            }

            R = Math.max(R, keySet.size() * 2);
        }
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