N = int(input())

time_list = []

for i in range(N):
    A, B = map(int, input().split())
    if A <= B:
        time_list.append(B)

if not time_list:
    print(-1)
else:
    print(min(time_list))