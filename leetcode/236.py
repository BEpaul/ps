class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        shortest_path_len = -1
        n = len(grid)

        if grid[0][0] == 1 or grid[n-1][n-1] == 1:
            return shortest_path_len

        queue = deque()
        queue.append((0, 0, 1))
        visited = [[False] * n for _ in range(n)]
        visited[0][0] = True
        delta = [(1, 0), (-1, 0), (0, 1), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1)]
        
        while queue:
            cur_x, cur_y, cur_len = queue.popleft()
            if cur_x == n - 1 and cur_y == n - 1:
                shortest_path_len = cur_len
                break

            for dx, dy in delta:
                next_x = cur_x + dx
                next_y = cur_y + dy
                if next_x >= 0 and next_x < n and next_y >= 0 and next_y < n:
                    if grid[next_x][next_y] == 0 and not visited[next_x][next_y]:
                        queue.append((next_x, next_y, cur_len + 1))
                        visited[next_x][next_y] = True

        return shortest_path_len