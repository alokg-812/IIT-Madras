# Goldbach's conjecture


import math

def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            return False
    return True

def Goldbach_even(n):
    primeList = [i for i in range(2, n) if is_prime(i)]
    ans = []
    for i in range(len(primeList)):
        for j in range(i, len(primeList)):
            if primeList[i] + primeList[j] == n:
                ans.append((primeList[i], primeList[j]))
    return ans

def Goldbach_odd(n):
    for p in range(2, n):
        if is_prime(p):
            for k in range(1, int(math.sqrt((n - p) // 2)) + 1):
                if (p+2*k*k == n):
                    return [(p, k, k)]
    return []

def Goldbach(n):
    if n <= 2:
        return []
    elif n % 2 == 0:
        return Goldbach_even(n)
    else:
        return Goldbach_odd(n)

n=int(input())
print(sorted(Goldbach(n)))
