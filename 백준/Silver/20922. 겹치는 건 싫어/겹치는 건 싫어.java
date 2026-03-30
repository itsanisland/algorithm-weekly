import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        int[] count = new int[100_001];
        int len = 0, start = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int end = 0; end < N; end++) {
            int cur = arr[end];
            count[cur]++;

            while (count[cur] > K) {
                count[arr[start]]--;
                start++;
            }

            len = Math.max(len, end - start + 1);
        }
        
        System.out.println(len);
    }
}