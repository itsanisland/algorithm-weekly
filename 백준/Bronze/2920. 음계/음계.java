import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String rslt = "";
        int prev = Integer.parseInt(st.nextToken());
        for (int i = 1; i < 8; i++) {
            int input = Integer.parseInt(st.nextToken());

            if (prev == input - 1) rslt = "ascending";
            else if (prev == input + 1) rslt = "descending";
            else { 
                rslt = "mixed";
                break;
            }

            prev = input;
        }

        System.out.println(rslt);
    }
}