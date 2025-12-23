import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        String name1 = st.nextToken();
        String name2 = st.nextToken();

        int[] alphabets = { 3, 2, 1, 2, 4, 3, 1, 3, 1, 1, 3, 1, 3, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1 };
        int max = Math.max(n, m);
        int min = Math.min(n, m);

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < min; i++) {
            list.add(alphabets[name1.charAt(i) - 'A']);
            list.add(alphabets[name2.charAt(i) - 'A']);
        }

        if (n < m) {
            for (int i = min; i < max; i++) {
                list.add(alphabets[name2.charAt(i) - 'A']);
            }
        } else {
            for (int i = min; i < max; i++) {
                list.add(alphabets[name1.charAt(i) - 'A']);
            }
        }

        List<Integer> list2 = new ArrayList<>();

        while (list.size() > 2) {
            for (int i = 0; i < list.size() - 1; i++) {
                int num = list.get(i) + list.get(i + 1);
                list2.add(num > 9 ? num - 10 : num);
            }
            list = new ArrayList<>(list2);
            list2 = new ArrayList<>();
        }

        int ans = Integer.parseInt("" + list.get(0) + list.get(1));
        System.out.println(ans + "%");
    }
}