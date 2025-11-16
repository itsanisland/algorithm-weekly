import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] cnt = new int[2];

            for (int j = 0; j < 4; j++) {
                int input = Integer.parseInt(st.nextToken());
                cnt[input]++;
            }

            char rslt = ' ';
            if (cnt[0] == 1) rslt = 'A';
            else if (cnt[0] == 2) rslt = 'B';
            else if (cnt[0] == 3) rslt = 'C';
            else if (cnt[0] == 4) rslt = 'D';
            else rslt = 'E';
            
            System.out.println(rslt);   
        }
    }
}