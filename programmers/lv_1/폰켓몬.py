def solution(nums):
    answer = 0
    set_count = len(set(nums))
    list_count_half = len(nums) // 2
    
    if set_count <= list_count_half:
        answer = set_count
    else:
        answer = list_count_half
    
    return answer