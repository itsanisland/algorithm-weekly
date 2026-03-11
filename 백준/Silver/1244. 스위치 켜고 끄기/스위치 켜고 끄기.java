import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] switches = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                for (int i = cnt; i <= N; i += cnt) {
                    switches[i] = (switches[i] + 1) % 2;
                }
            } else {
                int p = 1;
                switches[cnt] = (switches[cnt] + 1) % 2;
                while (cnt - p > 0 && cnt + p <= N) {
                    if (switches[cnt - p] == switches[cnt + p]) {
                        switches[cnt - p] = switches[cnt + p] = (switches[cnt + p] + 1) % 2;
                        p++;
                    } else break;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(switches[i] + " ");
            if (i % 20 == 0) System.out.println();
        }
    }
}