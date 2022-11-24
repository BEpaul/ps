height_list = []

for _ in range(9):
    h = int(input())
    height_list.append(h)

height_list.sort()

for i in range(9):
    for j in range(i+1, 9):
        total = sum(height_list)
        hsum = total - height_list[i] - height_list[j]
        if hsum == 100:
            del height_list[j]
            del height_list[i]
            for height in height_list:
                print(height)
            exit(0)
        
## 파이썬의 itertool-permutations 라이브러리를 활용하여 조합을 할 수도 있다.