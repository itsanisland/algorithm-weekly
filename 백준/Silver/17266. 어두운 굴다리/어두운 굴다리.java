import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[M];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = arr[0];

        for (int i = 0; i < M - 1; i++) {
            ans = Math.max(ans, (arr[i + 1] - arr[i] + 1) / 2);
        }

        ans = Math.max(ans, N - arr[M - 1]);
        
        System.out.println(ans);
    }
}