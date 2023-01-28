import sys

K, N = map(int, sys.stdin.readline().rstrip().split())
line_length = []
for _ in range(K):
    line_length.append(int(sys.stdin.readline().rstrip()))

start, end = 1, max(line_length)

while start <= end:
    mid = (start + end) // 2
    lines = 0
    
    for line in line_length:
        lines += line // mid

    if lines >= N:
        start = mid + 1
    else:
        end = mid - 1

print(end)