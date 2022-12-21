import sys

N = int(sys.stdin.readline())
li = []

for _ in range(N):
    command = list(sys.stdin.readline().split())
    if command[0] == 'push':
        li.append(int(command[1]))

    elif command[0] == 'pop':
        if li:
            print(li[len(li)-1])
            li.pop()
        else:
            print(-1)

    elif command[0] == 'size':
        print(len(li))

    elif command[0] == 'empty':
        if li:
            print(0)
        else:
            print(1)

    elif command[0] == 'top':
        if li:
            print(li[len(li)-1])
        else:
            print(-1)