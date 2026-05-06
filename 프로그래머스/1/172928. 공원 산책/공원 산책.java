import java.util.*;

class Solution {
    // 2. 방향 정의 (N, S, W, E)
    private static final int[] DY = {-1, 1, 0, 0};
    private static final int[] DX = {0, 0, -1, 1};
    private static final String DIR = "NSWE";
    
    public int[] solution(String[] park, String[] routes) {
        int r = park.length;
        int c = park[0].length();
        int y = 0, x = 0;

        // 1. 시작 지점 찾기
        for (int i = 0; i < r; i++) {
            if (park[i].contains("S")) {
                y = i;
                x = park[i].indexOf("S");
                break;
            }
        }

        for (String route : routes) {
            String[] split = route.split(" ");
            int dirIdx = DIR.indexOf(split[0]);
            int dist = Integer.parseInt(split[1]);

            int ny = y, nx = x;
            boolean isPossible = true;

            // 3. 이동 검증
            for (int i = 0; i < dist; i++) {
                ny += DY[dirIdx];
                nx += DX[dirIdx];

                // 범위 밖이거나 장애물(X)을 만난 경우
                if (ny < 0 || ny >= r || nx < 0 || nx >= c || park[ny].charAt(nx) == 'X') {
                    isPossible = false;
                    break;
                }
            }

            // 4. 이동이 가능할 때만 좌표 업데이트
            if (isPossible) {
                y = ny;
                x = nx;
            }
        }

        return new int[]{y, x};
    }
}