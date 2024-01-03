t = int(input())

for _ in range(t):
    dict = {}
    n = int(input())
    n_nums = list(map(int, input().split()))

    for num in n_nums:
        dict[num] = 1
    
    m = int(input())
    m_nums = list(map(int, input().split()))

    for num in m_nums:
        if num in dict:
            print(1)
        else:
            print(0)