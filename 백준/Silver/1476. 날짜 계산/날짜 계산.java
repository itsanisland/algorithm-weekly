import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int max = E * S * M;
        int i = 0, ans = 0;
        
        while (true) {
            if (i % 15 == E - 1 && i % 28 == S - 1 && i % 19 == M - 1) {
                ans = i;
                break;
            }
            i++;
        }
        
        System.out.println(ans + 1);
    }
}