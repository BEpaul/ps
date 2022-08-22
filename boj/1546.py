N = int(input())
score_list = list(map(int, input().split()))
sum = 0
M = max(score_list)

for i in range(len(score_list)):
    score_list[i] = score_list[i] / M * 100
    sum += score_list[i]

print(sum / N)