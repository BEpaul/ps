import sys

li = []
N = int(sys.stdin.readline())
for i in range(N):
    num = int(sys.stdin.readline())
    li.append(num)

li.sort()
for j in li:
    sys.stdout.write(str(j)+'\n')