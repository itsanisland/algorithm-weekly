class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[arr.length - (arr.length == 1 ? 0 : 1)];
        int min = Integer.MAX_VALUE;
        int idx = 0;
        
        for (int num : arr) {
            min = Math.min(min, num);
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == min) {
                continue;
            }
            answer[idx++] = arr[i];
        }
        
        if (arr.length == 1) {
            answer[idx] = -1;
        }
        
        return answer;
    }
}