class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoH = Integer.parseInt(video_len.split(":")[0]);
        int videoM = Integer.parseInt(video_len.split(":")[1]);
        int videoMins = videoH * 60 + videoM;
        int posH = Integer.parseInt(pos.split(":")[0]);
        int posM = Integer.parseInt(pos.split(":")[1]);
        int posMins = posH * 60 + posM;
        int opStartH = Integer.parseInt(op_start.split(":")[0]);
        int opStartM = Integer.parseInt(op_start.split(":")[1]);
        int opStartMins = opStartH * 60 + opStartM;
        int opEndH = Integer.parseInt(op_end.split(":")[0]);
        int opEndM = Integer.parseInt(op_end.split(":")[1]);
        int opEndMins = opEndH * 60 + opEndM;
        
        for (String cmd: commands) {
            if (opStartMins <= posMins && posMins <= opEndMins) {
                posMins = opEndMins;
            }
            
            if (cmd.equals("next")) {
                if (videoMins - posMins < 10) {
                    posMins = videoMins;
                } else {
                    posMins += 10;
                }
            } else {
                if (posMins < 10) {
                    posMins = 0;
                } else {
                    posMins -= 10;
                }
            }
            
            if (opStartMins <= posMins && posMins <= opEndMins) {
                posMins = opEndMins;
            }
        }
        
        posH = posMins / 60;
        posM = posMins % 60;
        
        return (posH < 10 ? "0" : "") + posH + ":" + (posM < 10 ? "0" : "") + posM;
    }
}