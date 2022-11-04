import sys

N = int(sys.stdin.readline())
cnt = 0
for num in range(1000001):
    res = num
    for number in str(num):
        num += int(number)
    
    if N == num:
        cnt += 1
        print(res)
        break
    else:
        continue

if cnt == 0:
    print(0)