alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
input_str = input().upper()
count_list = []

for alpha in alphabet:
    count_list.append(input_str.count(alpha))
    
sorted_list = sorted(count_list)

if sorted_list[len(sorted_list) - 1] == sorted_list[len(sorted_list) - 2]:
    print('?')
else:
    print(alphabet[count_list.index(max(count_list))])
