import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[] times = new int[2410];
        
        for (int i = 0; i < book_time.length; i++) {
            String startTime = book_time[i][0];
            String endTime = book_time[i][1];
            
            String[] startTimeSplitted = startTime.split(":");
            int sJoinedTime = Integer.parseInt(startTimeSplitted[0] + startTimeSplitted[1]);
            int eJoinedTime = getEndJoinedTime(endTime);
                
            for (int j = sJoinedTime; j <= eJoinedTime; j++) {
                times[j]++;
            }
        }
        
        for (int i = 0; i < 2410; i++) {
            if (answer < times[i]) {
                answer = times[i];
            }
        }
        
        return answer;
    }
    
    int getEndJoinedTime(String endTime) {
        String[] splitted = endTime.split(":");
        int hour = Integer.parseInt(splitted[0]);
        int minute = Integer.parseInt(splitted[1]);
        
        minute += 9;
        if (minute >= 60) {
            hour++;
            minute -= 60;
        }
        
        String hourStr = Integer.toString(hour);
        String minuteStr = "";
        
        if (minute < 10) {
            minuteStr += "0";
        }
        minuteStr += Integer.toString(minute);
        
        return Integer.parseInt(hourStr + minuteStr);
    }
}