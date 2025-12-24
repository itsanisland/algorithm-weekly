import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet(Arrays.asList(br.readLine().split(" ")));
        
        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (String num : br.readLine().split(" ")) {
            sb.append(set.contains(num) ? "1" : "0").append("\n");
        }

        System.out.println(sb);
    }
}