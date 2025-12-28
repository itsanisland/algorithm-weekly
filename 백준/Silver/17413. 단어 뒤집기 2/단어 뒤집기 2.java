import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringBuffer ans = new StringBuffer();
        StringBuffer tmp = new StringBuffer();
        boolean ck = false;
        
        for (char c : s.toCharArray()) {
            if (c == '<') {
                if (tmp.length() > 0) {
                    ans.append(tmp.reverse());
                    tmp.setLength(0);
                }
                ck = true;
                ans.append(c);
            } else if (c == '>') {
                ans.append(c);
                ck = false;
            } else if (c == ' ') {
                if (tmp.length() > 0) {
                    ans.append(tmp.reverse());
                    tmp.setLength(0);
                }
                ans.append(' ');
            } else {
                if (ck) {
                    ans.append(c);
                } else {
                    tmp.append(c);
                }
            }
        }

        if (tmp.length() > 0) {
            ans.append(tmp.reverse());
            tmp.setLength(0);
        }

        System.out.println(ans);
    }
}