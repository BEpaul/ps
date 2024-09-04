import sys
sys.setrecursionlimit(1000000)

def find(num):
    if set[num] != num:
        set[num] = find(set[num])
    return set[num]

def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        set[b] = a
    else:
        set[a] = b

n, m = map(int, sys.stdin.readline().split())
set = [0] * (n + 1)
for i in range(1, n + 1):
    set[i] = i

for _ in range(m):
    opt, a, b = map(int, sys.stdin.readline().split())

    if opt == 0: # union
        union(a, b)
    else: # find
        if find(a) == find(b):
            print('YES')
        else:
            print('NO')
