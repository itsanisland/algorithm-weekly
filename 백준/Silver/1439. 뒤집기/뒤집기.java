import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int zeroCnt = 0, oneCnt = 0;
        char cur = ' ';
        
        for (char c : s.toCharArray()) {
            if (c != cur) {
                if (c == '0') {
                    zeroCnt++;
                } else {
                    oneCnt++;
                }
            }
            cur = c;
        }
        
        System.out.println(Math.min(zeroCnt, oneCnt));
    }
}