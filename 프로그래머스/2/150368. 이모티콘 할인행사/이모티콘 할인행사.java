import java.util.*;

class Solution {
    int[] rates = { 10, 20, 30, 40 };
    int[] output;
    int totalSubs = 0, totalSales = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        output = new int[emoticons.length];
        
        dfs(0, emoticons, users);
        
        int[] answer = {totalSubs, totalSales};
        return answer;
    }
    
    private void dfs(int n, int[] emoticons, int[][] users) {
        if (n == emoticons.length) {
            int subs = 0, sales = 0;
            for (int i = 0; i < users.length; i++) {
                int rate = users[i][0];
                int cost = users[i][1];
                
                int totalCost = 0;
                for (int j = 0; j < output.length; j++) {
                    if (output[j] >= rate) {
                        totalCost += emoticons[j] * (100 - output[j]) / 100;
                    }
                }
            
                if (totalCost >= cost) {
                    subs++;
                } else {
                    sales += totalCost;
                }
            }
            
            if (subs > totalSubs) {
                totalSubs = subs;
                totalSales = sales;
            } else if (subs == totalSubs) {
                totalSales = Math.max(totalSales, sales);
            }
            
            return;
        }
        
        for (int i = 0; i < rates.length; i++) {
            output[n] = rates[i];
            dfs(n + 1, emoticons, users);
        }
    }
}