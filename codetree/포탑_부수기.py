"""
[구현 순서]
1. 입력 받기: towers[][](ad), recent_attack_time[][] recent_attacked_time[][]
2. 공격자 선정
    - 기준을 지켜 포탑 선정 -> 순회 기준 행과 열의 합을 신경쓰면서 열이 큰 것부터 순회하면 될 듯
3. 공격자의 공격
    - 공격 대상 선정
        - 마찬가지로 기준을 잘 지키기 -> 순회 기준 행과 열의 합을 신경쓰고, 열이 작은 것 신경쓰기
    (1) 레이저 공격
    (2) 포탄 공격
4. 포탑 부서짐
    - 공격을 받는 시점에 추가 (공격력이 0 이하가 된 포탑은 0으로 처리)
5. 포탑 정비
    - 공격과 무관하고 부서지지 않은 포탑은 공격력 1씩 증가


[REMIND]
- 최초에 공격력이 0인 포탑(부서진 포탑)이 있을 수 있다.
- 범위를 벗어난다면 반대쪽으로 가도록 하는 것 잘 신경써주기...
- 만약 부서지지 않은 포탑이 1개가 된다면 그 즉시 중지한다!
"""

import sys
sys.stdin = open('input.txt', 'r')

from collections import deque

N, M, K = map(int, input().split())
towers = [list(map(int, input().split())) for _ in range(N)]
recent_attack_time = [[0] * M for _ in range(N)]
recent_attacked_time = [[0] * M for _ in range(N)]
four_dir = [(0, 1), (1, 0), (0, -1), (-1, 0)] # 동 남 서 북
eight_dir = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)] # 북 북동 동 남동 남 남서 서 북서

def select_attacker():
    candidates = []
    min_ad = 5001
    for i in range(N):
        for j in range(M):
            if towers[i][j] <= 0:
                continue

            if min_ad == towers[i][j]:
                candidates.append([recent_attack_time[i][j], i, j])
            elif min_ad > towers[i][j]:
                candidates.clear()
                min_ad = towers[i][j]
                candidates.append([recent_attack_time[i][j], i, j])

    sorted_candidates = sorted(candidates, key=lambda x: (-x[0], -(x[1] + x[2]), -x[2]))
    x, y = sorted_candidates[0][1], sorted_candidates[0][2]
    # 공격자 공격력 증가
    towers[x][y] += (N + M)
    recent_attack_time[x][y] = testcase

    return x, y

def attack():
    # 공격 대상 선정
    candidates = []
    max_ad = -1
    for i in range(N):
        for j in range(M):
            if towers[i][j] <= 0:
                continue

            # 공격자 예외처리
            if recent_attack_time[i][j] == testcase:
                continue

            if max_ad == towers[i][j]:
                candidates.append([recent_attack_time[i][j], i, j])
            elif max_ad < towers[i][j]:
                candidates.clear()
                max_ad = towers[i][j]
                candidates.append([recent_attack_time[i][j], i, j])
    sorted_candidate = sorted(candidates, key=lambda x: (x[0], (x[1] + x[2]), x[2]))
    x, y = sorted_candidate[0][1], sorted_candidate[0][2]
    target = (x, y)

    # (1) 레이저 공격
    if not laser(target):
        # (2) 포탄 공격
        bombshell(target)


