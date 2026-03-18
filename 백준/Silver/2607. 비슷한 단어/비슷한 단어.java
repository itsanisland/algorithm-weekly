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
            
            // 길이 차이가 2 이상이면 절대 비슷한 단어가 될 수 없음
            if (diffLen > 1) continue;

            int diffCnt = 0;
            int p1 = 0, p2 = 0;

            // 두 포인터로 정렬된 배열 비교
            while (p1 < firstArr.length && p2 < arr.length) {
                if (firstArr[p1] == arr[p2]) {
                    p1++;
                    p2++;
                } else {
                    // 글자가 다르면 더 작은 쪽의 포인터를 이동시키고 차이 기록
                    if (firstArr[p1] < arr[p2]) p1++;
                    else p2++;
                    diffCnt++;
                }
            }

            // 루프 종료 후 남은 글자들도 차이에 포함 (중요!)
            diffCnt += (firstArr.length - p1) + (arr.length - p2);

            // 비슷한 단어 판정
            if (diffLen == 0) {
                // 완전히 같거나(0), 하나를 다른 걸로 교체(2)한 경우
                // (교체 시 p1++, p2++가 각각 따로 일어나서 diffCnt는 2가 됨)
                if (diffCnt <= 2) ans++;
            } else {
                // 길이가 1 차이 날 때, 차이 나는 글자가 딱 하나(1)인 경우
                if (diffCnt == 1) ans++;
            }
        }
        
        System.out.println(ans);
    }
}