x, y = input().split()

time = 60 * int(x) + int(y)

if time < 45:
    alarm = 1440 - (45 - time)

else:
    alarm = time - 45

hour = alarm // 60
minute = alarm % 60

print(hour, minute)