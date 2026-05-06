class Solution {
    public int[] solution(String[] park, String[] routes) {
        int r = park.length;
        int c = park[0].length();
        
        int y = 0, x = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (park[i].charAt(j) == 'S') {
                    y = i;
                    x = j;
                    break;
                }
            }
        }
        
        for (String route : routes) {
            String[] splits = route.split(" ");
            String op = splits[0];
            int n = Integer.parseInt(splits[1]);
            int ny = y, nx = x;
            boolean ck = false;
            
            switch (op) {
                case "N":
                    for (int i = 0; i < n; i++) {
                        ny--;
                        if (ny < 0 || park[ny].charAt(nx) == 'X') {
                            ck = true;
                            break;
                        }
                    }
                    break;
                case "S":
                    for (int i = 0; i < n; i++) {
                        ny++;
                        if (ny == r || park[ny].charAt(nx) == 'X') {
                            ck = true;
                            break;
                        }
                    }
                    break;
                case "W":
                    for (int i = 0; i < n; i++) {
                        nx--;
                        if (nx < 0 || park[ny].charAt(nx) == 'X') {
                            ck = true;
                            break;
                        }
                    }
                    break;
                case "E":
                    for (int i = 0; i < n; i++) {
                        nx++;
                        if (nx == c || park[ny].charAt(nx) == 'X') {
                            ck = true;
                            break;
                        }
                    }
                    break;
            }
    
            if (ck) continue;
            
            y = ny;
            x = nx;
        }
        
        int[] answer = {y, x};
        return answer;
    }
}