N = int(input())
input_list = list(map(int, input().split()))
cnt = 0

for el in input_list:
    temp = 0

    if el == 1:
        continue
    elif el == 2:
        cnt += 1
        continue
    else:    
        for i in range(2, el):
            if el % i == 0:
                temp += 1
                break
    
    if temp == 0:
        cnt += 1


print(cnt)