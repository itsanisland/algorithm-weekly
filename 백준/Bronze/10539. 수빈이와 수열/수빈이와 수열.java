import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int avg = Integer.parseInt(st.nextToken());
            int num = avg * i - sum;
            System.out.print(num  + " "); 
            sum += num;
        }
    }
}