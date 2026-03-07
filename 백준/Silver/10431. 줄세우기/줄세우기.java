 import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int P = Integer.parseInt(br.readLine());

        while (P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int cnt = 0;
            int[] arr = new int[20];

            for (int i = 0; i < 20; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                for (int j = i; j >= 0; j--) {
                    if (arr[i] < arr[j]) cnt++;
                }
            }

            sb.append(T + " " + cnt).append("\n");
        }

        System.out.print(sb);
    }
}