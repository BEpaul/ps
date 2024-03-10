import java.util.*;

class Solution {
    static final int MODULO = 1_000_000_007;
    static int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n + 1][m + 1];
        dp[1][1] = 1;
        
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] += (dp[i - 1][j] + dp[i][j - 1]) % MODULO;        
                }
            }
        }
        
        return dp[n][m] % MODULO;
    }
}