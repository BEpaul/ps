A, B, V = map(int, input().split())
height = 0
day = 1

while True:
    height += A
    if height >= V:
        break

    height -= B

    day += 1

print(day)