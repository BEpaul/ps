T = int(input())

def cal_resid(resid, iter):
    temp = 0
    for i in range(iter):
        resident = []
        temp += resid[i]
        resident[i] = temp
    return resident

# resid = 1

for i in range(T):
    k = int(input())
    n = int(input())
    
    resid_list = list(range(1,n+1))

    for j in range(k):
        res = cal_resid(resid_list, n+1)
        resid_list = res
    
print(resid_list[0])