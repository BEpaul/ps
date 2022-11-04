sentence = input()

count_space = sentence.count(' ') + 1

if sentence[0] == ' ':
    count_space -= 1

if sentence[len(sentence) - 1] == ' ':
    count_space -= 1
    
print(count_space)