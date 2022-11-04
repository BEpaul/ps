N = int(input())
cnt = 0

for i in range(N):
    word = list(input())
    temp_list = [word[0]]
    for index in range(1, len(word)):
        if word[index-1] == word[index]:
            continue
        else:
            if word[index] in temp_list:
                cnt += 1
                break

            else:
                temp_list.append(word[index])

print(N - cnt)