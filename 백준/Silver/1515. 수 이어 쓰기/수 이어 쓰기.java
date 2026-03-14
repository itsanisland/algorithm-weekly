import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int pointer = 0;
        int ans = 0;

        while (pointer < s.length()) {
            ans++;
            String base = "" + ans;
            
            for (int i = 0; i < base.length(); i++) {
                if (pointer < s.length() && base.charAt(i) == s.charAt(pointer)) {
                    pointer++;
                }
            }
        }
        
        System.out.println(ans);
    }
}