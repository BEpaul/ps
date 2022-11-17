N = int(input())
list_A = list(map(int, input().split()))
list_B = list(map(int, input().split()))

list_A.sort()
list_B.sort(reverse=True)

res = 0
for i in range(N):
    res += list_A[i] * list_B[i]

print(res)