S = list(input())
alphabet = 'abcdefghijklmnopqrstuvwxyz'

for alpha in alphabet:
    if alpha in S:
        print(S.index(alpha), end=' ')
    else:
        print(-1, end=' ')
