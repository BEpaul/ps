from collections import deque

def createGraph():
    n = int(input())
    graph = []
    for _ in range(n):
        graph.append(list(input()))

    return n, graph

def get_count_of_houses(n, graph):
    house_counts = []
    visited = [[False] * n for _ in range(n)]

    def bfs(x, y):
        count_of_houses = 1
        visited[x][y] = True
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]
        queue = deque()
        queue.append((x, y))

        while queue:
            cur_x, cur_y = queue.popleft()
            for i in range(4):
                next_x = cur_x + dx[i]
                next_y = cur_y + dy[i]
                if next_x >= 0 and next_x < n and next_y >= 0 and next_y < n:
                    if graph[next_x][next_y] == "1" and not visited[next_x][next_y]:
                        count_of_houses += 1
                        visited[next_x][next_y] = True
                        queue.append((next_x, next_y))

        house_counts.append(count_of_houses)

    for i in range(n):
        for j in range(n):
            if graph[i][j] == "1" and not visited[i][j]:
                bfs(i, j)

    return house_counts

n, graph = createGraph()
answer = get_count_of_houses(n, graph)
answer.sort()

print(len(answer))
for ans in answer:
    print(ans)
