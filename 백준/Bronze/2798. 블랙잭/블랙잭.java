import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = cards[i];
            for (int j = i + 1; j < n; j++) {
                sum += cards[j];
                for (int k = j + 1; k < n; k++) {
                    sum += cards[k];
                    if (sum <= m) max = Math.max(max, sum);
                    sum -= cards[k];
                }
                sum -= cards[j];
            }
        }

        System.out.println(max);
    }
}