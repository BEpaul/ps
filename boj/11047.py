import sys

N, K = map(int, sys.stdin.readline().split())

value_list = []

for _ in range(N):
    value = int(sys.stdin.readline())
    value_list.append(value)

value_list.reverse()

count = 0
for idx in range(N):
    quo = K // value_list[idx]
    count += quo
    rem = K % value_list[idx]
    K = rem

print(count)