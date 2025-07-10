"""
[구현 순서]
1. 입력받기: 술래(x, y, d, mode), 도망자[](x, y, d, dir, ), 나무[](x, y)
2. 도망자 움직임
    (1) 술래로부터 거리가 3 이하인 도망자 선정
        - 이미 잡힌 술래의 경우 out
    (2) 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
        - 움직이려는 칸에 술래가 있는 경우 움직이지 않는다.
        - 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동한다.
    (3) 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나는 경우
        - 방향 반대로 틀기
        - 해당 방향으로 술래가 없다면 1칸 이동
3. 술래 움직임
    (1) 달팽이(나선) 모양 이동 구현
        - 정방향(중앙 -> 바깥) 구현
        - 역방향(바깥 -> 중앙) 구현
    (2) (1, 1) 또는 중심 도착 시 다시 되돌아가도록 구현
4. 도망자 잡기
    (1) 술래의 시야(바라보고 있는 방향 3칸) 내에 도망자가 있는 경우 잡기
    (2) 만약 도망자와 나무가 함께 있는 경우 못 잡음
    (3) 잡은 경우 점수(t * 잡은 도망자 수) 획득
5. k번에 걸쳐 반복

[REMIND]
- 도망자는 중앙에서 시작하지는 않는다.
- 도망자: 좌우로 움직이는 사람: 항상 오른쪽을 보고 시작 / 상하로 움직이는 사람: 항상 아래쪽을 보고 시작
- 나무와 도망자는 초기에 겹쳐져 주어질 수 있다.
"""

import sys
sys.stdin = open('input.txt', 'r')

delta = [(-1, 0), (0, 1), (1, 0), (0, -1)] # 북 동 남 서
def is_in_range(x, y):
    return 1 <= x <= n and 1 <= y <= n

def switch_runner_dir(d):
    if d == 0: return 2
    elif d == 1: return 3
    elif d == 2: return 0
    elif d== 3: return 1

def change_chaser_dir(d, opt):
    if opt == 'inner':
        return 3 if d == 0 else d - 1
    return (d + 1) % 4

# 1. 입력받기: 술래(x, y, d), 도망자[](x, y, d, dir, ), 나무[](x, y)
n, m, h, k = map(int, input().split())
chaser = [n // 2 + 1, n // 2 + 1, 0, 'outer']
runners = [[0] * 3 for _ in range(m)]
trees = [[0] * 2 for _ in range(h)]
history = 0
count = 1
flag = False
answer = 0

for i in range(m):
    rx, ry, rd = map(int, input().split())
    runners[i][0], runners[i][1] = rx, ry
    runners[i][2] = 1 if rd == 1 else 2

for i in range(h):
    trees[i][0], trees[i][1] = map(int, input().split())

for turn in range(1, k + 1):
    print(f'--- {turn}번째 턴 시작 ---')
    # 2. 도망자 움직임
    for i in range(m):
        # - 이미 잡힌 술래의 경우 out
        if runners[i][0] == -1 and runners[i][1] == -1:
            continue

        # (1) 술래로부터 거리가 3 이하인 도망자 선정
        dist = abs(runners[i][0] - chaser[0]) + abs(runners[i][1] - chaser[1])
        if dist > 3:
            continue

        dx, dy = delta[runners[i][2]]
        next_rx, next_ry = runners[i][0] + dx, runners[i][1] + dy
        if is_in_range(next_rx, next_ry): # (2) 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
            # - 움직이려는 칸에 술래가 있는 경우 움직이지 않는다.
            if next_rx == chaser[0] and next_ry == chaser[1]:
                continue
            # - 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동한다.
            runners[i][0], runners[i][1] = next_rx, next_ry
        else: # (3) 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나는 경우
            # - 방향 반대로 틀기
            runners[i][2] = switch_runner_dir(runners[i][2])
            dx, dy = delta[runners[i][2]]
            next_rx, next_ry = runners[i][0] + dx, runners[i][1] + dy
            # - 해당 방향으로 술래가 없다면 1칸 이동
            if not (next_rx == chaser[0] and next_ry == chaser[1]):
                runners[i][0], runners[i][1] = next_rx, next_ry

    # 3. 술래 움직임
    # (1) 달팽이(나선) 모양 이동 구현
    # - 정방향(중앙 -> 바깥) 구현
    if chaser[3] == 'outer':
        dx, dy = delta[chaser[2]]
        chaser[0] += dx
        chaser[1] += dy
        history += 1

        if history == count:
            history = 0
            chaser[2] = change_chaser_dir(chaser[2], chaser[3])
            if flag:  # 방향 바꿔주고 초기화
                count += 1
                flag = False
            else:
                flag = True

        if chaser[0] == 1 and chaser[1] == 1:
            chaser[3] = 'inner'
            chaser[2] = 2
            history = 1
            count = n
            flag = True

    # - 역방향(바깥 -> 중앙) 구현
    elif chaser[3] == 'inner':
        dx, dy = delta[chaser[2]]
        chaser[0] += dx
        chaser[1] += dy
        history += 1

        if history == count:
            history = 0
            chaser[2] = change_chaser_dir(chaser[2], chaser[3])
            if flag:
                count -= 1
                flag = False
            else:
                flag = True

        if chaser[0] == (n // 2 + 1) and chaser[1] == (n // 2 + 1):
            chaser[2] = 0
            chaser[3] = 'outer'
            history = 0
            count = 1
            flag = False

    # 4. 도망자 잡기
    #     (1) 술래의 시야(바라보고 있는 방향 3칸) 내에 도망자가 있는 경우 잡기
    for i in range(3):
        is_tree = False
        delta_x, delta_y = delta[chaser[2]]
        area_x, area_y = chaser[0] + delta_x * i, chaser[1] + delta_y * i
        if not is_in_range(area_x, area_y):
            continue

        for tree in trees:
            # (2) 만약 도망자와 나무가 함께 있는 경우 못 잡음
            if area_x == tree[0] and area_y == tree[1]:
                is_tree = True
                break
        if is_tree:
            continue

        for runner in runners:
            if area_x == runner[0] and area_y == runner[1]:
                runner[0], runner[1] = -1, -1
                answer += turn # (3) 잡은 경우 점수(t * 잡은 도망자 수) 획득

    print('chaser:', chaser)
    print('runners:', runners)
    print('trees:', trees)
    print('answer:', answer)
    print()


print('========')

print(answer)