N = int(input())
li = list(map(int, input().split()))

min_value = min(li)
max_value = max(li)
print(min_value * max_value)