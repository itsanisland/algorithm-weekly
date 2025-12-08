import java.util.*;
import java.io.*;

class Main {

    public static int[] arr;
    public static int ans;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        
        solve(1, arr[n - 1] - arr[0], c);
        
        System.out.println(ans);
    }

    public static void solve(int min, int max, int c) {
        if (min > max) return;
        
        int mid = (min + max) / 2;

        int cnt = 1, front = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - front >= mid) {
                cnt++;
                front = arr[i];
            }
        }

        if (cnt < c) {
            solve(min, mid - 1, c);
        } else {
            ans = Math.max(ans, mid);
            solve(mid + 1, max, c);
        }
    }
}