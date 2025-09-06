"""
[구현 순서]
1. 입력 받기: 기사들 knights = [[r, c, h, w, k], ...], knights_map, chess_map
2. 기사 이동
    (1) 명령 받은 기사는 상하좌우로 한 칸 이동한다.
        - bfs를 사용하여 순회하며 한 칸씩 미룬다.
            - 만약 다음 칸에 벽이 있다면 모든 과정을 중단하고 무효화한다.
            - 다음 칸에 사람이 있다면 bfs 큐에 추가한다.
            - 다음 칸이 벽도 사람도 아니라면, 로직을 마무리하고 이동을 시작한다.
    (2) 이동을 하는 기사는 항상 alive (체력이 1 이상) 이어야 함을 체크한다.
    (3) 만약 중간에 벽으로 막히게 된다면 move 메모리는 초기화한다. (없던 일로 한다.)
    (4) 이동을 하게 된다면, knights_map 또한 갱신시켜준다.
3. 대결 데미지
    (1) 밀려난 기사들이 함정 위에 있다면 그 개수만큼 피해를 입는다. 즉, 2.에서 이동한 기사들은 미리 체크해둔다.
    (2) 명령을 받은 기사는 피해를 입지 않는다.
4. 정답 출력
    (1) Q번의 왕의 명령이 모두 종료되면 생존한 기사들이 받은 총 데미지 합을 출력한다.

[Remind]
- 이동 시 기사들을 연쇄적으로 이동시키는 것 중요!
- 밀렸더라도 밀쳐진 위치에 함정이 전혀 없다면 그 기사는 피해를 전혀 입지 않게 됨
- 처음 주어지는 기사들끼리와 벽은 서로 겹쳐져 있지 않는다.
- 체력이 0이 된 기사를 제외하는 것 잊지 않기
- 정답 출력은 종료 시점에 생존한 기사들이 받은 총 데미지 합을 계산한다.
"""
import sys # 제출 시 삭제
from collections import deque
sys.stdin = open('input.txt', 'r')

# === 입력 받기: 기사들 knights = [[r, c, h, w, k], ...], knights_map, chess_map === #
l, n, q = map(int, input().split())
chess_map = [[0] * (l + 1)] + [[0] + list(map(int, input().split())) for _ in range(l)]
knights = [[0]] + [list(map(int, input().split())) for _ in range(n)]
orders = [list(map(int, input().split())) for _ in range(q)]
direction = [(-1, 0), (0, 1), (1, 0), (0, -1)] # 북 동 남 서
moves = set() # 이동한 기사들 기록
damages = [0] * (n + 1)
answer = 0

knights_map = [[0] * (l + 1) for _ in range(l + 1)]
visited = [[False] * (l + 1) for _ in range(l + 1)]

for i in range(1, n + 1):
    r, c = knights[i][0], knights[i][1]
    h, w = knights[i][2], knights[i][3]
    for j in range(h):
        for k in range(w):
            knights_map[r + j][c + k] = i

def move_knights(i, d):
    moves.clear()

    # (1) 명령 받은 기사는 상하좌우로 한 칸 이동한다.
    # (2) 이동을 하는 기사는 항상 alive (체력이 1 이상) 이어야 함을 체크한다.
    r, c, h, w, k = knights[i]
    if k == 0:
        return False

    global knights_map

    # - bfs를 사용하여 순회하며 한 칸씩 미룬다.
    can_move = bfs(i, d)
    # (3) 만약 중간에 벽으로 막히게 된다면 move 메모리는 초기화한다. (없던 일로 한다.)
    if not can_move:
        moves.clear()
        return False

    # 이동 시키기
    for move in moves:
        r, c, h, w, k = knights[move]
        dx, dy = direction[d]
        knights[move][0], knights[move][1] = r + dx, c + dy

    # (4) 이동을 하게 된다면, knights_map 또한 갱신시켜준다.
    # knights_map 최신화
    knights_map = [[0] * (l + 1) for _ in range(l + 1)]

    moves.discard(i)
    renew_knights_map()

    return True

