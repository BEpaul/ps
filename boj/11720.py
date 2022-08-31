N = int(input())
number_list = list(map(int, input()))
total = 0

for index in range(N):
    total += number_list[index]

print(total)