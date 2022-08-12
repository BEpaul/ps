import sys

iter = int(input())

for i in range(iter):
    A, B = map(int,sys.stdin.readline().split())
    print(A + B)