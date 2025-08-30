import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		// 1. K 이상인 가장 작은 2의 거듭제곱
        int size = 1;
        while (size < K) size <<= 1;

        // 2. 그리디로 최소 쪼개기 횟수 계산
        int breaks = 0;
        int rem = K;
        int piece = size;

        while (rem > 0) {
            if (rem >= piece) {
                rem -= piece;       // 이 크기의 조각을 사용
            } else {
                piece >>= 1;        // 필요 이상 크면 반으로 쪼갠다
                breaks++;           // 쪼개기 1회
            }
        }

        System.out.println(size + " " + breaks);
	}

}