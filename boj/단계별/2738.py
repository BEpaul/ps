import sys

N, M = map(int, sys.stdin.readline().split())

li_1 = []
li_2 = []

for _ in range(N):
    append_list = list(map(int, sys.stdin.readline().split()))
    li_1.append(append_list)

for __ in range(N):
    append_list = list(map(int, sys.stdin.readline().split()))
    li_2.append(append_list)

for i in range(N):
    for j in range(M):
        print(li_1[i][j] + li_2[i][j], end=" ")
    print("")