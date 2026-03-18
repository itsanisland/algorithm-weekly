import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String firstWord = br.readLine();
        char[] firstArr = firstWord.toCharArray();
        Arrays.sort(firstArr);
        
        int ans = 0;
        for (int i = 1; i < N; i++) {
            String word = br.readLine();
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            
            int diffLen = Math.abs(firstWord.length() - word.length());
            if (diffLen > 1) continue;

            int sameCnt = 0;
            int p1 = 0, p2 = 0;

            // 두 포인터로 공통 문자 개수 찾기
            while (p1 < firstArr.length && p2 < arr.length) {
                if (firstArr[p1] == arr[p2]) {
                    sameCnt++;
                    p1++;
                    p2++;
                } else if (firstArr[p1] < arr[p2]) {
                    p1++;
                } else {
                    p2++;
                }
            }

            // 비슷한 단어 판정 조건
            int firstLen = firstArr.length;
            int wordLen = arr.length;

            if (firstLen == wordLen) {
                // 1. 완전히 같음 (sameCnt == 길이)
                // 2. 한 글자 교체 (sameCnt == 길이 - 1)
                if (sameCnt >= firstLen - 1) ans++;
            } else if (firstLen == wordLen + 1) {
                // 3. 첫 단어에서 한 글자 삭제 (비교 단어가 하나 짧음)
                if (sameCnt == wordLen) ans++;
            } else if (firstLen == wordLen - 1) {
                // 4. 첫 단어에서 한 글자 추가 (비교 단어가 하나 김)
                if (sameCnt == firstLen) ans++;
            }
        }
        System.out.println(ans);
    }
}