import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        while (!input.equals("0")) {
            String rslt = "yes";
            int len = input.length();
            for (int i = 0; i < len / 2; i++) {
                if (input.charAt(i) != input.charAt(len - i - 1)) {
                    rslt = "no";
                    break;
                }
            }
            System.out.println(rslt);
            input = br.readLine();
        }
    }
}