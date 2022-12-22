import sys
from collections import deque

N = int(sys.stdin.readline())
deq = deque()

for _ in range(N):
    command = list(sys.stdin.readline().split())

    if command[0] == 'push':
        deq.append(int(command[1]))
    
    elif command[0] == 'pop':
        if deq:
            print(deq[0])
            deq.popleft()
        else:
            print(-1)
    
    elif command[0] == 'size':
        print(len(deq))
    
    elif command[0] == 'empty':
        if deq:
            print(0)
        else:
            print(1)
    
    elif command[0] == 'front':
        if deq:
            print(deq[0])
        else:
            print(-1)

    elif command[0] == 'back':
        if deq:
            print(deq[len(deq)-1])
        else:
            print(-1)

# 파이썬 list 너무 느리다 ㅜㅜ
# deque(데크)를 활용하여 시간복잡도를 효율적으로 관리할 수 있다.