N, M = map(int, input().split())
min = min(N, M)
max = max(N, M)

for gcd in range(min, 0, -1):
    if N % gcd == 0 and M % gcd == 0:
        print(gcd)
        break

    else:
        continue

lcm = max
while True:
    if lcm % N == 0 and lcm % M == 0:
        print(lcm)
        break
    
    else:
        lcm += 1
        continue