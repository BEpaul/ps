n = int(input())
status = set()
for _ in range(n):
    name, cmd = input().split()
    if cmd == 'enter':
        status.add(name)
    else:
        status.remove(name)

names = []
for s in status:
    names.append(s)

names.sort(reverse=True)
for name in names:
    print(name)