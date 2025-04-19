S = input()
length = len(S)
s_set = set()
i, j = 0, 1
while True:
    s_set.add(S[i:j])

    if (i, j) == (length - 1, length):
        break

    if j == length:
        i += 1
        j = i + 1
    else:
        j += 1

print(len(s_set))