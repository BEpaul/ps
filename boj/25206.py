import sys

def getScore(grade):
    if grade == 'A+':
        return 4.5
    if grade == 'A0':
        return 4.0
    if grade == 'B+':
        return 3.5
    if grade == 'B0':
        return 3.0
    if grade == 'C+':
        return 2.5
    if grade == 'C0':
        return 2.0
    if grade == 'D+':
        return 1.5
    if grade == 'D0':
        return 1.0
    if grade == 'F':
        return 0
    return -1

grades = [sys.stdin.readline().rstrip() for _ in range(20)]
score_sum = 0
subject_sum = 0

for grade in grades:
    splitted = grade.split(' ')
    mult = getScore(splitted[2])
    if mult != -1:
        subject_sum += int(float(splitted[1]))
        score_sum += mult * int(float(splitted[1]))

print(round(score_sum / subject_sum, 6))
        

    