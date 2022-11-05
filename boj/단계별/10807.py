import sys

N = int(sys.stdin.readline())

li = list(map(int, input().split()))
v = int(sys.stdin.readline())
print(li.count(v))