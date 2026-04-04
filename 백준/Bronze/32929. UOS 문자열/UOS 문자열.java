import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());

        String uso = "UOS";

        int idx = x % 3 == 0 ? 2 : x % 3 - 1;
        
        System.out.println(uso.charAt(idx));
    }
}