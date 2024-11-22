import sys
from collections import deque

sys.stdin = open('a/input.txt', 'r')
def bfs():
    queue = deque()
    queue.append(a)

    while queue:
        now = queue.popleft()
        if now == b:
            return dist[now]
        
        for next in graph[now]:
            if dist[next] > 0:
                continue
            
            dist[next] = dist[now] + 1
            queue.append(next)

    return -1

n = int(sys.stdin.readline())
graph = [[] for _ in range(n + 1)]
a, b = map(int, sys.stdin.readline().split())
m = int(sys.stdin.readline())
dist = [0 for _ in range(n + 1)]

for _ in range(m):
    s, e = map(int, sys.stdin.readline().split())
    graph[s].append(e)
    graph[e].append(s)

print(bfs())