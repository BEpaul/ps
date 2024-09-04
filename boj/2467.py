import sys

n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))

left = 0
right = n - 1
answer = 0
sum = 2000000000
while left < right:
    res = nums[left] + nums[right]
    if sum > abs(res):
        sum = abs(res)
        answer = [nums[left], nums[right]]
    
    if res > 0:
        right -= 1
    elif res < 0:
        left += 1
    else:
        break

print(answer[0], answer[1])