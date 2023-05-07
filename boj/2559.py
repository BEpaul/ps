import sys
n, k = map(int, sys.stdin.readline().split())
li = list(map(int, input().split()))

P = [0] * n
P[0] = li[0]
res = []

for i in range(1, n):
    P[i] = P[i-1] + li[i]

for j in range(n):
    idx = j-k
    if 0 <= idx <= n:
        res.append(P[j] - P[idx])
    elif idx == -1:
        res.append(P[j])

print(max(res))