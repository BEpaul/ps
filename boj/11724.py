import sys
sys.setrecursionlimit(10 ** 5)

# dfs 함수
def dfs(v):
    visited[v] = True
    for e in adj[v]:
        if not visited[e]:
            dfs(e)

n, m = map(int, input().split())
count = 0
adj = [[] for _ in range(n+1)]
visited = [False] * (n+1)

# 인접 리스트 채우기
for _ in range(m):
    u, v = map(int, input().split())
    adj[u].append(v)
    adj[v].append(u)

for i in range(1, n+1):
    if not visited[i]:
        dfs(i)
        count += 1

print(count)