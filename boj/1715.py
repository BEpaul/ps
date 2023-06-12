import sys
import heapq

n = int(sys.stdin.readline())
heap = []
count = 0

for _ in range(n):
    heapq.heappush(heap, int(sys.stdin.readline()))
    
if n > 1:
    while len(heap) > 1:
        num_1 = heapq.heappop(heap)
        num_2 = heapq.heappop(heap)
        count += num_1 + num_2
        heapq.heappush(heap, num_1 + num_2)

print(count)