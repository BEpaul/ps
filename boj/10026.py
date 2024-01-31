from collections import deque
import copy

def red_green_blindness(n, graph):
    result = 0

    visited = [[False] * n for _ in range(n)]
    delta = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def bfs(x, y):
        visited[x][y] = True
        queue = deque()
        queue.append((x, y))

        while queue:
            cur_x, cur_y = queue.popleft()
            cur_color = graph[cur_x][cur_y]
            for dx, dy in delta:
                next_x = cur_x + dx
                next_y = cur_y + dy

                if next_x >= 0 and next_x < n and next_y >= 0 and next_y < n:
                    if graph[next_x][next_y] == cur_color and not visited[next_x][next_y]:
                        visited[next_x][next_y] = True
                        queue.append((next_x, next_y))
    
    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                bfs(i, j)
                result += 1

    return result

n = int(input())

normal_graph = [list(input()) for _ in range(n)]
blind_graph = copy.deepcopy(normal_graph)

for i in range(n):
    for j in range(n):
        if blind_graph[i][j] == "G":
            blind_graph[i][j] = "R"

normal_answer = red_green_blindness(n, normal_graph)
blind_answer = red_green_blindness(n, blind_graph)

print(normal_answer, blind_answer)