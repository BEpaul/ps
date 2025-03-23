import sys
from collections import deque

sys.stdin = open('a/input.txt', 'r')

N = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().split()))
balloons = deque((i + 1, num) for i, num in enumerate(numbers))
answer = []
while balloons:
    index, num = balloons.popleft()
    answer.append(index)
    if num > 0:
        balloons.rotate(-(num - 1))
    else:
        balloons.rotate(-num)

print(*answer)