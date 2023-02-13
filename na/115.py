location = input()

row = ord(location[0]) - 96
column = int(location[1])
movements = [(-2, 1), (-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 2), (-1, 2)]

count = 0
for move in movements:
    row_next = int(row) + move[0]
    column_next = int(column) + move[1]
    if row_next >= 1 and column_next >= 1 and row_next <= 8 and column_next <= 8:
        count += 1

print(count)