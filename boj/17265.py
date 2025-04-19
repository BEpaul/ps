def is_in_range(x, y):
    return 0 <= x < N and 0 <= y < N

def calculate(a, b, o):
    if o == '+':
        return a + b
    if o == '-':
        return a - b
    if o == '*':
        return a * b

def dfs(x, y, operator):
    if x == N - 1 and y == N - 1:
        return

    for dx, dy in delta:
        next_x, next_y = x + dx, y + dy
        
        if not is_in_range(next_x, next_y):
            continue

        # 연산자인 경우
        if not grid[next_x][next_y].isdigit():
            max_dp[next_x][next_y] = max_dp[x][y]
            min_dp[next_x][next_y] = min_dp[x][y]
            dfs(next_x, next_y, grid[next_x][next_y])
            
        # 숫자인 경우
        else:
            max_dp[next_x][next_y] = max(max_dp[next_x][next_y], 
                                         calculate(max_dp[x][y], int(grid[next_x][next_y]), operator))
            min_dp[next_x][next_y] = min(min_dp[next_x][next_y], 
                                         calculate(min_dp[x][y], int(grid[next_x][next_y]), operator))
            dfs(next_x, next_y, None)

N = int(input())
grid = [input().split() for _ in range(N)]
delta = [(0, 1), (1, 0)] # 동 남
max_dp = [[-1e9] * N for _ in range(N)]
min_dp = [[1e9] * N for _ in range(N)]
max_dp[0][0], min_dp[0][0] = int(grid[0][0]), int(grid[0][0])

dfs(0, 0, None)

print(max_dp[N - 1][N - 1], min_dp[N - 1][N - 1])
