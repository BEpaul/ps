from collections import deque
import sys

dq = deque()

n = int(sys.stdin.readline())
for _ in range(n):
    input_list = list(sys.stdin.readline().split())
    
    if input_list[0] == 'push':
        dq.append(input_list[1])

    elif input_list[0] == 'pop':
        if not dq:
            print(-1)
            continue
        print(dq.popleft())

    elif input_list[0] == 'size':
        print(len(dq))

    elif input_list[0] == 'empty':
        if dq:
            print(0)
        else:
            print(1)
    
    elif input_list[0] == 'front':
        if not dq:
            print(-1)
            continue
        print(dq[0])

    elif input_list[0] == 'back':
        if not dq:
            print(-1)
            continue
        print(dq[len(dq)-1])