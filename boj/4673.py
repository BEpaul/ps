def selfnumber(n):
    res = 0
    while n:
        n //= 10
        res += 1

