import sys

li = []
sum = 0
prj = 100

for _ in range(10):
    sum += int(sys.stdin.readline())
    li.append(sum)

for i in range(10):
    if i == 9:
        print(li[i])
        break
        
    if abs(prj - li[i]) < abs(prj - li[i+1]):
        print(li[i])
        break