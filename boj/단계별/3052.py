li = []

for i in range(10):
    num = int(input()) % 42
    li.append(num)

print(len(set(li)))