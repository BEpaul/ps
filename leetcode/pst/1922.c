#define MOD 1000000007

int fastpow(long long base, long long exp) {
    int result = 1;

    for (;;) {
        if (exp & 1) {
            result = result * base % MOD;
        }
        exp >>= 1;
        if (!exp) {
            break;
        }
        
        base = base * base % MOD;
    }

    return result;
}

int countGoodNumbers(long long n) {
    return (long long)fastpow(5, (n + 1) / 2) * fastpow(4, n - (n + 1) / 2) % MOD;
}