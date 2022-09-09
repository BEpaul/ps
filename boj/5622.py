input_alphabet = input()
alphabet_list = ['ABC','DEF','GHI','JKL','MNO','PQRS','TUV','WXYZ']
cnt = 0

for col in alphabet_list:
    for alpha in col:
        for inp in input_alphabet:
            if alpha == inp:
                cnt += alphabet_list.index(col) + 3

print(cnt)