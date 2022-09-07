N = int(input())
A = list(map(int, input().split()))

compare_value = 1
cnt = 0

for index in range(N):
    if A[index] == compare_value:
        compare_value += 1

    elif A[index] != compare_value:
        cnt += 1

print(cnt)