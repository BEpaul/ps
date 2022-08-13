iter = int(input())

for i in range(1, iter+1):
    A, B = map(int, input().split())
    print("Case #{}: {}".format(i, A+B))