import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        StringBuilder sb = new StringBuilder(s);
        int cntZero = 0, cntOne = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '0') cntZero++;
            else cntOne++;
        }

        cntZero /= 2;
        cntOne /= 2;

        int idx = 0;
        while (cntOne > 0) {
            if (sb.charAt(idx) == '1') {
                cntOne--;
                sb.deleteCharAt(idx);
            } else idx++;
        }

        idx = sb.length() - 1;
        while (cntZero > 0) {
            if (sb.charAt(idx) == '0') {
                cntZero--;
                sb.deleteCharAt(idx);
            }
            idx--;
        }
        
        System.out.println(sb);
    }
}