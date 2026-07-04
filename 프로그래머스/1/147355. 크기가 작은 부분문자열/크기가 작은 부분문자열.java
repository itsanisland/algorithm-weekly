class Solution {
    public int solution(String t, String p) {
        int pp = p.length() - 1;
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < p.length(); i++) {
            sb.append(t.charAt(i));
        }
        
        int ans = 0;
        while (true) {
            String num = sb.toString();

            if (num.compareTo(p) <= 0) {
                ans++;
            }
            
            pp += 1;
            
            if (pp == t.length()) {
                break;
            }
            
            sb.deleteCharAt(0);
            sb.append(t.charAt(pp));
        }

        return ans;
    }
}