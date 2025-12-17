import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 1000 - Integer.parseInt(br.readLine());

        int cnt = 0;

        int[] arr = { 500, 100, 50, 10, 5, 1 };

        for (int i = 0; i < arr.length; i++) {
            cnt += n / arr[i];
            n %= arr[i];
        }

        System.out.println(cnt);
    }
}