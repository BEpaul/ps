while(True):
    length_list = list(map(int, input().split()))
    
    if sum(length_list) == 0:
        break

    max_len = max(length_list)
    length_list.remove(max_len)

    if length_list[0] ** 2 + length_list[1] ** 2 == max_len ** 2:
        print("right")
    else:
        print("wrong")