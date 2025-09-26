from collections import defaultdict

def solution(tickets):
    route = []
    
    def dfs(start):
        while graph[start]:
            dest = graph[start].pop()
            dfs(dest)
        route.append(start)
    
    graph = defaultdict(list)

    for s, e in tickets:
        graph[s].append(e)
        
    for key in graph:
        graph[key].sort(reverse = True)
        
        
    dfs("ICN")
    
    return route[::-1]