import java.util.*;

class Solution {
    static int n;
    static int answer;
    static int basketIdx;
    static int[] basket;
    public int solution(int[][] board, int[] moves) {
        n = board.length;
        basket = new int[n * n + 1];
        
        for (int move : moves) {
            pick(board, move);
        }
        
        return answer;
    }
    
    void pick(int[][] board, int move) {
        for (int i = 0; i < n; i++) {
            if (board[i][move - 1] > 0) {
                putToBasket(board[i][move - 1]);
                board[i][move - 1] = 0;
                break;
            }
        }
    }
    
    void putToBasket(int doll) {
        if (basket[basketIdx] == doll) {
            answer += 2;
            basket[basketIdx] = 0;
            basketIdx--;
        } else {
            basket[++basketIdx] = doll;
        }
    }
}