import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int zeroCnt = 0; // 전부 0으로 바꾸는 경우
        int oneCnt = 0;  // 전부 1로 바꾸는 경우

        if (s.charAt(0) == '1') {
            zeroCnt++;
        } else {
            oneCnt++;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            int prev = s.charAt(i);
            int next = s.charAt(i + 1);
            if (prev != next) {
                if (next == '1') { // 01
                    zeroCnt++;
                } else { // 10
                    oneCnt++;
                }
            }
        }

        System.out.println(Math.min(zeroCnt, oneCnt));
    }
}