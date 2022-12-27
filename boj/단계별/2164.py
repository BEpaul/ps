from collections import deque

N = int(input())

deq = deque()
for i in range(N):
    deq.append(i+1)

if N == 1:
    print(1)

else:
    while True:
        deq.popleft()
        deq.append(deq[0])
        deq.popleft()

        if len(deq) == 1:
            break

    print(deq[0])
