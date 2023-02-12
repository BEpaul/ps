import sys

N, M = map(int, sys.stdin.readline().split())
res_list = []

for _ in range(N):
    num_list = list(map(int, sys.stdin.readline().split()))
    min_value = min(num_list)
    res_list.append(min_value)

print(max(res_list))