def laser(target):
    ax, ay = attacker
    tx, ty = target
    queue = deque()
    queue.append((ax, ay))

    # step_map 작성
    reverse_queue = deque()
    reverse_queue.append((tx, ty))

    visited = [[False] * M for _ in range(N)]
    step_map = [[0] * M for _ in range(N)]

    while reverse_queue:
        reverse_now_x, reverse_now_y = reverse_queue.popleft()
        visited[reverse_now_x][reverse_now_y] = True

        for dx, dy in four_dir:
            reverse_next_x, reverse_next_y = reverse_now_x + dx, reverse_now_y + dy

            if reverse_next_x == -1:
                reverse_next_x = N - 1
            if reverse_next_x == N:
                reverse_next_x = 0

            if reverse_next_y == -1:
                reverse_next_y = M - 1
            if reverse_next_y == M:
                reverse_next_y = 0

            if towers[reverse_next_x][reverse_next_y] == 0 or visited[reverse_next_x][reverse_next_y]:
                continue

            # 이동
            visited[reverse_next_x][reverse_next_y] = True
            reverse_queue.append((reverse_next_x, reverse_next_y))
            step_map[reverse_next_x][reverse_next_y] = step_map[reverse_now_x][reverse_now_y] + 1

    # attacker의 step_map == 0 -> 경로 X -> 포탄 공격으로 전환
    if step_map[ax][ay] == 0:
        return False

    # 이동했던 경로 저장
    routes = []
    next_step = step_map[ax][ay]
    while queue:
        now_x, now_y = queue.popleft()
        next_step -= 1
        # 다음 도착 지점이 target이면 종료
        if next_step == 0:
            break

        for dx, dy in four_dir:
            next_x, next_y = now_x + dx, now_y + dy

            # 지도 반대편으로 이동
            if next_x == -1:
                next_x = N - 1
            if next_x == N:
                next_x = 0

            if next_y == -1:
                next_y = M - 1
            if next_y == M:
                next_y = 0

            if towers[next_x][next_y] == 0:
                continue

            # next_x, next_y에 대해 target으로부터 bfs 탐색
            if step_map[next_x][next_y] == next_step:
                queue.append((next_x, next_y))
                routes.append((next_x, next_y))
                break

    # 피해 입히기
    towers[tx][ty] -= towers[ax][ay]
    recent_attacked_time[tx][ty] = testcase
    if towers[tx][ty] < 0:
        towers[tx][ty] = 0

    for route in routes:
        rx, ry = route
        towers[rx][ry] -= towers[ax][ay] // 2
        recent_attacked_time[rx][ry] = testcase
        if towers[rx][ry] < 0:
            towers[rx][ry] = 0

    return True

def bombshell(target):
    # 공격 대상에 포탄을 던진다.
    ax, ay = attacker
    tx, ty = target
    towers[tx][ty] -= towers[ax][ay]
    recent_attacked_time[tx][ty] = testcase
    if towers[tx][ty] < 0:
        towers[tx][ty] = 0

    for dx, dy in eight_dir:
        next_x, next_y = tx + dx, ty + dy

        if next_x == -1:
            next_x = N - 1
        if next_x == N:
            next_x = 0

        if next_y == -1:
            next_y = M - 1
        if next_y == M:
            next_y = 0

        if towers[next_x][next_y] == 0:
            continue

        # 공격자면 예외 처리
        if next_x == ax and next_y == ay:
            continue

        towers[next_x][next_y] -= towers[ax][ay] // 2
        recent_attacked_time[next_x][next_y] = testcase
        if towers[next_x][next_y] < 0:
            towers[next_x][next_y] = 0

def maintain():
    for i in range(N):
        for j in range(M):
            if towers[i][j] != 0 and recent_attacked_time[i][j] < testcase and recent_attack_time[i][j] < testcase:
                towers[i][j] += 1

def is_done():
    count = 0
    for i in range(N):
        for j in range(M):
            if towers[i][j] > 0:
                count += 1

    if count <= 1:
        return True

    return False

def get_answer():
    answer = 0
    for i in range(N):
        for j in range(M):
            if answer < towers[i][j]:
                answer = towers[i][j]

    return answer

for testcase in range(1, K + 1):

    ## 2. 공격자 선정
    attacker = select_attacker()
    # print('attacker:', attacker)

    ## 3. 공격자의 공격
    attack()

    if is_done():
        break

    ## 5. 포탑 정비
    maintain()

print(get_answer())

