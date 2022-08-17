num = int(input())
const = num
cnt = 0

while True:
    A = num // 10
    B = num % 10
    C = B * 10 + ((A+B) % 10)
    num = C
    cnt += 1
    if (num == const):
        break

print(cnt)
