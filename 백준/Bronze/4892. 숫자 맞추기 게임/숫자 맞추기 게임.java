import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n0 = Integer.parseInt(br.readLine());
        int i = 1;
        
        while (n0 != 0) {
            int n1 = 3 * n0;
            int n2 = n1 % 2 == 0 ? n1 / 2 : (n1 + 1) / 2;
            int n3 = 3 * n2;
            int n4 = n3 / 9;
            
            System.out.println(i++ + ". " + (n1 % 2 == 0 ? "even " : "odd ") + n4);

            n0 = Integer.parseInt(br.readLine());
        }
    }
}