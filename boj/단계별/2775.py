T = int(input())

for i in range(T):
    k = int(input())
    n = int(input())
    floor_resid = [list(range(1, 15))]

    for j in range(1, 15):
        temp_list = []
        resid = 0
        for index in range(14):
            resid += floor_resid[j-1][index]
            temp_list.append(resid)
            
        floor_resid.append(temp_list)

    print(floor_resid[k][n-1])