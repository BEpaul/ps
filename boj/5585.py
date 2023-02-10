import sys

cash = int(sys.stdin.readline())
res = 1000 - cash
count = 0

while (True):
    if res >= 500:
        res -= 500
        count += 1

    elif res >= 100:
        res -= 100
        count += 1

    elif res >= 50:
        res -= 50
        count += 1

    elif res >= 10:
        res -= 10
        count += 1

    elif res >= 5:
        res -= 5
        count += 1

    else:
        count += res
        break

print(count)