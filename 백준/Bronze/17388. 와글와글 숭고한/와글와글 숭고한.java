import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        if (s + k + h >= 100) System.out.println("OK");
        else {
            int min = Math.min(s, Math.min(k, h));
            if (min == s) System.out.println("Soongsil");
            else if (min == k) System.out.println("Korea");
            else System.out.println("Hanyang");
        }
    }
}