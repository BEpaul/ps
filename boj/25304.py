sum = int(input())
iter = int(input())
res = 0

for i in range(iter):
    price, num = map(int, input().split())
    res += price * num

if res == sum:
    print('Yes')
else:
    print('No')