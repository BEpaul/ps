N = int(input())
N_list = list(map(int, input().split()))
M = int(input())
M_list = list(map(int, input().split()))

N_list.sort()


for m in M_list:
    start, end = 0, N-1
    flag = False
    while start <= end:
        mid = (start + end) // 2

        if N_list[mid] > m:
            end = mid-1

        elif N_list[mid] == m:
            flag = True
            break

        else:
            start = mid+1

    if flag == True:
        print(1)
    else:
        print(0)


















# N = int(input())
# N_list = list(map(int, input().split()))

# M = int(input())
# M_list = list(map(int, input().split()))

# for m in M_list:
#     if m in N_list:
#         print(1)
#     else:
#         print(0)