import java.io.*;
import java.util.*;

class Solution {
    static Deque<String> deque = new ArrayDeque<>();
    static Stack<String> stack;
    static int answer;
    static List<String> closeBrackets = List.of(")", "]", "}");
    static List<String> openBrackets = List.of("(", "[", "{");
    
    public int solution(String s) {
        int size = s.length();
        int iter = size;
        String[] splitted = s.split("");
        for (String sp : splitted) {
            deque.addLast(sp);
        }
        
        while (iter-- > 0) {
            stack = new Stack<>();
            
            for (String str : deque) {
                if (stack.isEmpty()) {
                    if (closeBrackets.contains(str)) {
                        stack.push(str);
                        break;  
                    } 
                    stack.push(str);
                } else {
                    if (closeBrackets.contains(str)) {
                        String open = stack.peek();
                        if (open.equals("(") && str.equals(")")) {
                            stack.pop();
                        } else if (open.equals("{") && str.equals("}")) {
                            stack.pop();
                        } else if (open.equals("[") && str.equals("]")) {
                            stack.pop();
                        }
                    } else {
                        stack.push(str);
                    }
                }
            }
            
            if (stack.isEmpty()) answer++;
            
            String tmp = deque.pollFirst();
            deque.addLast(tmp);
        }
        
        return answer;
    }
}