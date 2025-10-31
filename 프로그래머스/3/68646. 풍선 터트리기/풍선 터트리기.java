import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        
        int[] lMinArr = new int[n];
        int[] rMinArr = new int[n];
        
        int lMin = Integer.MAX_VALUE, rMin = Integer.MAX_VALUE;
        lMinArr[0] = lMin; rMinArr[n - 1] = rMin;
        
        for (int i = 1; i < n - 1; i++) {
            if (a[i - 1] < lMin) {
                lMin = a[i - 1];
            }
            
            lMinArr[i] = lMin;
            
            if (a[n - i] < rMin) {
                rMin = a[n - i];
            }
            
            rMinArr[n - 1 - i] = rMin;
        }
        
        int answer = n;
        
        for (int i = 0; i < n; i++) {
            if (lMinArr[i] < a[i] && rMinArr[i] < a[i]) answer--;
        }
        
        return answer;
    }
}