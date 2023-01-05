def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a%b)

def lcm(a, b):
    return (a * b) // gcd(a, b)

T = int(input())

for i in range(T):
    a, b = map(int, input().split())
    print(lcm(a, b))

# 유클리드 알고리즘 -> 최대공약수와 최소공배수의 관계