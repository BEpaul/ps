N, M = map(int, input().split())
tree_list = list(map(int, input().split()))

start = 1
end = max(tree_list)

while start <= end:
    mid = (start + end) // 2
    
    temp = 0
    for tree in tree_list:
        if tree >= mid:
            temp += tree - mid

    if temp >= M:
        start = mid+1
    else:
        end = mid-1
        
print(end)





# 이분 탐색의 시간복잡도: O(logN)