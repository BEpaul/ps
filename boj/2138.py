import sys

n = int(sys.stdin.readline())
cur = list(map(int, sys.stdin.readline().rstrip('\n')))
prj = list(map(int, sys.stdin.readline().rstrip('\n')))

def push_button(button):
    if button == 0:
        button = 1
    else:
        button = 0

    return button

def change_button(bulb, index):
    
    count = 0
    if index == 0:
        bulb[0] = push_button(bulb[0])
        bulb[1] = push_button(bulb[1])
        count += 1

    for i in range(1, n):
        if bulb[i-1] != prj[i-1]:
            count += 1
            bulb[i-1] = push_button(bulb[i-1])
            bulb[i] = push_button(bulb[i])

            if i != n-1:
                bulb[i+1] = push_button(bulb[i+1])

    if bulb == prj:
        return count
    
    else:
        return int(1e7)

# 첫 버튼 누른 경우 
push_first_btn = change_button(cur[:], 0)

# 첫 버튼 누르지 않은 경우
no_push_first_btn = change_button(cur[:], 1)

res = min(push_first_btn, no_push_first_btn)

if res == int(1e7):
    print(-1)
else:
    print(res)