X = int(input())

range_value = 1
cnt = 2
temp = 0

while X > range_value:
    temp = range_value
    range_value += cnt
    cnt += 1

rem = X - temp

if cnt % 2 == 0:
    print(str(cnt-rem) + '/' + str(rem))
elif cnt % 2 == 1:
    print(str(rem) + '/' + str(cnt-rem))


## 홀짝 나누어서 진행