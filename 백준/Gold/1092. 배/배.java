import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        
        List<Integer> cranes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        
        List<Integer> boxes = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(cranes);
        Collections.sort(boxes);
        Collections.reverse(cranes);
        Collections.reverse(boxes);

        if (cranes.get(0) < boxes.get(0)) { // 모든 박스를 옮길 수 없는 경우
            System.out.println(-1);
            return;
        } 

        int ans = 0;
        int movedCnt = 0;
        boolean[] moved = new boolean[m];
        
        while (movedCnt < m) {
            int boxIdx = 0; // 분마다 초기화
            for (int i = 0; i < n; i++) {
                while (boxIdx < m) {
                    if (!moved[boxIdx] && boxes.get(boxIdx) <= cranes.get(i)) {
                        moved[boxIdx] = true;
                        movedCnt++;
                        boxIdx++; // 다음 박스
                        break;
                    }
                    boxIdx++; // 반드시 이동
                }
            }
            ans++;
        }

        System.out.println(ans);
    }
}