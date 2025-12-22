import java.util.*;
import java.io.*;

class Main {

    public final static String aeiou = "aeiou";

    public static int L, C;
    public static char[] alphabets;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabets = new char[C];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabets);
        
        comb(0, 0, 0, new StringBuilder());
    }

    public static void comb(int start, int cnt, int aeiouCnt, StringBuilder sb) {
        if (cnt == L) {
            if (aeiouCnt >= 1 && cnt - aeiouCnt >= 2) {
                System.out.println(sb);
            }
            return;
        }

        for (int i = start; i < C; i++) {
            sb.append(alphabets[i]);
            comb(i + 1, cnt + 1, aeiou.contains("" + alphabets[i]) ? aeiouCnt + 1 : aeiouCnt, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}