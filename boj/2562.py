li = []

for i in range(1,10):
    num = int(input())
    li.append(num)

print(max(li))
print(li.index(max(li))+1)
