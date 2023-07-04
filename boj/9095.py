dp = [0, 1, 2, 4]
t = int(input())

for _ in range(4, 14):
    dp.append(sum(dp[-3:]))

for __ in range(t):
    n = int(input())
    print(dp[n])

# input 1~4 -> output 1, 2, 4, 7
# dp[n] = dp[n-3] + dp[n-2] + dp[n-1]