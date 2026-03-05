import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double H = Double.parseDouble(st.nextToken());
        double W = Double.parseDouble(st.nextToken());
        double N = Double.parseDouble(st.nextToken()) + 1;
        double M = Double.parseDouble(st.nextToken()) + 1;

        int ans = (int) Math.ceil(H / N) * (int) Math.ceil(W / M);
        
        System.out.println(ans);
    }
}