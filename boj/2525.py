x, y = input().split()
oven_time = int(input())

total_time = int(x) * 60 + int(y) + oven_time

if total_time >= 1440:
    total_time -= 1440

hour = total_time // 60
minute = total_time % 60

print(hour, minute)