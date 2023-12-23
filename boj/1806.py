import sys

def get_prefix_sum(nums, nums_size):
    prefix = [0] * (nums_size + 1)
    for i in range(1, nums_size + 1):
        prefix[i] = prefix[i-1] + nums[i-1]

    return prefix

def two_sum(nums, nums_size, target):
    l, r = 0, 1
    result = 100001

    while l < nums_size:
        if nums[r] - nums[l] >= target:
            result = min(result, r - l)
            l += 1
        else:
            if r < nums_size:
                r += 1
            else:
                l += 1

    return result

n, s = map(int, sys.stdin.readline().split())
nums = list(map(int, sys.stdin.readline().split()))

prefix_sum = get_prefix_sum(nums, n)
answer = two_sum(prefix_sum, n, s)

if answer == 100001:
    print(0)
else:
    print(answer)