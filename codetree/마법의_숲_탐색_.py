"""
[구현 순서]
1. 입력 받기: R, C, K | forest_map[][], spirits[]
2. 골렘의 탐색
    -


[Remind]
- 골렘이 회전할 때 출구 또한 반시계/시계 방향으로 바뀌는 것 주의!
- 골렘이 숲을 탐색할 때, (1), (2), (3)의 방법을 더 이상 움직이지 못할 때까지 반복한다.
- 골렘이 숲이 들어가지 못할 때 잘 고려하기

"""

import sys
sys.stdin = open('input.txt', 'r')

from collections import deque

R, C, K = map(int, input().split())
forest_map = [[0] * (C + 1) for _ in range(R + 1)]

spirits = [(-2, -2)]
spirit_dir = [-1]
input_sprit = [tuple(map(int, input().split())) for _ in range(K)]
for ins in input_sprit:
    spirits.append((-1, ins[0]))
    spirit_dir.append(ins[1])

answer = 0
delta = [(-1, 0), (0, 1), (1, 0), (0, -1)] # 북 동 남 서
exit_map = [[False] * (C + 1) for _ in range(R + 1)]

def search_forest():
    while True:
        sx, sy = spirits[spirit_num]

        # 바닥에 이미 닿았다면 종료
        if sx == R - 1:
            break

        # (1) 남쪽으로 한 칸 내려가기
        can_move = move_south(sx, sy)

        # (2) 서쪽 방향으로 회전하며 내려가기
        if not can_move:
            can_move = move_west(sx, sy)

        # (3) 동쪽 방향으로 회전하며 내려가기
        if not can_move:
            can_move = move_east(sx, sy)

        if not can_move:
            break

    # 골렘이 숲에 진입하지 못한 채로 끝났는지 체크
    if spirits[spirit_num][0] <= 1:
        return True

    # 최종 도착 시점에서 exit_map에 출구 정보 추가
    sx, sy = spirits[spirit_num]
    exit = (sx + delta[spirit_dir[spirit_num]][0], sy + delta[spirit_dir[spirit_num]][1])
    exit_map[exit[0]][exit[1]] = True

    # 탐색하여 정령이 다른 골렘으로 이동할 수 있는지 확인하고, 가능한 최대의 점수를 획득할 수 있는 큰 열에 도달하기
    max_col = sx + 1

    visited = [[False] * (C + 1) for _ in range(R + 1)]
    queue = deque()
    queue.append((exit[0], exit[1])) # exit 좌표 추가
    visited[sx][sy] = True

    while queue:
        now_x, now_y = queue.popleft()
        visited[now_x][now_y] = True
        if now_x > max_col:
            max_col = now_x

        for dx, dy in delta:
            next_x, next_y = now_x + dx, now_y + dy

            if not in_range(next_x, next_y):
                continue

            # 같은 골렘 내에서 이동
            if forest_map[next_x][next_y] == forest_map[now_x][now_y]:
                if not visited[next_x][next_y]:
                    visited[next_x][next_y] = True
                    queue.append((next_x, next_y))

            # 다른 골렘으로 이동
            if exit_map[now_x][now_y]:
                if forest_map[next_x][next_y] > 0 and forest_map[next_x][next_y] != forest_map[now_x][now_y]:
                    if not visited[next_x][next_y]:
                        visited[next_x][next_y] = True
                        queue.append((next_x, next_y))

    global answer
    answer += max_col

def move_south(sx, sy):
    if forest_map[sx + 2][sy] == 0 and forest_map[sx + 1][sy - 1] == 0 and forest_map[sx + 1][sy + 1] == 0:
        # 이동 - spirit(r,c) 반영
        next_sx, next_sy = sx + 1, sy
        spirits[spirit_num] = (next_sx, next_sy)

        # forest_map 반영
        modify_forest_map(next_sx, next_sy, sx, sy)

        return True

def move_west(sx, sy):
    if sy == 2:
        return False
    if sx != -1 and forest_map[sx][sy - 2] != 0:
        return False

    if sx > 0 and forest_map[sx - 1][sy - 1] != 0:
        return False

    if forest_map[sx + 1][sy - 1] == 0:
        if forest_map[sx + 1][sy - 2] == 0 and forest_map[sx + 2][sy - 1] == 0:
            # 이동
            next_sx, next_sy = sx + 1, sy - 1
            spirits[spirit_num] = (next_sx, next_sy)

            # 출구 방향 돌리기
            spirit_dir[spirit_num] -= 1
            if spirit_dir[spirit_num] == -1:
                spirit_dir[spirit_num] = 3

            # forest_map 반영
            modify_forest_map(next_sx, next_sy, sx, sy)

            return True

def move_east(sx, sy):
    if sy == C - 1:
        return False

    if sx != -1 and forest_map[sx][sy + 2] != 0:
        return False

    if sx > 0 and forest_map[sx - 1][sy + 1] != 0:
        return False

    if forest_map[sx + 1][sy + 1] == 0:
        if forest_map[sx + 1][sy + 2] == 0 and forest_map[sx + 2][sy + 1] == 0:
            # 이동
            next_sx, next_sy = sx + 1, sy + 1
            spirits[spirit_num] = (next_sx, next_sy)

            # 출구 방향 돌리기
            spirit_dir[spirit_num] += 1
            if spirit_dir[spirit_num] == 4:
                spirit_dir[spirit_num] = 0

            # forest_map 반영
            modify_forest_map(next_sx, next_sy, sx, sy)

            return True

def modify_forest_map(next_sx, next_sy, sx, sy):
    # 기존 삭제
    if sx != -1:
        forest_map[sx][sy] = 0
        for dx, dy in delta:
            near_sx, near_sy = sx + dx, sy + dy
            if in_range(near_sx, near_sy):
                forest_map[near_sx][near_sy] = 0
    # 추가
    forest_map[next_sx][next_sy] = spirit_num
    for dx, dy in delta:
        near_next_sx, near_next_sy = next_sx + dx, next_sy + dy
        if in_range(near_next_sx, near_next_sy):
            forest_map[near_next_sx][near_next_sy] = spirit_num

def init_map():
    for i in range(R + 1):
        for j in range(C + 1):
            forest_map[i][j] = 0
            exit_map[i][j] = False

def in_range(x, y):
    return 0 <= x < R + 1 and 1 <= y < C + 1

for spirit_num in range(1, K + 1):

    # 골렘의 탐색
    is_full = search_forest()
    if is_full:
        init_map()
        continue

print(answer)
