import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        // 한 자리 수는 무조건 1팩
        if (N.length() == 1) {
            System.out.println(1);
            return;
        }

        int len = N.length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append('1');
        }
        String threshold = sb.toString();

        if (N.compareTo(threshold) >= 0) {
            System.out.println(len);
        } else {
            System.out.println(len - 1);
        }
    }
}