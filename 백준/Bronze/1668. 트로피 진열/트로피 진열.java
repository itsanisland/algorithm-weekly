import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = arr[0];
        int l = 1;
        
        for (int i = 1; i < n; i++) {
            if (max < arr[i]) {
                l++;
                max = arr[i];
            }
        }

        max = arr[n - 1];
        int r = 1;

        for (int i = n - 1; i > 0; i--) {
            if (max < arr[i - 1]) {
                r++;
                max = arr[i - 1];
            }
        }

        System.out.println(l);
        System.out.println(r);
    }
}