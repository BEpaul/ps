import sys
sys.setrecursionlimit(10**6)

def is_in_range(x, y):
    return 0 <= x < M and 0 <= y < N

def dfs(x, y):
    visited[x][y] = True
    for dx, dy in delta:
        next_x, next_y = x + dx, y + dy
        if is_in_range(next_x, next_y) and not visited[next_x][next_y] and hyunsoomak[next_x][next_y] == 1:
            dfs(next_x, next_y)

M, N = map(int, input().split())
hyunsoomak = [list(map(int, input().split())) for _ in range(M)]
delta = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)] # 북 북동 동 남동 남 남서 서 북서

visited = [[False] * N for _ in range(M)]
answer = 0

for i in range(M):
    for j in range(N):
        if not visited[i][j] and hyunsoomak[i][j] == 1:
            dfs(i, j)
            answer += 1

print(answer)