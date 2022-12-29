from collections import deque

N, K = map(int, input().split())
deq_origin, deq_rev = deque(), deque()

for i in range(N):
    deq_origin.append(i+1)

while deq_origin:

    for j in range(K-1):
        deq_origin.append(deq_origin.popleft())
    deq_rev.append(deq_origin.popleft())

print("<", end="")
for d in range(N-1):
    print(deq_rev[d], end=", ")
print(deq_rev[N-1], end="")
print(">")