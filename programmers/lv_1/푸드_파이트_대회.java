import java.util.*;

class Solution {
    public String solution(int[] food) {
        int len = food.length;
        StringBuilder sb = new StringBuilder();
        int[] foodCount = new int[len];
        
        for (int i = 1; i < len; i++) {
            foodCount[i] = food[i] / 2;
            for (int j = 0; j < foodCount[i]; j++) {
                sb.append(String.valueOf(i));
            }
        }
        
        sb.append("0");
        
        for (int i = len - 1; i >= 1; i--) {
            for (int j = 0; j < foodCount[i]; j++) {
                sb.append(String.valueOf(i));
            }
        }
        
        return sb.toString();
    }
}