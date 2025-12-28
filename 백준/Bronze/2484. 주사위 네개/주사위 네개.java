import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int max = 0;

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int[] arr = new int[4];
            int[] cnt = new int[7];
            Set<Integer> set = new HashSet<>();
            
            for (int i = 0; i < 4; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
                cnt[num]++;
                set.add(num);
            }
    
            Arrays.sort(arr);
    
            int prize = 0;
            if (set.size() == 1) {
                prize = 50_000 + arr[0] * 5_000;
            } else if (set.size() == 2) {
                for (int i = 1; i <= 6; i++) {
                    if (cnt[i] == 3) {
                        prize = 10_000 + i * 1_000;
                        break;
                    } else if (cnt[i] == 2) {
                        if (prize == 0) prize = 2_000;
                        prize += i * 500;
                    }
                }
            } else if (set.size() == 3) {
                for (int i = 1; i <= 6; i++) {
                    if (cnt[i] == 2) {
                        prize = 1_000 + i * 100;
                        break;
                    }
                }
            } else {
                prize = arr[3] * 100;
            }

            max = Math.max(max, prize);
        }

        System.out.println(max);
    }
}