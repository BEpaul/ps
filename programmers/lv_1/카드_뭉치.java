import java.util.*;

class Solution {
    static Stack<String> stack1;
    static Stack<String> stack2;
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        stack1 = createStack(cards1);
        stack2 = createStack(cards2);
        
        for (String g : goal) {
            if (!consumeIfMatch(stack1, g) && !consumeIfMatch(stack2, g)) {
                return "No";
            }
        }
        
        return "Yes";
    }
    
    Stack<String> createStack(String[] cards) {
        Stack<String> stack = new Stack<>();
        for (int i = cards.length - 1; i >= 0; i--) {
            stack.add(cards[i]);
        }
        return stack;
    }
    
    boolean consumeIfMatch(Stack<String> stack, String target) {
        if (!stack.isEmpty() && stack.peek().equals(target)) {
            stack.pop();
            return true;
        }
        return false;
    }
}