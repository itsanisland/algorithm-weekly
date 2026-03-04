import java.io.*;
import java.util.*;

public class Main {

    static char[][] U = new char[3][3];
    static char[][] D = new char[3][3];
    static char[][] F = new char[3][3];
    static char[][] B = new char[3][3];
    static char[][] L = new char[3][3];
    static char[][] R = new char[3][3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            int n = Integer.parseInt(br.readLine());
            String[] cmd = br.readLine().split(" ");

            for (String c : cmd) {
                rotate(c.charAt(0), c.charAt(1));
            }

            // U 출력
            for (int i = 0; i < 3; i++) {
                System.out.println(new String(U[i]));
            }
        }
    }

    static void init() {
        fill(U, 'w');
        fill(D, 'y');
        fill(F, 'r');
        fill(B, 'o');
        fill(L, 'g');
        fill(R, 'b');
    }

    static void fill(char[][] a, char c) {
        for (int i = 0; i < 3; i++)
            Arrays.fill(a[i], c);
    }

    static void rotate(char face, char dir) {
        if (face == 'U') rotateU(dir);
        else if (face == 'D') rotateD(dir);
        else if (face == 'F') rotateF(dir);
        else if (face == 'B') rotateB(dir);
        else if (face == 'L') rotateL(dir);
        else if (face == 'R') rotateR(dir);
    }

    static void rotateFace(char[][] a, boolean cw) {
        char[][] t = new char[3][3];
        for (int i = 0; i < 3; i++) t[i] = a[i].clone();

        if (cw) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    a[j][2 - i] = t[i][j];
        } else {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    a[2 - j][i] = t[i][j];
        }
    }

    static void rotateU(char d) {
        rotateFace(U, d == '+');
        for (int i = 0; i < 3; i++) {
            char t = F[0][i];
            if (d == '+') {
                F[0][i] = R[0][i];
                R[0][i] = B[0][i];
                B[0][i] = L[0][i];
                L[0][i] = t;
            } else {
                F[0][i] = L[0][i];
                L[0][i] = B[0][i];
                B[0][i] = R[0][i];
                R[0][i] = t;
            }
        }
    }

    static void rotateD(char d) {
        rotateFace(D, d == '+');
        for (int i = 0; i < 3; i++) {
            char t = F[2][i];
            if (d == '+') {
                F[2][i] = L[2][i];
                L[2][i] = B[2][i];
                B[2][i] = R[2][i];
                R[2][i] = t;
            } else {
                F[2][i] = R[2][i];
                R[2][i] = B[2][i];
                B[2][i] = L[2][i];
                L[2][i] = t;
            }
        }
    }

    static void rotateF(char d) {
        rotateFace(F, d == '+');
        for (int i = 0; i < 3; i++) {
            char t = U[2][i];
            if (d == '+') {
                U[2][i] = L[2 - i][2];
                L[2 - i][2] = D[0][2 - i];
                D[0][2 - i] = R[i][0];
                R[i][0] = t;
            } else {
                U[2][i] = R[i][0];
                R[i][0] = D[0][2 - i];
                D[0][2 - i] = L[2 - i][2];
                L[2 - i][2] = t;
            }
        }
    }

    static void rotateB(char d) {
        rotateFace(B, d == '+');
        for (int i = 0; i < 3; i++) {
            char t = U[0][i];
            if (d == '+') {
                U[0][i] = R[i][2];
                R[i][2] = D[2][2 - i];
                D[2][2 - i] = L[2 - i][0];
                L[2 - i][0] = t;
            } else {
                U[0][i] = L[2 - i][0];
                L[2 - i][0] = D[2][2 - i];
                D[2][2 - i] = R[i][2];
                R[i][2] = t;
            }
        }
    }

    static void rotateL(char d) {
        rotateFace(L, d == '+');
        for (int i = 0; i < 3; i++) {
            char t = U[i][0];
            if (d == '+') {
                U[i][0] = B[2 - i][2];
                B[2 - i][2] = D[i][0];
                D[i][0] = F[i][0];
                F[i][0] = t;
            } else {
                U[i][0] = F[i][0];
                F[i][0] = D[i][0];
                D[i][0] = B[2 - i][2];
                B[2 - i][2] = t;
            }
        }
    }

    static void rotateR(char d) {
        rotateFace(R, d == '+');
        for (int i = 0; i < 3; i++) {
            char t = U[i][2];
            if (d == '+') {
                U[i][2] = F[i][2];
                F[i][2] = D[i][2];
                D[i][2] = B[2 - i][0];
                B[2 - i][0] = t;
            } else {
                U[i][2] = B[2 - i][0];
                B[2 - i][0] = D[i][2];
                D[i][2] = F[i][2];
                F[i][2] = t;
            }
        }
    }
}