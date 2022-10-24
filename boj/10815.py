import sys

N = int(sys.stdin.readline())
num_set = set(sys.stdin.readline().split())
M = int(sys.stdin.readline())
cmp_list = list(sys.stdin.readline().split())

for cmp in cmp_list:
    if cmp in num_set:
        print(1, end=" ")
    else:
        print(0, end=" ")


# 요소들에 대한 문제는 이진탐색(binary search)로 접근하면 시간을 줄일 수 있다.