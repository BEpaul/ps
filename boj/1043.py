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
truth_people = list(map(int, sys.stdin.readline().split()))
set = [i for i in range(n + 1)]

input_list = []
for _ in range(m):
    input_list.append(list(map(int, sys.stdin.readline().split())))
    
for input in input_list:
    for i in range(1, input[0] + 1):
        if i < input[0]:
            union(input[i], input[i + 1])

answer = 0
for input in input_list:
    flag = True
    for i in range(1, truth_people[0] + 1):
        for j in range(1, input[0] + 1):
            if find(input[j]) == find(truth_people[i]):
                flag = False
                break
    if flag:
        answer += 1

print(answer)