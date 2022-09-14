N = int(input())

range_value = 1
cnt = 1

while N > range_value:
    range_value += 6 * cnt
    cnt += 1

print(cnt)
