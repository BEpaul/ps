N, M = map(int, input().split())

num_list = list(map(int, input().split()))
res_list = []

for i in range(N):
    for j in range(i+1, N):
        for k in range(j+1, N):
            res = num_list[i] + num_list[j] + num_list[k]
            if res <= M:
                res_list.append(res)
            else:
                continue

print(max(res_list))