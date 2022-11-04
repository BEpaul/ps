C = int(input())

for iter in range(C):
    li = list(map(int, input().split()))
    sum = 0
    part = 0

    for i in range(1, len(li)):
        sum += li[i]

    avg = sum / li[0]

    for i in range(1, len(li)):
        if li[i] > avg:
            part += 1

    print("%0.3f%%" % (part/li[0] * 100))


# 8~9 line 배열의 합 sum(li[1:]) 로 계산 가능