N, L = map(int, input().split())
roads = [list(map(int, input().split())) for _ in range(N)]
answer = 0

def is_in_range(x):
    return 0 <= x < N

## 각 행 좌 -> 우 탐색
used_roads = [[False] * N for _ in range(N)]
for i in range(N):
    for j in range(N):
        escape_flag = False
        # 끝 지점 도달 시 도착 인정 (answer++)
        if j == N - 1:
            answer += 1
            break

        diff = roads[i][j + 1] - roads[i][j]

        # 다음 칸과 차이가 2 이상인 경우
        if abs(diff) > 1:
            break

        # 다음 칸의 숫자가 1 큰 경우
        if diff == 1:
            for k in range(j - L + 1, j + 1):
                if not is_in_range(k) or used_roads[i][k]:
                    escape_flag = True
                    break

                used_roads[i][k] = True

        # 다음 칸의 숫자가 1 작은 경우
        if diff == -1:
            for k in range(j + 1, j + L + 1):
                if not is_in_range(k) or used_roads[i][k]:
                    escape_flag = True
                    break

                used_roads[i][k] = True

        if escape_flag:
            break


used_roads = [[False] * N for _ in range(N)]
# 뒤집기
new_roads = [[0] * N for _ in range(N)]
for i in range(N):
    for j in range(N):
        new_roads[i][j] = roads[j][i]

for i in range(N):
    for j in range(N):
        escape_flag = False
        # 끝 지점 도달 시 도착 인정 (answer++)
        if j == N - 1:
            answer += 1
            break

        diff = new_roads[i][j + 1] - new_roads[i][j]

        # 다음 칸과 차이가 2 이상인 경우
        if abs(diff) > 1:
            break

        # 다음 칸의 숫자가 1 큰 경우
        if diff == 1:
            for k in range(j - L + 1, j + 1):
                if not is_in_range(k) or used_roads[i][k]:
                    escape_flag = True
                    break

                used_roads[i][k] = True

        # 다음 칸의 숫자가 1 작은 경우
        if diff == -1:
            for k in range(j + 1, j + L + 1):
                if not is_in_range(k) or used_roads[i][k]:
                    escape_flag = True
                    break

                used_roads[i][k] = True

        if escape_flag:
            break

print(answer)