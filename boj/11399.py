import sys

N = int(sys.stdin.readline())
li = list(map(int, sys.stdin.readline().split()))
li.sort()

res = 0
cnt = N
for i in range(N):
    res += li[i] * cnt
    cnt -= 1

print(res)