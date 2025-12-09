import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        if (b < a) {
            long temp = b;
            b = a;
            a = temp;
        }

        long count = (a == b) ? 0 : b - a - 1;
        System.out.println(count);

        if (count > 0) {
            StringBuilder sb = new StringBuilder();
            for (long i = a + 1; i < b; i++) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        }
    }
}