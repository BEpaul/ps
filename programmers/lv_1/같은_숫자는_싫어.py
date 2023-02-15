def solution(arr):
    answer = []
    
    if arr:
        answer.append(arr[0])  
        
        for a in arr:
            if answer[len(answer)-1] != a:
                answer.append(a)
    
    return answer