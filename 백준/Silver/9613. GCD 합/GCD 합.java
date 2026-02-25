import java.util.*;
import java.io.*;

class Main {

    static int getGCD(int a, int b) {
        return a % b == 0 ? b : getGCD(b, a % b);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            long ans = 0;
            
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] > arr[j]) ans += getGCD(arr[i], arr[j]);
                    else ans += getGCD(arr[j], arr[i]);
                }
            }

            System.out.println(ans);
        } 
    }
}