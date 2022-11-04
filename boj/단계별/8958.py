testcase = int(input())

for iter in range(testcase):
    
    li = list(input())
    value = 0
    sum = 0

    for i in range(len(li)):
        if li[i] == 'O':
            value += 1
        
        elif li[i] == 'X':
            value = 0
        
        sum += value

    print(sum)