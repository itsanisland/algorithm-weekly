import java.util.*;

class Solution {
    int maxDiff = 0;
    int[] answer;
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        dfs(info, new int[11], n, 0);
        
        return maxDiff == 0 ? new int[] {-1} : answer;
    }
    
    private void dfs(int[] aInfo, int[] rInfo, int n, int i) {
        if (i == 11) {
            rInfo[10] += n;
            evaluate(aInfo, rInfo);
            rInfo[10] -= n;
            return;
        }

        int cnt = aInfo[i] + 1;  // 이기기 위해 필요한 화살 수

        // 이 점수에서 이길 수 있을 때만
        if (n >= cnt) {
            rInfo[i] = cnt;
            dfs(aInfo, rInfo, n - cnt, i + 1);
            rInfo[i] = 0; // 백트래킹
        }

        // 이 점수 포기
        dfs(aInfo, rInfo, n, i + 1);
    }
    
    private void evaluate(int[] aInfo, int[] rInfo) {
        int rScore = 0, aScore = 0;
        for (int j = 0; j < 11; j++) {
            if (rInfo[j] == 0 && aInfo[j] == 0) continue;
            if (rInfo[j] > aInfo[j]) rScore += 10 - j;
            else aScore += 10 - j;
        }

        int diff = rScore - aScore;
        if (diff <= 0) return;

        if (diff > maxDiff || (diff == maxDiff && isBetter(rInfo, answer))) {
            maxDiff = diff;
            answer = rInfo.clone();
        }
    }

    private boolean isBetter(int[] cur, int[] best) {
        for (int i = 10; i >= 0; i--) {
            if (cur[i] > best[i]) return true;
            else if (cur[i] < best[i]) return false;
        }
        return false;
    }
}