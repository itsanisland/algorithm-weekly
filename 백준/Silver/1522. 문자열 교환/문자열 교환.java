import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int size = s.length();
        int a = 0;
        
        for (char c : s.toCharArray()) {
            if (c == 'a') a++;
        }

        int bMin = 0;
        for (int i = 0; i < a; i++) {
            if (s.charAt(i) == 'b') bMin++;
        }

        int b = bMin;
        for (int l = 0; l < size; l++) {
            if (s.charAt(l) == 'b') b--; // 빠지는 것

            int r = (l + a) % size; // 들어오는 것
            if (s.charAt(r) == 'b') b++;
            
            bMin = Math.min(bMin, b);
        }
        
        System.out.println(bMin);
    }
}