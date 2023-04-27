import sys
from bisect import bisect_left

n = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().split()))

li = []

for num in A:
    low_idx = bisect_left(li, num)
    if len(li) == low_idx:
        li.append(num)
    else:
        li[low_idx] = num

print(len(li))