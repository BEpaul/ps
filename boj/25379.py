N = int(input())
A = list(map(int, input().split()))

cnt = [0, 0]
temp = [0, 0]

for index in range(N):
    A[index] %= 2
    if A[index] == 1:
        cnt[0] += temp[0]

    elif A[index] == 0:
        temp[0] += 1
        
for index in range(N):
    if A[index] == 0:
        cnt[1] += temp[1]

    elif A[index] == 1:
        temp[1] += 1
        
print(min(cnt))