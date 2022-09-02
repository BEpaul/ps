test = int(input())

for i in range(test):
    iter, char = input().split()

    for c in char:
        print(c * int(iter), end='')
    print('')