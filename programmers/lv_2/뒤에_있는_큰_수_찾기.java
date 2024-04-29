import java.util.*;

class Solution {
    Stack<Integer> stack = new Stack<>();
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] reverseNumbers = new int[len];
        int[] answer = new int[len];
        
        for (int i = 0; i < len; i++) {
            reverseNumbers[i] = numbers[len - i - 1];
        }
        
        answer[len - 1] = -1;
        stack.push(reverseNumbers[0]);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek() > reverseNumbers[i]) {
                    answer[len - i - 1] = stack.peek();
                    stack.push(reverseNumbers[i]);
                    break;
                } else {
                    stack.pop();
                }
            }
            
            if (stack.isEmpty()) {
                stack.push(reverseNumbers[i]);
                answer[len - i - 1] = -1;    
            }
        }
        
        return answer;
    }
}