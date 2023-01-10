def fibonacci(n):
    if n == 1 or n == 2:
        return 1
    else:
        return fibonacci(n-1) + fibonacci(n-2)

def dp_fib(n):
    dp = [0] * (n+1)
    dp[1], dp[2] = 1, 1
    cnt = 0
    for i in range(3, n+1):
        cnt += 1
        dp[i] = dp[i-1] + dp[i-2]
    return cnt

n = int(input())
print(fibonacci(n), dp_fib(n)) 