import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input = br.readLine();
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : input.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int oddCnt = 0;
        char oddChar = 0;

        for (char key : map.keySet()) {
            if (map.get(key) % 2 != 0) {
                oddCnt++;
                oddChar = key;
            }
        }

        // 홀수개 문자가 2개 이상이면 팰린드롬 불가
        if (oddCnt > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        // key들을 오름차순 정렬
        List<Character> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        // 앞쪽 절반을 만든다
        StringBuilder half = new StringBuilder();

        for (char key : keys) {
            int cnt = map.get(key);
            for (int i = 0; i < cnt / 2; i++) {
                half.append(key);
            }
        }

        StringBuilder answer = new StringBuilder();

        // 1. 앞쪽 절반
        answer.append(half);

        // 2. 홀수 문자(있다면) 가운데 배치
        if (oddCnt == 1) answer.append(oddChar);

        // 3. 뒤쪽 절반 = half 역순
        answer.append(half.reverse());

        System.out.println(answer.toString());
    }
}