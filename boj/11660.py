import sys

N, M = map(int, sys.stdin.readline().split())
A = []
P = []

for __ in range(N+1):
    P.append([0]*(N+1))

for _ in range(N):
    temp_list = list(map(int, sys.stdin.readline().split()))
    A.append(temp_list)

# (1,1) ~ (x,y) 의 합
for i in range(1, N+1):
    for j in range(1, N+1):
        P[i][j] = P[i-1][j] + P[i][j-1] - P[i-1][j-1] + A[i-1][j-1]

# 부분합
for __ in range(M):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    print(P[x2][y2] - P[x1-1][y2] - P[x2][y1-1] + P[x1-1][y1-1])