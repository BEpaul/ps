import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int size = topping.length;
        
        if (size == 1) {
            return answer;
        }
        
        int[] leftToRight = new int[size];
        int[] rightToLeft = new int[size];
        
        Set<Integer> leftToRightSet = new HashSet<>();
        Set<Integer> rightToLeftSet = new HashSet<>();
        leftToRight[0] = 1;
        rightToLeft[size - 1] = 1;
        leftToRightSet.add(topping[0]);
        rightToLeftSet.add(topping[size - 1]);
        
        for (int i = 1; i < size; i++) {
            leftToRight[i] = leftToRight[i - 1];
            rightToLeft[size - i - 1] = rightToLeft[size - i];
            
            if (!leftToRightSet.contains(topping[i])) {
                leftToRight[i]++;
                leftToRightSet.add(topping[i]);
            }
            
            if (!rightToLeftSet.contains(topping[size - i - 1])) {
                rightToLeft[size - i - 1]++;
                rightToLeftSet.add(topping[size - i - 1]);
            }
        }
        
        for (int i = 1; i < size; i++) {
            if (leftToRight[i - 1] == rightToLeft[i]) answer++;
        }
        
        return answer;
    }
}