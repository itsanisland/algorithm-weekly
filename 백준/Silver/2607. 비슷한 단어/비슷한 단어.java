import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String firstWord = br.readLine();
        int[] firstCount = getCount(firstWord);
        
        int ans = 0;
        for (int i = 1; i < N; i++) {
            String word = br.readLine();
            int[] wordCount = getCount(word);
            
            int same = 0;
            // 두 단어에서 공통으로 포함된 알파벳의 개수를 구함
            for (int j = 0; j < 26; j++) {
                same += Math.min(firstCount[j], wordCount[j]);
            }
            
            // 비슷한 단어 조건 체크
            if (firstWord.length() == word.length()) {
                // 완전히 같거나, 한 글자만 바뀐 경우
                if (same == firstWord.length() || same == firstWord.length() - 1) ans++;
            } else if (firstWord.length() == word.length() + 1) {
                // 첫 단어에서 한 글자를 뺀 경우 (비교 단어가 하나 짧음)
                if (same == word.length()) ans++;
            } else if (firstWord.length() == word.length() - 1) {
                // 첫 단어에서 한 글자를 더한 경우 (비교 단어가 하나 김)
                if (same == firstWord.length()) ans++;
            }
        }
        System.out.println(ans);
    }

    // 알파벳 빈도수를 세는 헬퍼 함수
    static int[] getCount(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'A']++;
        }
        return count;
    }
}