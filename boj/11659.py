import sys

N, M = map(int, sys.stdin.readline().split())

li = list(map(int, sys.stdin.readline().split()))
P = [0]
p = 0
for temp in li:
    p += temp
    P.append(p)

for _ in range(M):
    i, j = map(int, sys.stdin.readline().split())

    print(P[j]-P[i-1])