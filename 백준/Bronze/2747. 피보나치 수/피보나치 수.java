import java.util.*;
import java.io.*;

class Main {

    private static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        System.out.println(fibo(n));
    }

    private static int fibo(int n) {
        if (n == 0 || n == 1) return memo[n] = n;
        if (memo[n] == -1) return memo[n] = fibo(n - 2) + fibo(n - 1);
        return memo[n - 2] + memo[n - 1];
    }
}