# - bfs를 사용하여 순회하며 한 칸씩 미룬다.
def bfs(knight_num, knight_dir):
    global visited
    visited = [[False] * (l + 1) for _ in range(l + 1)]
    x, y = knights[knight_num][0], knights[knight_num][1]
    queue = deque()
    queue.append((x, y, knight_num))

    while queue:
        now_x, now_y, now_num = queue.popleft()
        visited[now_x][now_y] = True
        moves.add(now_num)

        # 자신의 범위 밖 탐색
        dir_x, dir_y = direction[d]
        outer_next_x, outer_next_y = now_x + dir_x, now_y + dir_y

        # - 만약 다음 칸에 벽이 있다면 모든 과정을 중단하고 무효화한다.
        # 만약 밀리는 방향에 벽이 있는 경우 (격자 벗어남)
        if outer_next_x < 1 or outer_next_x >= l + 1 or outer_next_y < 1 or outer_next_y >= l + 1:
            return False

        # 만약 밀리는 방향에 벽이 있는 경우 (chess_map = 2)
        if chess_map[outer_next_x][outer_next_y] == 2:
            return False

        # - 다음 칸에 사람이 있다면 bfs 큐에 추가한다.
        if knights_map[outer_next_x][outer_next_y] != now_num and knights_map[outer_next_x][outer_next_y] != 0:
            if not visited[outer_next_x][outer_next_y]:
                visited[outer_next_x][outer_next_y] = True
                queue.append((outer_next_x, outer_next_y, knights_map[outer_next_x][outer_next_y]))

        # 자신의 범위 내 탐색
        for dx, dy in direction:
            next_x, next_y = now_x + dx, now_y + dy

            # 벽인지 아닌지 체크
            if 1 <= next_x < l + 1 and 1 <= next_y < l + 1:
                if chess_map[next_x][next_y] != 2 and not visited[next_x][next_y]:
                    if knights_map[next_x][next_y] == now_num:
                        queue.append((next_x, next_y, now_num))
                        visited[next_x][next_y] = True

    return True

def in_range(x, y):
    return 1 <= x < l + 1 and 1 <= y < l + 1

def damage():
    global answer, knights_map
    # (1) 밀려난 기사들이 함정 위에 있다면 그 개수만큼 피해를 입는다. 즉, 2.에서 이동한 기사들은 미리 체크해둔다.
    for moved_knight_num in moves:
        for i in range(knights[moved_knight_num][0], knights[moved_knight_num][0] + knights[moved_knight_num][2]):
            for j in range(knights[moved_knight_num][1], knights[moved_knight_num][1] + knights[moved_knight_num][3]):
                if chess_map[i][j] == 1:
                    if knights[moved_knight_num][4] > 0:
                        knights[moved_knight_num][4] -= 1
                        damages[moved_knight_num] += 1

    # (3) 죽은 기사는 knights_map에서 없앤다.
    renew_knights_map()

def renew_knights_map():
    global knights_map
    # knights_map 최신화
    knights_map = [[0] * (l + 1) for _ in range(l + 1)]
    for i in range(1, n + 1):
        if knights[i][4] == 0:
            continue

        r, c = knights[i][0], knights[i][1]
        h, w = knights[i][2], knights[i][3]
        for j in range(h):
            for k in range(w):
                knights_map[r + j][c + k] = i


for seq, order in enumerate(orders):
    # print('seq:', seq)
    i, d = order
    # === 2. 기사 이동 ===
    is_blocked = move_knights(i, d)
    if not is_blocked:
        continue

    # === 3. 대결 데미지 ===
    damage()

# 4. 정답 출력
#     (1) Q번의 왕의 명령이 모두 종료되면 생존한 기사들이 받은 총 데미지 합을 출력한다.
for i in range(1, n + 1):
    if knights[i][4] > 0:
        answer += damages[i]

print(answer)