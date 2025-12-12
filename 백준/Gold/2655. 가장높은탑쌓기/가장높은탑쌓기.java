import java.util.*;
import java.io.*;

class Main {
    
    public static class Brick {
        int idx;
        int s, h, w;

        Brick(int idx, int s, int h, int w) {
            this.idx = idx;
            this.s = s;
            this.h = h;
            this.w = w;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        Brick[] arr = new Brick[n + 1];
        arr[0] = new Brick(0, 0, 0, 0);

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int s = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[i] = new Brick(i, s, h, w);
        }
        
        // 무게 기준 정렬
        Arrays.sort(arr, new Comparator<Brick>() {
            public int compare(Brick b1, Brick b2) {
                return b1.w - b2.w;
            }
        });

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j].s < arr[i].s) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i].h);
                }
            }
        }

        int maxH = 0;
        
        for (int i = 0; i <= n; i++) {
            maxH = Math.max(maxH, dp[i]);
        }

        // 탑 쌓은 순서 찾기(역추적)
        int idx = n;
        List<Integer> tower = new ArrayList<>();

        while (idx != 0) {
            if (maxH == dp[idx]) {
                tower.add(arr[idx].idx);
                maxH -= arr[idx].h;
            }
            idx -= 1;
        }

        System.out.println(tower.size());
        for (int i = tower.size() - 1; i >= 0; i--) {
            System.out.println(tower.get(i));
        }
    }
}