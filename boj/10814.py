import sys

N = int(sys.stdin.readline())
info_list = []

for i in range(N):
    info_list.append(list(sys.stdin.readline().split()))
    
info_list.sort(key=lambda t: int(t[0]))
for j in info_list:
    print(j[0], j[1])