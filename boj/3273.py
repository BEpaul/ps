import sys

def twoSum(nums, result):
    l, r = 0, len(nums) - 1
    count = 0
    nums.sort()

    while l < r:
        if nums[l] + nums[r] == result:
            count += 1
            l += 1
        elif nums[l] + nums[r] > result:
            r -= 1
        else:
            l += 1

    return count


n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))
x = int(sys.stdin.readline())

answer = twoSum(nums, x)
print(answer)