import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int len = s.length();
        int cntZero = 0, cntOne = 0;
        boolean[] deleted = new boolean[len];

        for (char ch : s.toCharArray()) {
            if (ch == '0') cntZero++;
            else cntOne++;
        }

        cntZero /= 2;
        cntOne /= 2;

        int idx = 0;
        while (cntOne > 0) {
            if (s.charAt(idx) == '1') {
                cntOne--;
                deleted[idx] = true;
            }
            idx++;
        }

        idx = len - 1;
        while (cntZero > 0) {
            if (s.charAt(idx) == '0') {
                cntZero--;
                deleted[idx] = true;
            }
            idx--;
        }

        String ans = "";
        for (int i = 0; i < len; i++) {
            if (deleted[i]) continue;
            ans += s.charAt(i);
        }
        System.out.println(ans);
    }
}