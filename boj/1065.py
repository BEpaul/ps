N = int(input())

if N <= 99:
    han_number_list = list(range(1, N+1))
    print(len(han_number_list))

else:
    han_number_list = list(range(1, 100))
    for num in range(100, N+1):
        if (int(str(num)[2]) - int(str(num)[1])) == (int(str(num)[1]) - int(str(num)[0])):
            han_number_list.append(num)
    
    print(len(han_number_list))