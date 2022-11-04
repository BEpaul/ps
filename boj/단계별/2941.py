input_alphabet = input()

cro_list = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']

for cro in cro_list:
    input_alphabet = input_alphabet.replace(cro, '0')

print(len(input_alphabet))