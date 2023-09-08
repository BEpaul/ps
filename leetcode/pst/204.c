// Sieve of Eratosthenes

int isPrime[5000001];

int countPrimes(int n) {
    int i;
    for (i = 2; i < n; i++) isPrime[i] = 1;

    for (i = 2; i * i < n; i++) {
        if (!isPrime[i]) continue;

        for (int j = i * i; j < n; j += i) isPrime[j] = 0;
    }

    int count = 0;
    for (i = 2; i < n; i++) count += isPrime[i];
    return count; 
}
