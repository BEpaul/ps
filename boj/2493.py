import sys

def get_top_with_laser(nums, nums_size):
    stack = []
    output = [0] * nums_size

    for i in range(nums_size):
        while stack:
            if stack and nums[stack[-1]] >= nums[i]:
                output[i] = stack[-1] + 1
                break
            else:
                stack.pop()
                
        stack.append(i)
    
    return output

n = int(sys.stdin.readline())
tops = list(map(int, sys.stdin.readline().split()))

answer = get_top_with_laser(tops, n)
print(*answer)