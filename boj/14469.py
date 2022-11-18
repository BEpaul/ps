N = int(input())

total_list = []
for _ in range(N):
    cow_list = list(map(int, input().split()))
    total_list.append(cow_list)

total_list.sort(key=lambda x: x[0])
res = -1

for i in range(N):
    if total_list[i][0] >= res:
        res = total_list[i][0] + total_list[i][1]

    else:
        res += total_list[i][1]

print(res)