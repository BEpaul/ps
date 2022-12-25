T = int(input())

for _ in range(T):
    val = input()
    li = []
    flag = True

    for v in val:
        if v == '(':
            li.append(val)
        elif v == ')':
            if li:
                li.pop()
            else:
                flag = False

    if not li and flag == True:
        print('YES')

    else:
        print('NO')