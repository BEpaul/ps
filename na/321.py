n = input()
size = len(n)

left = 0
right = 0

for i in range(0, size // 2):
    left += int(n[i])
    right += int(n[i + size // 2])

if left == right:
    print("LUCKY")
else:
    print("READY")