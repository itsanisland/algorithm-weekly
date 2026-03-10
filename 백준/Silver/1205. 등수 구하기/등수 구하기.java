import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int rank = 1;

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            if (arr[N - 1] >= newScore && N + 1 > P) {
                rank = -1;
            } else {
                for (int i = 0; i < N; i++) {
                    if (arr[i] > newScore) rank++;
                }
            }
        }
        
        System.out.println(rank);
    }
}