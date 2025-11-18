import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String s = br.readLine();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();

            for (char ch : s.toCharArray()) {
                switch (ch) {
                    case '<':
                        if (!left.isEmpty()) right.push(left.pop());
                        break;
                    case '>':
                        if (!right.isEmpty()) left.push(right.pop());
                        break;
                    case '-':
                        if (!left.isEmpty()) left.pop();
                        break;
                    default:
                        left.push(ch);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : left) sb.append(c);
            while (!right.isEmpty()) sb.append(right.pop());

            System.out.println(sb.toString());
        }
    }
}