import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        int[] alphabets = new int[26];

        for (char ch : S.toCharArray()) {
            alphabets[ch - 'a']++;
        }

        for (int cnt : alphabets) {
            System.out.print(cnt + " ");
        }
    }
}