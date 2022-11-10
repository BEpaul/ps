import sys

N, K = map(int, sys.stdin.readline().split())

A = list(map(int, sys.stdin.readline().split()))
P = [0]
p = 0
for temp in A:
    p += temp
    P.append(p)

max = -1

for i in range(N+1):
    if i+K > N:
        break
    if P[i+K]-P[i] > max:
        max = P[i+K]-P[i]

print(max)