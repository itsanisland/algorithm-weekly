import java.util.*;
import java.io.*;

class Main {

    public static boolean srp(char m, char t) {
        switch (m) {
            case 'S':
                switch (t) {
                    case 'R':
                        return false;
                    case 'P':
                        return true;
                    default:
                        return false;
                }
            case 'R':
                switch (t) {
                    case 'S':
                        return true;
                    case 'P':
                        return false;
                    default:
                        return false;
                }
            case 'P':
                switch (t) {
                    case 'S':
                        return false;
                    case 'R':
                        return true;
                    default:
                        return false;
                }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] m = new char[2];
        char[] t = new char[2];
        m[0] = st.nextToken().charAt(0);
        m[1] = st.nextToken().charAt(0);
        t[0] = st.nextToken().charAt(0);
        t[1] = st.nextToken().charAt(0);

        for (int i = 0; i < 2; i++) {
            boolean ck = false;
            for (int j = 0; j < 2; j++) {
                ck = srp(m[i], t[j]);
                if (!ck) break;
            }

            if (ck) {
                System.out.println("MS");
                return;
            }
        }

        for (int i = 0; i < 2; i++) {
            boolean ck = false;
            for (int j = 0; j < 2; j++) {
                ck = srp(t[i], m[j]);
                if (!ck) break;
            }

            if (ck) {
                System.out.println("TK");
                return;
            }
        }

        System.out.println("?");
    }
}