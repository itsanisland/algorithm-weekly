import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String ox = br.readLine();

        int ans = 0;
        int bonus = 0;
        for (int i = 1; i <= n; i++) {
            if (ox.charAt(i - 1) == 'O') {
                ans += i + bonus;
                bonus++;
            } else {
                bonus = 0;
            }
        }

        System.out.println(ans);
    }
}