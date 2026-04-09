import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        Set<Character> set = new HashSet<>();

        while (N-- > 0) {
            String option = br.readLine();
            String[] words = option.split(" ");
            boolean ck = false;
                
            for (int i = 0; i < words.length; i++) {
                char first = Character.toUpperCase(words[i].charAt(0));
                if (!set.contains(first)) {
                    set.add(first);
                    StringBuilder tmp = new StringBuilder(words[i]);
                    tmp.insert(0, "[");
                    tmp.insert(2, "]");
                    words[i] = tmp.toString();
                    ck = true;
                    break;
                }
            }

            if (!ck) {
                for (int i = 0; i < words.length; i++) {
                    for (int j = 1; j < words[i].length(); j++) {
                        char ch = Character.toUpperCase(words[i].charAt(j));
                        if (!set.contains(ch)) {
                            set.add(ch);
                            StringBuilder tmp = new StringBuilder(words[i]);
                            tmp.insert(j, "[");
                            tmp.insert(j + 2, "]");
                            words[i] = tmp.toString();
                            ck = true;
                            break;
                        }
                    }

                    if (ck) break;
                }
            }

            for (String word : words) {
                sb.append(word).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}