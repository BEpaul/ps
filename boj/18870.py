import sys
from collections import defaultdict

n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))
dict = defaultdict(int)

sorted_nums_set = sorted(set(nums))

index = 0
for num in sorted_nums_set:
    dict[num] = index
    index += 1

for num in nums:
    print(dict[num], end=' ')