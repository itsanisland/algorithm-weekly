class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            int cnt = food[i];
            if (cnt >= 2) {
                for (int j = 0; j < cnt / 2; j++) {
                    sb.append(i);
                }
            }
        }
        
        StringBuilder reversed = new StringBuilder(sb.toString());
        reversed.reverse();
        
        sb.append(0).append(reversed);
        return sb.toString();
    }
}