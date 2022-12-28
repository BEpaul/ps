import sys
from collections import deque

N = int(sys.stdin.readline())
deq = deque()

while True:
    number = int(sys.stdin.readline())
    if number == -1:
        break

    elif number == 0:
        deq.popleft()

    else:
        if len(deq) == N:
            continue
        else:
            deq.append(number)

if deq:
    for d in deq:
        print(d, end=" ")
else:
    print("empty")