import java.util.*;
import java.io.*;

class Main {

    public final static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int d = Integer.parseInt(br.readLine());
        int[][] dp = new int[d + 1][8];
        List<Integer>[] adj = new ArrayList[8];
        for (int i = 0; i < 8; i++) {
            adj[i] = new ArrayList<>();
        }

        adj[0].add(1); adj[0].add(2);
        adj[1].add(0); adj[1].add(2); adj[1].add(3);
        adj[2].add(0); adj[2].add(1); adj[2].add(3); adj[2].add(4);
        adj[3].add(1); adj[3].add(2); adj[3].add(4); adj[3].add(5);
        adj[4].add(2); adj[4].add(3); adj[4].add(5); adj[4].add(6);
        adj[5].add(3); adj[5].add(4); adj[5].add(7);
        adj[6].add(4); adj[6].add(7);
        adj[7].add(5); adj[7].add(6);

        dp[0][0] = 1;

        for (int i = 1; i <= d; i++) {
            for (int cur = 0; cur < 8; cur++) {
                for (int nxt : adj[cur]) {
                    dp[i][nxt] += dp[i - 1][cur];
                    dp[i][nxt] %= MOD;
                }
            }
        } 

        System.out.println(dp[d][0]);
    }
}