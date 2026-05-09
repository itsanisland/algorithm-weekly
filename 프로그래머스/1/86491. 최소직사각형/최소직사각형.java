// 모든 명함을 가로가 세로보다 길게(혹은 그 반대로) 눕혀버린다.
class Solution {
    public int solution(int[][] sizes) {
        int maxH = 0;
        int maxW = 0;
        
        for (int[] size : sizes) {
            // 1. 가로, 세로 중 더 긴 값을 w, 짧은 값을 h로 둔다. (명함을 돌리는 행위)
            int w = Math.max(size[0], size[1]);
            int h = Math.min(size[0], size[1]);
            
            // 2. 긴 값들 중 최대와 짧은 값들 중 최대를 구한다.
            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }
                
        return maxW * maxH;
    }
}