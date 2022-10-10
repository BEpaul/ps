import sys

N = int(sys.stdin.readline())
dic_list = []

for i in range(N):
    dic_list.append(sys.stdin.readline().strip())

dic_list = list(set(dic_list))
dic_list.sort()
dic_list.sort(key=len)
for j in dic_list:
    print(j)