import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        
        boolean[] eaten = new boolean[N];
        int ans = 0;

        for (int i = 0; i < N; i++) {
            char ch = s.charAt(i);
            if (ch == 'P') {
                boolean flag = false;
                for (int j = K; j >= 1; j--) {
                    if (i - j >= 0 && s.charAt(i - j) == 'H' && !eaten[i - j]) {
                        ans++;
                        eaten[i - j] = true;
                        flag = true;
                        break;
                    }
                }

                if (flag) continue;

                for (int j = 1; j <= K; j++) {
                    if (i + j < N && s.charAt(i + j) == 'H' && !eaten[i + j]) {
                        ans++;
                        eaten[i + j] = true;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}