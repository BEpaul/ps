from bisect import bisect_right

N = int(input())
N_list = list(map(int, input().split()))
M = int(input())
M_list = list(map(int, input().split()))

N_list.sort()

for m in M_list:
    start, end, res_idx = 0, N-1, N
    while start <= end:
        mid = (start + end) // 2
        if m <= N_list[mid]:
            res_idx = mid
            end = mid-1
        
        else:
            start = mid+1
    if res_idx <= N-1:
        if N_list[res_idx] != m:
            print(0, end=' ')
        else:
            print(bisect_right(N_list, m)-res_idx, end=' ')
            
    else:
        print(0)