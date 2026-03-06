import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int M = Integer.parseInt(br.readLine());
        int[] S = new int[21];
        
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());

            switch(command) {
                case "add":
                    S[num] = 1;
                    break;
                case "remove":
                    S[num] = 0;
                    break;
                case "check":
                    sb.append(S[num]).append("\n");
                    break;
                case "toggle":
                    S[num] = S[num] == 0 ? 1 : 0;
                    break;
                case "all":
                    Arrays.fill(S, 1);
                    break;
                case "empty":
                    Arrays.fill(S, 0);
                    break;
            }
        }
        
        System.out.println(sb);
    }
}