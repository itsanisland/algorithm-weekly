import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int minOdd = 100;
        int sumOdd = 0;

        for (int i = 0; i < 7; i++) {
            int input = Integer.parseInt(br.readLine());
            
            if (input % 2 != 0) {
                minOdd = Math.min(minOdd, input);
                sumOdd += input;
            }
        }

        if (sumOdd == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sumOdd);
            System.out.println(minOdd);
        }
    }
}