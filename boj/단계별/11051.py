def factorial(n):
    if n >= 1:
        return n * factorial(n-1)
    else:
        return 1

N, K = map(int, input().split())

res = factorial(N) // (factorial(N-K) * factorial(K))
print(res % 10007)