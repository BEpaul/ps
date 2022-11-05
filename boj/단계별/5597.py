set_list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]

sub_list = []

for _ in range(28):
    num = int(input())
    sub_list.append(num)


res_list = list(set(set_list)-set(sub_list))

res_list.sort()
for res in res_list:
    print(res)