import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int M = Integer.parseInt(br.readLine());

        Deque<Character> prev = new ArrayDeque<>();
        Deque<Character> back = new ArrayDeque<>();

        for (char ch : s.toCharArray()) prev.offer(ch);

        while (M-- > 0) {
            String[] input = br.readLine().split(" ");
            char cmd = input[0].charAt(0);
            char x = input.length > 1 ? input[1].charAt(0) : ' ';

            switch (cmd) {
                case 'L':
                    if (prev.size() > 0) {
                        back.offerFirst(prev.pollLast());
                    }
                    break;
                case 'D':
                    if (back.size() > 0) {
                        prev.offerLast(back.pollFirst());
                    }
                    break;
                case 'B':
                    if (prev.size() > 0) {
                        prev.pollLast();
                    }
                    break;
                case 'P':
                    prev.offerLast(x);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : prev) sb.append(ch);
        for (char ch : back) sb.append(ch);
        System.out.println(sb);
    }
}