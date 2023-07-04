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