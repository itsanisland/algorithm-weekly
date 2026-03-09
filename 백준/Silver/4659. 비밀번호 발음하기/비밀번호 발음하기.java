import java.util.*;
import java.io.*;

class Main {

    static final String AEIOU = "aeiou";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        while (!s.equals("end")) {
            boolean step1 = false, step2 = true, step3 = true;

            // bouchtuh
            String m = "";
            String j = "";
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                
                if (AEIOU.contains("" + ch)) { // 모음
                    step1 = true;
                    m += ch;
                    j = "";
                } else { // 자음
                    j += ch;
                    m = "";
                }

                if (m.length() == 3 || j.length() == 3) {
                    step2 = false;
                    break;
                }

                if (i > 0 && s.charAt(i - 1) == s.charAt(i)) {
                    if (s.charAt(i) == 'e' || s.charAt(i) == 'o') continue;
                    step3 = false;
                    break;
                }
            }
            
            System.out.println("<" + s + "> " + (step1 && step2 && step3 ? "is acceptable." : "is not acceptable."));
            s = br.readLine();
        }
    }
}