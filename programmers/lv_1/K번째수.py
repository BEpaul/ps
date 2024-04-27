def solution(array, commands):
    answer = []
    
    for command in commands:
        i = command[0]
        j = command[1]
        k = command[2]
        
        splitted_arr = array[i-1:j]
        splitted_arr.sort()
        answer.append(splitted_arr[k-1])
    
    return answer