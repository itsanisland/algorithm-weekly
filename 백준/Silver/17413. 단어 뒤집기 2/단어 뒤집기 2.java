import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        String ans = "";
        StringBuffer tmp = new StringBuffer();
        boolean ck = false;
        
        for (char c : s.toCharArray()) {
            if (c == '<') {
                if (tmp.length() > 0) {
                    ans += tmp.reverse().toString();
                    tmp.setLength(0);
                }
                ck = true;
                ans += c;
            } else if (c == '>') {
                ans += c;
                ck = false;
            } else if (c == ' ') {
                if (tmp.length() > 0) {
                    ans += tmp.reverse().toString();
                    tmp.setLength(0);
                }
                ans += ' ';
            } else {
                if (ck) {
                    ans += c;
                } else {
                    tmp.append(c);
                }
            }
        }

        if (tmp.length() > 0) {
            ans += tmp.reverse().toString();
            tmp.setLength(0);
        }

        System.out.println(ans);
    }
}