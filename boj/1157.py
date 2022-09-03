l_alpha = 'abcdefghijklmnopqrstuvwxyz'
h_alpha = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

input_str = input()
l_count_list = []
h_count_list = []

for l in l_alpha:
    l_count_list.append(input_str.count(l))

print(l_count_list.index('h'))
    
        