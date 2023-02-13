n = int(input())

move_list = list(input().split())
location = [1, 1]

for move in move_list:
    if move == 'L' and location[1] != 1:
        location[1] -= 1
    elif move == 'R' and location[1] != n:
        location[1] += 1
    elif move == 'U' and location[0] != 1:
        location[0] -= 1
    elif move == 'D' and location[0] != n:
        location[0] += 1

print(location[0], location[1])