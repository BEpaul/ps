def factorial(n):
    if n > 1:
        return n * factorial(n-1)
    else:
        return 1

N = int(input())
val = 10
cnt = 0

for _ in range(len(str(factorial(N)))):
    if factorial(N) % val == 0:
        cnt += 1
        val *= 10
        continue

    else:
        break

print(cnt)