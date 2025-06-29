"""
[구현 순서]
1. 입력받기: 팩맨(x, y), 몬스터[](x, y, d), 몬스터 알[](x, y, d), 몬스터 시체[](x, y, t), 격자[][](몬스터 수)
2. 몬스터 복제 시도
    (1) 몬스터들 현 위치에 복제(알 낳기)
3. 몬스터 이동
    (2) 몬스터 이동 구현
        - 몬스터 시체가 있는 경우, 팩맨이 있는 경우, 격자 벗어나는 경우 반시계 방향 45도 회전
        - 갈 수 있는 지 판단 후 계속해서 회전 및 판단
        - 8방향 모두 움직일 수 없다면 움직이지 않음
4. 팩맨 이동
    (1) 64개의 이동 방법 중 우선순위 나열
    (2) 모든 경우에 대해 몬스터를 가장 많이 먹을 수 있는 순서로 이동
        - 격자를 나가는 경우 제외
    (3) 몬스터를 먹고 시체 남기기 (소멸 턴 명시)
5. 몬스터 시체 소멸
    (1) 소멸 턴이 된 몬스터 시체 소멸하기
6. 몬스터 복제 완성
    (1) 알 형태였던 몬스터 부화 (방향을 잘 지닌 채로 깨어나는지 확인)


[REMIND]
- 팩맨 이동 시, 알은 먹지 않으며 움직이지 전에 함께 있었던 몬스터도 먹지 않는다. 즉, 이동하는 과정에 있는 몬스터만 먹는다.
- delta는 1~8!
"""
from collections import deque

def renew_monster_count_map():
    count_map = [[0] * 5 for _ in range(5)]
    for monster in monsters:
        count_map[monster[0]][monster[1]] += 1
    return count_map

def change_monster_dir(d):
    return 1 if d == 8 else d + 1

def is_in_range(x, y):
    return 1 <= x <= 4 and 1 <= y <= 4

def is_dead_monster(x, y):
    return dead_monsters_map[x][y]

def is_packman(x, y):
    return x == packman[0] and y == packman[1]

def eat_monsters(x, y, remove_turn):
    length = len(monsters)
    for i in range(length):
        monster = monsters.popleft()
        if x == monster[0] and y == monster[1]:
            dead_monsters.append((monster[0], monster[1], remove_turn))
        else:
            monsters.append(monster)

def remove_dead_monsters(cur_turn):
    length = len(dead_monsters)
    for _ in range(length):
        dm = dead_monsters.popleft()
        dmx, dmy, dmt = dm
        if dmt != cur_turn:
            dead_monsters.append(dm)

def born_eggs():
    while eggs:
        monsters.append(eggs.popleft())


# 1. 입력받기: 팩맨(x, y), 몬스터[](x, y, d), 몬스터 알[](x, y, d), 몬스터 시체[](x, y, t), 격자[][](몬스터 수)
m, t = map(int, input().split())
packman = tuple(map(int, input().split()))
monsters = deque(list(map(int, input().split())) for _ in range(m))
eggs = deque()
dead_monsters = deque()
monster_count_map = renew_monster_count_map()
answer = 0

