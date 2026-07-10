import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int sameCnt = 0;
        int zeroCnt = 0;
        int pl = 0, pw = 0;
    
        Arrays.sort(lottos);
        Arrays.sort(win_nums);
        
        while (pl < 6 && pw < 6) {
            if (lottos[pl] == 0) {
                zeroCnt++;
                pl++;
            } else {
                if (lottos[pl] == win_nums[pw]) {
                    sameCnt++;
                    pl++;
                    pw++;
                } else if (lottos[pl] < win_nums[pw]) {
                    pl++;
                } else {
                    pw++;
                }
            }
        }
        
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        int[] answer = {rank[sameCnt + zeroCnt], rank[sameCnt]};
        return answer;
    }
}