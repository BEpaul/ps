import sys

N, M = map(int, sys.stdin.readline().split())
cnt = 0
str_set = set([sys.stdin.readline().strip() for _ in range(N)])

for _ in range(M):
    cmp_str = sys.stdin.readline().strip()
    if cmp_str in str_set:
        cnt += 1
    else:
        continue

print(cnt)