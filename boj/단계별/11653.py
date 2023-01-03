N = int(input())

temp = 2
while N > 1:
    if N % temp == 0:
        N /= temp
        print(temp)
    
    else:
        temp += 1
        continue