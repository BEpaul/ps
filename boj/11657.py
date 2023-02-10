INF = int(1e9)

def bellman_ford(start):
    dist[start] = 0

    for i in range(1, N+1):
        for j in range(M):
            now, next, cost = edges[j][0], edges[j][1], edges[j][2]
            if dist[now] != INF and dist[next] > dist[now] + cost:
                dist[next] = dist[now] + cost
                if i == N:
                    return 0
    return 1

N, M = map(int, input().split())
edges = []
dist = [INF] * (N+1)

for _ in range(M):
    A, B, C = map(int, input().split())
    edges.append((A, B, C))

res = bellman_ford(1)
if not res:
    print(-1)
else:
    for j in range(2, N+1):
        if dist[j] == INF:
            print(-1)
        else:
            print(dist[j])