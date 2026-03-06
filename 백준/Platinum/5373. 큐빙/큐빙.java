import java.util.*;
import java.io.*;

class Main {

    static char[][] U = new char[3][3];
    static char[][] D = new char[3][3];
    static char[][] F = new char[3][3];
    static char[][] B = new char[3][3];
    static char[][] L = new char[3][3];
    static char[][] R = new char[3][3];

    static void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                U[i][j] = 'w';
                D[i][j] = 'y';
                F[i][j] = 'r';
                B[i][j] = 'o';
                L[i][j] = 'g';
                R[i][j] = 'b';
            }
        }
    }

    static void rotateFace(char[][] face, char d) {
        char[][] cp = new char[3][3];
        for (int i = 0; i < 3; i++) cp[i] = face[i].clone();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (d == '+') face[j][2 - i] = cp[i][j];
                else face[2 - j][i] = cp[i][j];
            }
        }
    }

    static void rotateU(char d) {
        rotateFace(U, d);
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
        rotateFace(D, d);
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
        rotateFace(F, d);
        for (int i = 0; i < 3; i++) {
            char t = L[i][2];
            if (d == '+') {
                L[i][2] = D[0][i];
                D[0][i] = R[2 - i][0];
                R[2 - i][0] = U[2][2 - i];
                U[2][2 - i] = t;
            } else {
                L[i][2] = U[2][2 - i];
                U[2][2 - i] = R[2 - i][0];
                R[2 - i][0] = D[0][i];
                D[0][i] = t;
            }
        }
    }

    static void rotateB(char d) {
        rotateFace(B, d);
        for (int i = 0; i < 3; i++) {
            char t = L[i][0];
            if (d == '+') {
                L[i][0] = U[0][2 - i];
                U[0][2 - i] = R[2 - i][2];
                R[2 - i][2] = D[2][i];
                D[2][i] = t;
            } else {
                L[i][0] = D[2][i];
                D[2][i] = R[2 - i][2];
                R[2 - i][2] = U[0][2 - i];
                U[0][2 - i] = t;
            }
        }
    }

    static void rotateL(char d) {
        rotateFace(L, d);
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
        rotateFace(R, d);
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

    static void rotate(char face, char d) {
        if (face == 'U') rotateU(d);
        if (face == 'D') rotateD(d);
        if (face == 'F') rotateF(d);
        if (face == 'B') rotateB(d);
        if (face == 'L') rotateL(d);
        if (face == 'R') rotateR(d);
    }

    static void print(BufferedWriter bw) throws IOException {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bw.append(U[i][j]);
            }
            bw.newLine();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            while (n-- > 0) {
                String s = st.nextToken();
                char face = s.charAt(0);
                char d = s.charAt(1);
                rotate(face, d);
            }

            print(bw);
        }

        bw.flush();
        bw.close();
    }
}