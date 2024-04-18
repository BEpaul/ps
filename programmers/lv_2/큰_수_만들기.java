import java.io.*;
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int length = number.length() - k;
        int start = 0;
        
        for (int i = 0; i < length; i++) {
            char max = '0';
            for (int j = start; j <= i + k; j++) {
                if (number.charAt(j) > max) {
                    max = number.charAt(j);
                    start = j + 1;
                    
                    if (max == '9') break;
                }
            }
            
            sb.append(max);
        }
        
        return sb.toString();
    }
}