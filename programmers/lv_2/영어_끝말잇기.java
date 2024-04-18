import java.io.*;
import java.util.*;

class Solution {
    static HashSet<String> hashSet = new HashSet<>();
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int turn = 0;
        int count = 1;
        int i = 0;
        int end = -1;
        for (String word : words) {
            turn++;
            if (turn == n + 1) {
                turn = 1;
                count++;
            }
            
            if (hashSet.contains(word)) {
                answer[0] = turn;
                answer[1] = count;
                break;
            }
            
            if (i >= 1) {
                if (end != word.charAt(0)) {
                    answer[0] = turn;
                    answer[1] = count;
                    break;
                }
            }
            
            hashSet.add(word);
            end = word.charAt(word.length() - 1);
            i++;
        }

        return answer;
    }
}