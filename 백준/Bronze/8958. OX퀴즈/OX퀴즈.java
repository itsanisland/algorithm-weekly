import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            int totalScore = 0;
            int score = 0;

            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (ch == 'O') {
                    score++;
                } else {
                    score = 0;
                }
                totalScore += score;
            }

            System.out.println(totalScore);
        }
    }
}