import java.util.*;
import java.io.*;

class Main {

    private static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(solve(1 << n, 0, 0, 0));
    }

    private static int solve(int n, int r, int c, int num) {
        if (n == 1) return num;
    
        int half = n / 2;
        int block = half * half;
    
        if (R < r + half && C < c + half)
            return solve(half, r, c, num);
        else if (R < r + half && C >= c + half)
            return solve(half, r, c + half, num + block);
        else if (R >= r + half && C < c + half)
            return solve(half, r + half, c, num + block * 2);
        else
            return solve(half, r + half, c + half, num + block * 3);
    }
}