import java.util.*;
import java.io.*;

class Main {

    static int ans = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        dfs(S, T);
        
        System.out.println(ans);
    }

    static void dfs(String s, String t) {
        if (t.length() < s.length() || ans == 1) return;
        
        if (s.equals(t)) {
            ans = 1;
            return;
        }

        StringBuilder sb = new StringBuilder(t);
        if (t.charAt(t.length() - 1) == 'A') {
            sb.deleteCharAt(sb.length() - 1);
            dfs(s, sb.toString());
        }

        sb = new StringBuilder(t);
        if (t.charAt(0) == 'B') {
            sb.reverse();
            sb.deleteCharAt(sb.length() - 1);
            dfs(s, sb.toString());
        }
    }
}