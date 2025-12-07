import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int r = n;
        int c = m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'X') {
                    r--;
                    break;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j][i] == 'X') {
                    c--;
                    break;
                }
            }
        }
        
        System.out.println(Math.max(r, c));
    }
}