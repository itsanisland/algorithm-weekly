import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[7];

        for (int i = 0; i < 3; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[num]++;
        }

        int ans = 0;
        int max = 0;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] == 3) {
                ans = 10_000 + i * 1_000;
                break;
            } else if (arr[i] == 2) {
                ans = 1_000 + i * 100;
            } else if (arr[i] == 1) {
                max = Math.max(max, i);
            }
        }
        
        System.out.println(ans == 0 ? max * 100 : ans);
    }
}