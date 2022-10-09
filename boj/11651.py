import sys

N = int(sys.stdin.readline())

xy_list = []
for i in range(N):
    temp_list = list(map(int, sys.stdin.readline().split()))
    xy_list.append(temp_list)

xy_list.sort(key= lambda t: (t[1], t[0]))

for j in xy_list:
    print(j[0], j[1])