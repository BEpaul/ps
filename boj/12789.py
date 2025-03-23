import sys
from collections import deque
sys.stdin = open('a/input.txt', 'r')

N = int(sys.stdin.readline().rstrip())
line = deque(list(map(int, sys.stdin.readline().split())))
stack = []
order = 1

while line or stack:
    if stack and stack[-1] == order:
        stack.pop()
        order += 1
        continue

    if line and line[0] == order:
        line.popleft()
        order += 1
        continue
    
    if not line:
        break

    if not stack or line[0] <= stack[-1]:
        stack.append(line.popleft())
        continue
    break

if not line and not stack:
    print('Nice')
else:
    print('Sad')