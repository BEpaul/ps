import sys
from collections import Counter

N = int(input())
num_list = []
for i in range(N):
    num_list.append(int(sys.stdin.readline()))

num_list.sort()

# 산술평균
print(round(sum(num_list) / len(num_list)))

# 중앙값
print(num_list[int((len(num_list)+ 1)/2) - 1])

# 최빈값
cnt = Counter(num_list).most_common(2)

if len(num_list) > 1:
    if cnt[0][1] == cnt[1][1]:
        print(cnt[1][0])
    else:
        print(cnt[0][0])
else:
    print(cnt[0][0])
    
# 차
print(max(num_list) - min(num_list))


# collections.Counter
# most_common(k): 최빈값 구할 수 있음(k번째까지)
# Key를 이용하여 Value 갱신도 가능!