int gameResult(int n, int k) {
    if (n == 1) {
        return 0;
    }

    return (k + gameResult(n-1, k)) % n;
}

int findTheWinner(int n, int k) {
    return gameResult(n, k) + 1;
}