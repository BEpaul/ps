import sys
from collections import deque

def solve():
    answer = 0

    for h in range(1, 101):
        res = 0
        delta = ((-1, 0), (0, 1), (1, 0), (0, -1))
        visited = [[False] * n for _ in range(n)]

        def bfs(x, y):
            queue = deque()
            queue.append((x, y))
            visited[x][y] = True

            while queue:
                now_x, now_y = queue.popleft()

                for dx, dy in delta:
                    next_x = now_x + dx
                    next_y = now_y + dy
                    if in_range(next_x, next_y) and not visited[next_x][next_y] and map[next_x][next_y] >= h:
                        visited[next_x][next_y] = True
                        queue.append((next_x, next_y))

        for i in range(n):
            for j in range(n):
                if not visited[i][j] and map[i][j] >= h:
                    bfs(i, j)
                    res += 1

        answer = max(answer, res)

    return answer

def in_range(x, y):
    return x >= 0 and x < n and y >= 0 and y < n

n = int(sys.stdin.readline())
map = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

print(solve())