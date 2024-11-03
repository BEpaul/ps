def solution(cacheSize, cities):
    answer = 0
    cache = []
    
    if cacheSize == 0:
        return len(cities) * 5
    
    for city in cities:
        city = city.lower()
        if len(cache) == cacheSize:
            if city in cache:
                cache.remove(city)
                cache.append(city)
                answer += 1
            else:
                cache.pop(0)
                cache.append(city)
                answer += 5
        elif not cache:
            cache.append(city)
            answer += 5
        else:
            if city in cache:
                cache.remove(city)
                cache.append(city)
                answer += 1
            else:
                cache.append(city)
                answer += 5
        
    return answer