import math

N = int(input())

li = list(map(int, input().split()))
standard = li[0]
for i in range(1, N):
    gcd = math.gcd(standard, li[i])
    a = standard // gcd
    b = li[i] // gcd
    print("{}/{}".format(a, b))