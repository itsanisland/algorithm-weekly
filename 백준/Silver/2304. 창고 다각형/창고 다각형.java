import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[1001];

        int maxL = 0, maxH = 0;

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            arr[L] = H;

            if (maxH < H) {
                maxL = L; maxH = H;
            }
        }

        int ans = maxH;
        int max = 0;
        for (int i = 1; i < maxL; i++) {
            max = Math.max(max, arr[i]);
            ans += max;
        }

        max = 0;
        for (int i = 1000; i > maxL; i--) {
            max = Math.max(max, arr[i]);
            ans += max;
        }
        
        System.out.println(ans);
    }
}