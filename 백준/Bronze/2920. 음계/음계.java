import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean ascending = true, descending = true;

        int prev = Integer.parseInt(st.nextToken());
        for (int i = 1; i < 8; i++) {
            int input = Integer.parseInt(st.nextToken());

            if (prev > input) ascending = false;
            else descending = false;

            prev = input;
        }

        if (ascending) {
            System.out.println("ascending");
        } else if (descending) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}