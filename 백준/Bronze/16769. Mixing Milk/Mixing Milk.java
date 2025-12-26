import java.util.*;
import java.io.*;

class Main {

    public static void pour(int i, int[] c, int[] m) {
        int next = (i + 1) % 3;
        int limit = c[next] - m[next];
        
        if (m[i] <= limit) {
            m[next] += m[i];
            m[i] = 0;
        } else {
            m[next] += limit;
            m[i] -= limit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] c = new int[3];
        int[] m = new int[3];
        
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            m[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 100; i++) {
            pour(i % 3, c, m);
        }

        for (int milk : m) {
            System.out.println(milk);
        }
    }
}