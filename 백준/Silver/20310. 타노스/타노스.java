import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int cntZero = 0, cntOne = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '0') cntZero++;
            else cntOne++;
        }

        cntZero /= 2;
        cntOne /= 2;

        String ans = "";
        for (int i = 0; i < cntZero; i++) ans += '0';
        for (int i = 0; i < cntOne; i++) ans += '1';
        
        System.out.println(ans);
    }
}