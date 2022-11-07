total_list = []

for _ in range(9):
    li = list(map(int, input().split()))
    total_list.append(li)

x = 0
y = 0
max = -1

for i in range(9):
    for j in range(9):
        if total_list[i][j] > max:
            max = total_list[i][j]
            x = i+1
            y = j+1

print(max)
print(x, y)