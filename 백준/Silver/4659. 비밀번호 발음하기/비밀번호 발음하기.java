import java.util.*;
import java.io.*;

class Main {

    static final String AEIOU = "aeiou";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        while (!s.equals("end")) {
            boolean ck = false;

            int cnt1 = 0;
            int cnt2 = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                
                if (AEIOU.contains("" + ch)) { // 모음
                    ck = true;
                    cnt1++;
                    cnt2 = 0;
                } else { // 자음
                    cnt2++;
                    cnt1 = 0;
                }

                if (cnt1 == 3 || cnt2 == 3) {
                    ck = false;
                    break;
                }

                if (i > 0 && s.charAt(i - 1) == s.charAt(i)) {
                    if (s.charAt(i) == 'e' || s.charAt(i) == 'o') continue;
                    ck = false;
                    break;
                }
            }
            
            System.out.println("<" + s + "> " + (ck ? "is acceptable." : "is not acceptable."));
            s = br.readLine();
        }
    }
}