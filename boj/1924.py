X, Y = map(int, input().split())

days = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT']
last = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

day = 0

for i in range(0, X - 1):
    day += last[i]

day = (day + Y) % 7

print(days[day])