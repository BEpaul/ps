import java.util.*;

class Solution {
    static Stack<Integer> stack = new Stack<>();
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int priceIdx = stack.pop();
                answer[priceIdx] = i - priceIdx;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = prices.length - 1 - idx;
        }
        
        return answer;
    }
}