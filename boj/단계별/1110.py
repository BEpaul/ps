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

# 반복문 안에서 loop 돌때 변수 초기화 신경쓰자!