import sys

def get_right_bigger_number(nums_size, nums):
    stack = [0]
    output = [-1] * nums_size
    
    for i in range(1, nums_size):
        while stack and nums[i] > nums[stack[-1]]:
            output[stack[-1]] = nums[i]
            stack.pop()

        stack.append(i)
    
    return output

n = int(sys.stdin.readline())
a = list(map(int, sys.stdin.readline().split()))

result = get_right_bigger_number(n, a)

print(*result)