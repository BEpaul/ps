total_number_set = set(range(1,10001)) # 전체 숫자 집합 정의
not_self_number_set = set() # 생성자가 있는 숫자들의 집합 정의

for number in total_number_set:
    for num in str(number):
        number += int(num)
    
    not_self_number_set.add(number)

self_number_set = total_number_set - not_self_number_set # (전체 숫자 집합) - (생성자가 있는 숫자 집합)

for self_number in sorted(self_number_set): # 오름차순으로 정렬한 뒤 각 숫자들 출력
    print(self_number)