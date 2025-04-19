from collections import deque

delta = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]

def bfs(x, y):
    queue = deque()
    queue.append((x, y))
    visited[x][y] = True

    while queue:
        now_x, now_y = queue.popleft()
        for dx, dy in delta:
            next_x, next_y = now_x + dx, now_y + dy
            if is_in_range(next_x, next_y) and graph[next_x][next_y] == 1 and not visited[next_x][next_y]:
                visited[next_x][next_y] = True
                queue.append((next_x, next_y))
            
def is_in_range(x, y):
    return 0 <= x < h and 0 <= y < w

while True:
    w, h = map(int, input().split())
    answer = 0
    if w == 0 and h == 0:
        break

    graph = [list(map(int, input().split())) for _ in range(h)]
    visited = [[False] * w for _ in range(h)]

    for i in range(h):
        for j in range(w):
            if graph[i][j] == 1 and not visited[i][j]:
                bfs(i, j)
                answer += 1

    print(answer)