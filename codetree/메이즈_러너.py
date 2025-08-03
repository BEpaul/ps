import sys

"""
[풀이 순서]
1. 입력 받기 (미로 map / 참가자들은 위치, 이동거리를 가지고 있음 / 출구는 위치
2. 이동 로직 구현
    - 각 4방향에 벽이 없는지 확인
    - 4방향에 대해 최선의 위치를 확인, 이때 상하 움직임을 우선시
    - 움직일 수 없다면, 움직이지 않는다.
    - 만약 최단거리면 이동하고, 아니라면 이동하지 않는다.
3. 미로 회전 로직 구현
    - 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형 잡기
    - 정사각형 시계방향 90도 회전
    - 정사각형 내 내구도 1씩 깎기
4. 위의 로직들 반복
    - K초 넘는지 확인
    - 참가자가 모두 도착했는지 확인
5. 종료 조건 만족 시 출력
    - 참가자들을 순회하며 이동거리 계산
    - 출구 위치

[Memo]
- 참가자가 출구에 도착하면, (r c) = (-1, -1)로 변경


[Remind]
- 정사각형이 2개 이상이면, r좌표 작은 것 > c좌표 작은 것 우선
- 한 칸에 2명 이상의 참가자가 있을 수 있다
- 참가자들도 회전 시 함께 돌려줘야 한다!
-
"""

sys.stdin = open('input.txt', 'r')

n, m, k = map(int, input().split())
maze = [[0] * (n + 1)] + [[0] + list(map(int, input().split())) for _ in range(n)]
new_maze = [[0] * (n + 1) for _ in range(n + 1)]

runners = []
for _ in range(m):
    runners.append(list(map(int, input().split())))

exit = list(map(int, input().split()))

answer = 0
sx, sy, square_size = 0, 0, 0

def move():
    global answer, exit
    for runner in runners:
        if runner == exit:
            continue

        rx, ry = runner
        ex, ey = exit

        # 행이 다른 경우
        if rx != ex:
            nx, ny = rx, ry

            if ex > nx:
                nx += 1
            else:
                nx -= 1

            if not maze[nx][ny]:
                runner[0], runner[1] = nx, ny
                answer += 1
                continue

        # 열이 다른 경우
        if ry != ey:
            nx, ny = rx, ry

            if ey > ny:
                ny += 1
            else:
                ny -= 1

            if not maze[nx][ny]:
                answer += 1
                runner[0], runner[1] = nx, ny
                continue

def find_minimum_square():
    global exit, sx, sy, square_size
    ex, ey = exit

    for sz in range(2, n + 1):
        for x1 in range(1, n - sz + 2):
            for y1 in range(1, n - sz + 2):
                x2, y2 = x1 + sz - 1, y1 + sz - 1

                if not (x1 <= ex <= x2 and y1 <= ey <= y2):
                    continue

                is_runner_in = False
                for runner in runners:
                    rx, ry = runner
                    if x1 <= rx <= x2 and y1 <= ry <= y2:
                        if not (rx == ex and ry == ey):
                            is_runner_in = True

                if is_runner_in:
                    sx, sy = x1, y1
                    square_size = sz

                    return

def partial_rotate_square():
    # 정사각형 안 내구성 1씩 감소
    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            if maze[x][y]:
                maze[x][y] -= 1

    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            # Step 1. (sx, sy)를 (0, 0)으로 옮기는 변환
            ox, oy = x - sx, y - sy
            # Step 2. 변환된 상태에서 회전 이후 좌표가 (x, y) -> (y, square_size - x - 1)
            rx, ry = oy, square_size - ox - 1
            # Step 3. 다시 (sx, sy)를 더해준다.
            new_maze[rx + sx][ry + sy] = maze[x][y]

    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            maze[x][y] = new_maze[x][y]

def rotate_runner_and_exit():
    global exit

    for runner in runners:
        rx, ry = runner
        if sx <= rx < sx + square_size and sy <= ry < sy + square_size:
            ox, oy = rx - sx, ry - sy
            rx, ry = oy, square_size - ox - 1
            runner[0], runner[1] = rx + sx, ry + sy

    ex, ey = exit
    if sx <= ex < sx + square_size and sy <= ey < sy + square_size:
        ox, oy = ex - sx, ey - sy
        rx, ry = oy, square_size - ox - 1
        exit[0], exit[1] = rx + sx, ry + sy

for _ in range(k):
    move()

    is_done = True
    for runner in runners:
        if runner != exit:
            is_done = False

    if is_done:
        break

    find_minimum_square()

    partial_rotate_square()

    rotate_runner_and_exit()

print(answer)
print(exit[0], exit[1])
