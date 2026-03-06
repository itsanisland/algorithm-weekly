import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
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
                    bw.append("" + S[num]);
                    bw.newLine();
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
        
        bw.flush();
        bw.close();
    }
}