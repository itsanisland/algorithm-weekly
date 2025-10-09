import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {

            public int compare(String s1, String s2) {
                int pos = 0;
                String[] parsedS1 = parse(s1);
                String[] parsedS2 = parse(s2);
                String s1Head = parsedS1[0], s2Head = parsedS2[0];
                int s1Num = Integer.parseInt(parsedS1[1]), s2Num = Integer.parseInt(parsedS2[1]);
                
                if (s1Head.equals(s2Head)) {
                    return Integer.compare(s1Num, s2Num);
                }
                
                // System.out.println(parsedS1[0] + " " + parsedS1[1]);
                
                return s1Head.compareTo(s2Head);
            }
        });
        
        return files;
    }
    
    private String[] parse(String s) {
        String[] result = {"", ""};
        boolean finish = false;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('0' <= ch && ch <= '9') {
                result[1] += ch;
                if (!finish) finish = true;
            } else {
                if (finish) break;
                result[0] += ch;
            }
        }
        
        result[0] = result[0].toUpperCase();
        
        return result;
    } 
}