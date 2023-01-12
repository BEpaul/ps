arr = [[0 for _ in range(101)] for _ in range(101)]
N = int(input())

for _ in range(N):
    x, y = map(int, input().split())

    for row in range(x, x+10):
        for col in range(y, y+10):
            arr[row][col] = 1

res = 0
for i in range(101):
    res += sum(arr[i])

print(res)