int a[1001][2001], sum[1001][2001];

int maxValueOfCoins(int** piles, int n, int* m, int k) {
    memset(a, 0, 1001 * 2001 * sizeof(int));
    memset(sum, 0, 1001 * 2001 * sizeof(int));

    for (int i = 0; i < n; i++) {
        sum[i][0] = 0;
        for (int j = 0; j < m[i]; j++) {
            sum[i][j+1] = sum[i][j] + piles[i][j];
        }
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++) {
            for (int c = 0; c <= j && c <= m[i-1]; c++) {
                if (a[i][j] < a[i-1][j-c] + sum[i-1][c]) {
                    a[i][j] = a[i-1][j-c] + sum[i-1][c];
                }
            }
        }
    }

    return a[n][k];
}