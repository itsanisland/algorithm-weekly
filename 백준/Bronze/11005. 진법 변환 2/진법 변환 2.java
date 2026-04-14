import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        System.out.println(toBase(N, B));
    }

    private static String toBase(int N, int B) {
        if (N == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (N > 0) {
            int remainder = N % B;

            if (remainder < 10) {
                sb.append((char) ('0' + remainder));
            } else {
                sb.append((char) ('A' + remainder - 10));
            }

            N /= B;
        }

        return sb.reverse().toString();
    }
}