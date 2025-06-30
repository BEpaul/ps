"""
[구현 순서]
1. 입력 받기 (board, pieces, ...)
2. 90, 180, 270도 부분행렬 회전 함수 구현
3. 탐사 진행
    - 3 X 3 부분행렬을 돌려가면서 최적의 회전행렬을 발견한다.
        - 유물 발견은 board를 순회해가며 bfs 탐색
        - 우선순위: (1) 유물 1차 획득 가치가 최대 (2) 회전 각도 최소 (3) 회전 중심 좌표 열이 작을수록 (4) 행이 작을수록
4. 유물 획득
    - 최적의 회전행렬을 돌렸을 때 3개 이상 연결된 조각들을 점수로 획득
    - 빼낸 조각 부분들에는 0으로 바꿔줌
    - 다시 거꾸로 순회하며 유물 조각들을 채워준다.
    - 이를 더이상 캐낼 유물이 없을 때까지 반복문으로 반복한다.

[REMIND]
- 아직 K번의 턴을 진행하지 못했지만, 탐사 과정에서 어떤 방법으로도 유물을 획득할 수 없다면 모든 탐사가 그 즉시 종료! -> 해당 턴 아무것도 출력 X
- 초기에 주어지는 유적지에서는 탐사 진행 이전 유물 발견 X

"""

from collections import deque
import copy

# 1. 입력 받기 (board, pieces, ...)
K, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(5)]

piece_queue = deque()
li = list(map(int, input().split()))
for l in li:
    piece_queue.append(l)

square_size = 3
visited = [[False] * 5 for _ in range(5)]
delta = [(-1, 0), (0, 1), (1, 0), (0, -1)] # 북 동 남 서
answers = []

def get_optimum_board():
    optimum_board = []
    optimum_count = -1
    optimum_pos_queue = deque()

    ## 90도 ##
    for j in range(3): # 열
        for i in range(3): # 행
            rotated_board = rotate_90(i, j)
            piece_count, piece_pos_queue = get_piece(rotated_board)
            if piece_count > optimum_count:
                optimum_count = piece_count
                optimum_board = rotated_board
                optimum_pos_queue = piece_pos_queue

    ## 180도 ##
    for j in range(3):
        for i in range(3):
            rotated_board = rotate_180(i, j)
            piece_count, piece_pos_queue = get_piece(rotated_board)
            if piece_count > optimum_count:
                optimum_count = piece_count
                optimum_board = rotated_board
                optimum_pos_queue = piece_pos_queue

    ## 270도 ##
    for j in range(3):
        for i in range(3):
            rotated_board = rotate_270(i, j)
            piece_count, piece_pos_queue = get_piece(rotated_board)
            if piece_count > optimum_count:
                optimum_count = piece_count
                optimum_board = rotated_board
                optimum_pos_queue = piece_pos_queue

    return optimum_count, optimum_board, optimum_pos_queue


def get_piece(rotated_board):
    ret = 0
    num = 1
    global visited
    visited = [[False] * 5 for _ in range(5)]
    number_board = [[0] * 5 for _ in range(5)]

    for i in range(5):
        for j in range(5):
            if not visited[i][j]:
                bfs(i, j, rotated_board, number_board, num)
                num += 1
                visited[i][j] = True

    counts = [0] * 26
    # 새롭게 바뀐 board에서 count
    for i in range(5):
        for j in range(5):
            counts[number_board[i][j]] += 1

    i_set = set()
    for i in range(1, 26):
        if counts[i] >= 3:
            ret += counts[i]
            i_set.add(i)

    piece_pos_queue = deque()
    # 다시 number_board 돌면서 조각 위치들 저장
    for j in range(5):
        for i in range(4, -1, -1):
            if number_board[i][j] in i_set:
                piece_pos_queue.append((i, j))

    return ret, piece_pos_queue

def bfs(x, y, rotated_board, number_board, num):
    queue = deque()
    queue.append((x, y))
    number_board[x][y] = num

    while queue:
        now_x, now_y = queue.popleft()
        visited[now_x][now_y] = True

        for dx, dy in delta:
            next_x, next_y = now_x + dx, now_y + dy

            if in_range(next_x, next_y) and not visited[next_x][next_y]:
                if rotated_board[now_x][now_y] == rotated_board[next_x][next_y]:
                    visited[next_x][next_y] = True
                    number_board[next_x][next_y] = num
                    queue.append((next_x, next_y))

# 2. 90, 180, 270도 부분행렬 회전 함수 구현
def rotate_90(sx, sy):
    new_board = copy.deepcopy(board) # return을 위한 보드
    temp_board = [[0] * 5 for _ in range(5)] # 변환(회전)을 위한 보드
    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            ox, oy = x - sx, y - sy
            rx, ry = oy, square_size - ox - 1
            temp_board[sx + rx][sy + ry] = new_board[x][y]

    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            new_board[x][y] = temp_board[x][y]

    return new_board

def rotate_180(sx, sy):
    new_board = copy.deepcopy(board) # return을 위한 보드
    temp_board = [[0] * 5 for _ in range(5)] # 변환(회전)을 위한 보드
    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            ox, oy = x - sx, y - sy
            rx, ry = square_size - ox - 1, square_size - oy - 1
            temp_board[sx + rx][sy + ry] = new_board[x][y]

    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            new_board[x][y] = temp_board[x][y]

    return new_board

def rotate_270(sx, sy):
    new_board = copy.deepcopy(board) # return을 위한 보드
    temp_board = [[0] * 5 for _ in range(5)] # 변환(회전)을 위한 보드
    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            ox, oy = x - sx, y - sy
            rx, ry = square_size - oy - 1, ox
            temp_board[sx + rx][sy + ry] = new_board[x][y]

    for x in range(sx, sx + square_size):
        for y in range(sy, sy + square_size):
            new_board[x][y] = temp_board[x][y]

    return new_board

def in_range(x, y):
    return 0 <= x < 5 and 0 <= y < 5

def get_piece_iteration(board, pos_queue):
    extra_count = 0

    while pos_queue:
        x, y = pos_queue.popleft()
        piece = piece_queue.popleft()
        board[x][y] = piece

    ## 또 찾기
    while get_piece(board)[0]:
        count, pos_queue = get_piece(board)
        extra_count += count
        while pos_queue:
            x, y = pos_queue.popleft()
            piece = piece_queue.popleft()
            board[x][y] = piece

    return extra_count, board

# main
for testcase in range(1, K + 1):
    answer = 0
    first_optimum_count, optimum_board, optimum_pos_queue = get_optimum_board()
    if first_optimum_count == 0:
        break

    answer += first_optimum_count

    extra_count, board = get_piece_iteration(optimum_board, optimum_pos_queue)
    answer += extra_count
    answers.append(answer)

print(*answers)