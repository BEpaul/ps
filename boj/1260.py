from collections import deque

def dfs(cur):
    visited_dfs[cur] = 1
    print(cur, end=" ")
    for i in range(1, N+1):
        if not visited_dfs[i] and graph[cur][i]:
            dfs(i)

def bfs(cur):
    q = deque([cur])
    visited_bfs[cur] = 1
    while q:
        cur = q.popleft()
        print(cur, end=" ")
        for i in range(1, N+1):
            if not visited_bfs[i] and graph[cur][i]:
                q.append(i)
                visited_bfs[i] = 1

N, M, V = map(int, input().split())

visited_dfs = [0] * (N+1)
visited_bfs = [0] * (N+1)

graph = [[0 for col in range(N+1)] for row in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a][b] = 1
    graph[b][a] = 1


dfs(V)
print()
bfs(V)