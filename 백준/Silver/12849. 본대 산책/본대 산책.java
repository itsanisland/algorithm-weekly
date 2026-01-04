import java.util.*;
import java.io.*;

class Main {

    public static final long MOD = 1_000_000_007L;
    public static long[] dp;

    public static void next(long[] state) {
        long[] tmp = new long[8];
        tmp[0] = (state[1] + state[2]) % MOD;
        tmp[1] = (state[0] + state[2] + state[3]) % MOD;
        tmp[2] = (state[0] + state[1] + state[3] + state[4]) % MOD;
        tmp[3] = (state[1] + state[2] + state[4] + state[5]) % MOD;
        tmp[4] = (state[2] + state[3] + state[5] + state[6]) % MOD;
        tmp[5] = (state[3] + state[4] + state[7]) % MOD;
        tmp[6] = (state[4] + state[7]) % MOD;
        tmp[7] = (state[5] + state[6]) % MOD;
        dp = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int d = Integer.parseInt(br.readLine());
        dp = new long[8];
        dp[0] = 1;

        while (d-- > 0) {
            next(dp);
        }

        System.out.println(dp[0] % MOD);
    }
}