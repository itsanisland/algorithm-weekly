import java.util.*;

class Solution {
    
    int R, C;
    
    private boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;    
    }
    
    private boolean check(int r, int c, int size, String[][] park) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!inRange(r + i, c + j) || !park[r + i][c + j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int solution(int[] mats, String[][] park) {
        R = park.length;
        C = park[0].length;
        
        Arrays.sort(mats);
        
        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    if (park[j][k].equals("-1") && check(j, k, size, park)) {
                        return size;
                    }
                }
            } 
        }
        
        return -1;
    }
}