x_list = []
y_list = []

for _ in range(3):
    x, y = map(int, input().split())
    x_list.append(x)
    y_list.append(y)

x_list.sort()
y_list.sort()

if x_list.count(x_list[0]) == 2:
    x_list.remove(x_list[0])
    x_list.remove(x_list[0])
else:
    x_list.remove(x_list[2])

if y_list.count(y_list[0]) == 2:
    y_list.remove(y_list[0])
    y_list.remove(y_list[0])
else:
    y_list.remove(y_list[2])

print(x_list[0], y_list[0])