class Solution {
    public int[] solution(String[] wallpaper) {
        int minR = 50, maxR = 0;
        int minC = 50, maxC = 0;
        
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                char c = wallpaper[i].charAt(j);
                if (c == '#') {
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i + 1);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j + 1);
                }
            }
            
        }
        
        int[] answer = {minR, minC, maxR, maxC};
        return answer;
    }
}