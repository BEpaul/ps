def solution(s):
    answer = [0, 0]
    
    while True:
        answer[0] += 1
        for c in s:
            if c == '0':
                answer[1] += 1
        s = s.replace('0', '')        

        leng = len(s)

        s = format(leng, 'b')
        if s == '1':
            break
    
    return answer