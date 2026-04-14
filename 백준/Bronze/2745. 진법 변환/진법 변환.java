import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        
        System.out.println(toDecimal(N, B));
    }

    private static int toDecimal(String N, int B) {
        int dec = 0;

        for (char ch : N.toCharArray()) {
            int value;
            if (Character.isDigit(ch)) {
                value = ch - '0';
            } else {
                value = ch - 'A' + 10;
            }
            dec = dec * B + value;
        }

        return dec;
    }
}