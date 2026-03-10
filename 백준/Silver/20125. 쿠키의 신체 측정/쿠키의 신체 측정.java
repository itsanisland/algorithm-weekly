import java.util.*;
import java.io.*;

class Main {

    static final int[] DY = {0, 0, 1};
    static final int[] DX = {-1, 1, 0};

    static int N;
    static char[][] board;

    static int getLength(int y, int x, int d) {
        if (y < 1 || y > N || x < 1 || x > N) return 0;
        
        if (board[y][x] == '_') return 0;

        return 1 + getLength(y + DY[d], x + DX[d], d);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        N = Integer.parseInt(br.readLine());
        board = new char[N + 1][N + 1];
        int hy = -1, hx = -1;
        
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                board[i][j] = line.charAt(j - 1);
                if (hy == -1 && board[i][j] == '*') {
                    hy = i;
                    hx = j;
                }
            }
        }
        
        System.out.println(++hy + " " + hx);
        System.out.print(getLength(hy, hx - 1, 0) + " ");
        System.out.print(getLength(hy, hx + 1, 1) + " ");

        int wLen = getLength(hy + 1, hx, 2);
        int wy = hy + wLen;
        int wx = hx;
        System.out.print(wLen + " ");

        System.out.print(getLength(wy + 1, wx - 1, 2) + " ");
        System.out.print(getLength(wy + 1, wx + 1, 2) + " ");
    }
}