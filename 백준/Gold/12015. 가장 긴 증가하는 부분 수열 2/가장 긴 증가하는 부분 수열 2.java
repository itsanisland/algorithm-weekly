import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, length;
	private static int[] A, lis;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		
        A = new int[N];
        lis = new int[N];
        
        for (int i = 0; i < N; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        
        lis[0] = A[0];
        length = 1;
        
        for (int i = 0; i < N; i++) {
        	if (A[i] > lis[length - 1]) {
        		lis[length++] = A[i];
        	} else {
        		int idx = binarySearch(lis, 0, length - 1, A[i]);
        		lis[idx] = A[i];
        	}
        }
        
        System.out.println(length);
	}
	
	private static int binarySearch(int[] arr, int start, int end, int k) {
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] < k) {
				start = mid + 1;
			}
			else end = mid;
		}
		return start;
	}

}