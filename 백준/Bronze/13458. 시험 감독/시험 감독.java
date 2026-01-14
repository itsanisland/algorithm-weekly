import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = N;
        for (int i = 0; i < N; i++) {
            if (A[i] - B > 0) {
                if ((A[i] - B) % C != 0) {
                    ans++;
                }
                ans += (A[i] - B) / C;
            }
        }

        System.out.println(ans);
    }
}