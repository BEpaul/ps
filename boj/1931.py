import sys

n = int(sys.stdin.readline())
meeting_list = []
count = 0

for _ in range(n):
    li = list(map(int, sys.stdin.readline().split()))
    meeting_list.append(li)
    
meeting_list.sort(key = lambda x: (x[1], x[0]))

meeting_end = 0
for meeting in meeting_list:
    if meeting[0] >= meeting_end:
        count += 1
        meeting_end = meeting[1]

print(count)