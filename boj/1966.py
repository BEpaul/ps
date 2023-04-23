from collections import deque
import sys

tc = int(input())

for _ in range(tc):
    n, m = map(int, sys.stdin.readline().split())
    li = deque(list(map(int, sys.stdin.readline().split())))
    idx = 0
    count = 0
    
    while li:
        if idx == m:
            if li[0] >= max(li):
                count += 1
                print(count)
                break
            else:
                li.append(li.popleft())
                m += len(li)

        else:
            if li[0] >= max(li):
                count += 1
                li.popleft()
            else:
                li.append(li.popleft())
        idx += 1

