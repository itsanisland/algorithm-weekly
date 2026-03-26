import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int M = Integer.parseInt(br.readLine());

        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            left.offerLast(s.charAt(i));
        }

        while (M-- > 0) {
            String input = br.readLine();
            char cmd = input.charAt(0);

            switch (cmd) {
                case 'L':
                    if (!left.isEmpty()) {
                        right.offerFirst(left.pollLast());
                    }
                    break;
                case 'D':
                    if (!right.isEmpty()) {
                        left.offerLast(right.pollFirst());
                    }
                    break;
                case 'B':
                    if (!left.isEmpty()) {
                        left.pollLast();
                    }
                    break;
                case 'P':
                    left.offerLast(input.charAt(2));
                    break;
            }
        }

        StringBuilder sb = new StringBuilder(left.size() + right.size());

        for (char ch : left) sb.append(ch);
        for (char ch : right) sb.append(ch);

        System.out.println(sb);
    }
}