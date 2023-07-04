pb_word = input()
find_word = input()
count = 0

while True:
    idx = pb_word.find(find_word)
    if idx == -1:
        break
    else:
        pb_word = pb_word[(idx+len(find_word)):]
        count += 1

print(count)

# 짧은 풀이... split()을 이렇게 활용할 수도 있넹
# word = input()
# small = input()
# sp_word = word.split(small)

# print(len(sp_word) - 1)