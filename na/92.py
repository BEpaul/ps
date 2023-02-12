import sys

N, M, K = map(int, sys.stdin.readline().split())

num_list = list(map(int, sys.stdin.readline().split()))
num_list.sort(reverse=True)
result = 0
temp = 0

for _ in range(M):
    if temp == K:
        temp = 0
        result += num_list[1]
        continue

    result += num_list[0]
    temp += 1
    
print(result)