int min(int a, int b) {
    if (a >= b) return b;
    else return a;
}

int numSquares(int n) {
    int dp[10001];
    for (int i = 0; i <= n; i++) {
        dp[i] = INT_MAX-1;
    }
    dp[0] = 0;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j * j <= i; j++) {
            dp[i] = min(dp[i - j * j] + 1, dp[i]);
        }
    }

    return dp[n];
}