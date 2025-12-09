import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int temp = b;
        if (b < a) {
            b = a;
            a = temp;
        }

        String ans = "";

        for (int i = a + 1; i < b; i++) {
            ans += i + " ";
        }

        System.out.println(a == b ? 0 : b - a - 1);
        System.out.println(ans);
    }
}