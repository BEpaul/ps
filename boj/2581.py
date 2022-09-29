M = int(input())
N = int(input())

cnt_list = []

for i in range(M, N+1):
    temp = 0

    if i == 1:
        continue
    elif i == 2:
        cnt_list.append(2)
        continue
    else:
        for j in range(2, i):
            if i % j == 0:
                temp += 1
                break

    if temp == 0:
        cnt_list.append(i)

if len(cnt_list) == 0:
    print(-1)
else:
    print(sum(cnt_list))
    print(min(cnt_list))