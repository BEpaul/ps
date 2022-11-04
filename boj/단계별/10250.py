T = int(input())

for i in range(T):
    H, W, N = map(int, input().split())

    if N % H ==0:
        room_num_1 = H
        room_num_2 = N // H

    else:
        room_num_1 = N % H
        room_num_2 = N // H + 1

    print(str(room_num_1)+str(room_num_2).zfill(2))