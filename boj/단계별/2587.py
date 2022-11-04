import sys

num_list = []
for _ in range(5):
    num = int(sys.stdin.readline())
    num_list.append(num)

num_list.sort()
print(sum(num_list) // 5)
print(num_list[2])