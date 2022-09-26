N = int(input())

five_share = N // 5
five_rem = N % 5

if N == 4 or N == 7:
    print(-1)
else:
    if five_rem == 0:
        print(five_share)

    elif five_rem == 1:
        print(five_share + 1)

    elif five_rem == 2:
        print(five_share + 2)

    elif five_rem == 3:
        print(five_share + 1)

    elif five_rem == 4:
        print(five_share + 2)
