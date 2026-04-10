import java.util.*;
import java.io.*;

class Main {
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        dfs(T, S);
        System.out.println(ans);
    }

    static void dfs(String t, String s) {
        // 이미 찾았거나 t가 s보다 짧아지면 종료
        if (t.length() == s.length()) {
            if (t.equals(s)) ans = 1;
            return;
        }
        if (ans == 1) return;

        // 1. 맨 뒤에 A가 있으면 A를 지워본다
        if (t.charAt(t.length() - 1) == 'A') {
            dfs(t.substring(0, t.length() - 1), s);
        }

        // 2. 맨 앞에 B가 있으면 B를 지우고 뒤집어본다
        if (t.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(t.substring(1));
            dfs(sb.reverse().toString(), s);
        }
    }
}