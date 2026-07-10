class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            int i = index;
            while (i-- > 0) {
                c++;
                
                if (c > 'z') {
                        c = 'a';
                    }
            
                if (skip.contains(c + "")) {
                    i++;
                }
            }
            
            sb.append(c);
        }
        
        return sb.toString();
    }
}