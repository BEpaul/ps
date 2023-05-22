n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]

for i in range(m):
    x, y = map(int, input().split())

    graph[x].append(y)
    graph[y].append(x)

visited = [0] * (n+1)
count = -1

def dfs(cur):
    visited[cur] = 1
    global count
    count += 1
    for i in graph[cur]:
        if not visited[i]:
            dfs(i)

dfs(1)
print(count)