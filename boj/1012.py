dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y):
    queue = [(x, y)]
    matrix[x][y] = 0

    while queue:
        x, y = queue.pop(0)

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if matrix[nx][ny] == 1:
                queue.append((nx, ny))
                matrix[nx][ny] = 0

t = int(input())

for _ in range(t):
    m, n, k = map(int, input().split())
    count = 0
    matrix = [[0] * m for _ in range(n)]

    for _ in range(k):
        x, y = map(int, input().split())
        matrix[y][x] = 1

    for i in range(n):
        for j in range(m):
            if matrix[i][j] == 1:
                bfs(i, j)
                count += 1

    print(count)