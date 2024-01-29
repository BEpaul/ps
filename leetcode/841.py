class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        visited = [False] * len(rooms)
        visited[0] = True
        queue = deque()
        queue.append(rooms[0])

        def bfs():
            while queue:
                room = queue.popleft()
                for key in room:
                    if not visited[key]:
                        visited[key] = True
                        queue.append(rooms[key])

        bfs()
        
        return all(visited)