import java.util.*;
import java.io.*;

class Main {

    public final static int[] DY = { -1, 1, 0, 0 };
    public final static int[] DX = { 0, 0, -1, 1 };

    public static int r, c;
    public static char[][] map;

    public static boolean isValid(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean ck = false;

        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                if (map[y][x] == 'W') {
                    for (int i = 0; i < 4; i++) {
                        int ny = y + DY[i];
                        int nx = x + DX[i];
    
                        if (isValid(ny, nx)) {
                            if (map[ny][nx] == 'S') {
                                ck = true;
                                break;
                            }
                            if (map[ny][nx] == '.') {
                                map[ny][nx] = 'D';
                            }
                        }
                    }
                }
            }
        }

        if (ck) {
            System.out.println(0);
        } else {
            System.out.println(1);
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    System.out.print(map[y][x]);
                }
                System.out.println();
            }
        }
    }
}