delta = [0, (-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1)] # 북 북서 서 남서 남 남동 동 북서
delta_four = [0, (-1, 0), (0, -1), (1, 0), (0, 1)] # 상 좌 하 우
packman_movements = [(delta_four[1], delta_four[1], delta_four[1]), (delta_four[1], delta_four[1], delta_four[2]),
                 (delta_four[1], delta_four[1], delta_four[3]), (delta_four[1], delta_four[1], delta_four[4]),
                 (delta_four[1], delta_four[2], delta_four[1]), (delta_four[1], delta_four[2], delta_four[2]),
                 (delta_four[1], delta_four[2], delta_four[3]), (delta_four[1], delta_four[2], delta_four[4]),
                 (delta_four[1], delta_four[3], delta_four[1]), (delta_four[1], delta_four[3], delta_four[2]),
                 (delta_four[1], delta_four[3], delta_four[3]), (delta_four[1], delta_four[3], delta_four[4]),
                 (delta_four[1], delta_four[4], delta_four[1]), (delta_four[1], delta_four[4], delta_four[2]),
                 (delta_four[1], delta_four[4], delta_four[3]), (delta_four[1], delta_four[4], delta_four[4]),

                 (delta_four[2], delta_four[1], delta_four[1]), (delta_four[2], delta_four[1], delta_four[2]),
                 (delta_four[2], delta_four[1], delta_four[3]), (delta_four[2], delta_four[1], delta_four[4]),
                 (delta_four[2], delta_four[2], delta_four[1]), (delta_four[2], delta_four[2], delta_four[2]),
                 (delta_four[2], delta_four[2], delta_four[3]), (delta_four[2], delta_four[2], delta_four[4]),
                 (delta_four[2], delta_four[3], delta_four[1]), (delta_four[2], delta_four[3], delta_four[2]),
                 (delta_four[2], delta_four[3], delta_four[3]), (delta_four[2], delta_four[3], delta_four[4]),
                 (delta_four[2], delta_four[4], delta_four[1]), (delta_four[2], delta_four[4], delta_four[2]),
                 (delta_four[2], delta_four[4], delta_four[3]), (delta_four[2], delta_four[4], delta_four[4]),

                 (delta_four[3], delta_four[1], delta_four[1]), (delta_four[3], delta_four[1], delta_four[2]),
                 (delta_four[3], delta_four[1], delta_four[3]), (delta_four[3], delta_four[1], delta_four[4]),
                 (delta_four[3], delta_four[2], delta_four[1]), (delta_four[3], delta_four[2], delta_four[2]),
                 (delta_four[3], delta_four[2], delta_four[3]), (delta_four[3], delta_four[2], delta_four[4]),
                 (delta_four[3], delta_four[3], delta_four[1]), (delta_four[3], delta_four[3], delta_four[2]),
                 (delta_four[3], delta_four[3], delta_four[3]), (delta_four[3], delta_four[3], delta_four[4]),
                 (delta_four[3], delta_four[4], delta_four[1]), (delta_four[3], delta_four[4], delta_four[2]),
                 (delta_four[3], delta_four[4], delta_four[3]), (delta_four[3], delta_four[4], delta_four[4]),

                 (delta_four[4], delta_four[1], delta_four[1]), (delta_four[4], delta_four[1], delta_four[2]),
                 (delta_four[4], delta_four[1], delta_four[3]), (delta_four[4], delta_four[1], delta_four[4]),
                 (delta_four[4], delta_four[2], delta_four[1]), (delta_four[4], delta_four[2], delta_four[2]),
                 (delta_four[4], delta_four[2], delta_four[3]), (delta_four[4], delta_four[2], delta_four[4]),
                 (delta_four[4], delta_four[3], delta_four[1]), (delta_four[4], delta_four[3], delta_four[2]),
                 (delta_four[4], delta_four[3], delta_four[3]), (delta_four[4], delta_four[3], delta_four[4]),
                 (delta_four[4], delta_four[4], delta_four[1]), (delta_four[4], delta_four[4], delta_four[2]),
                 (delta_four[4], delta_four[4], delta_four[3]), (delta_four[4], delta_four[4], delta_four[4]),
                 ]

for turn in range(1, t + 1):
    # 2. 몬스터 복제 시도
    # (1) 몬스터들 현 위치에 복제(알 낳기)
    for monster in monsters:
        egg = [monster[0], monster[1], monster[2]]
        eggs.append(egg)

    # dead_monsters_map 갱신
    dead_monsters_map = [[False] * 5 for _ in range(5)]
    for dead_monster in dead_monsters:
        dead_monsters_map[dead_monster[0]][dead_monster[1]] = True

    # 3. 몬스터 이동
    # (2) 몬스터 이동 구현
    for monster in monsters:
        mx, my, md = monster
        for _ in range(8): # - 갈 수 있는 지 판단 후 계속해서 회전 및 판단
            dx, dy = delta[md]
            next_mx, next_my = mx + dx, my + dy
            # - 몬스터 시체가 있는 경우, 팩맨이 있는 경우, 격자 벗어나는 경우 반시계 방향 45도 회전
            # - 8방향 모두 움직일 수 없다면 움직이지 않음
            if not is_in_range(next_mx, next_my) or is_dead_monster(next_mx, next_my) or is_packman(next_mx, next_my):
                md = change_monster_dir(md)
                continue
            # - 갈 수 있다면 간다.
            monster[0], monster[1], monster[2] = next_mx, next_my, md
            break

    monster_count_map = renew_monster_count_map()
    # 4. 팩맨 이동
    # (1) 64개의 이동 방법 중 우선순위 나열
    max_count = -1
    move_res = ()
    move_history = ()
    for packman_movement in packman_movements:
        px, py = packman
        count = 0
        escape_flag = False
        visit_flag = [[False] * 5 for _ in range(5)]
        for move in packman_movement:
            next_px, next_py = px + move[0], py + move[1]
            if not is_in_range(next_px, next_py): # - 격자를 나가는 경우 제외
                escape_flag = True
                break

            if not visit_flag[next_px][next_py]:
                count += monster_count_map[next_px][next_py]
            px, py = next_px, next_py
            visit_flag[next_px][next_py] = True

        if escape_flag:
            continue
        # (2) 모든 경우에 대해 몬스터를 가장 많이 먹을 수 있는 순서로 이동
        if count > max_count:
            max_count = count
            move_history = packman_movement
            move_res = (px, py)
    # (3) 몬스터를 먹고 시체 남기기 (소멸 턴 명시)
    px, py = packman
    for mh in move_history:
        dx, dy = mh
        px, py = px + dx, py + dy
        eat_monsters(px, py, turn + 2)

    packman = move_res
    # 5. 몬스터 시체 소멸
    # (1) 소멸 턴이 된 몬스터 시체 소멸하기
    remove_dead_monsters(turn)

    # 6. 몬스터 복제 완성
    # (1) 알 형태였던 몬스터 부화 (방향을 잘 지닌 채로 깨어나는지 확인)
    born_eggs()
    monster_count_map = renew_monster_count_map()

for i in range(1, 5):
    for j in range(1, 5):
        answer += monster_count_map[i][j]
print(answer)