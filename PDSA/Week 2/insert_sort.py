def InsertionSort(L):
    n = len(L)
    if n < 1:
        return L
    for i in range(1, n):
        j = i
        while j > 0 and L[j] < L[j - 1]:
            L[j], L[j - 1] = L[j - 1], L[j]
            j -= 1
    return L

# test the code
print(InsertionSort([5, 2, 8, 1, 9]))


# recursive insertion sortign

def RecursiveInsertionSort(L, n=None):
    if n is None:
        n = len(L)
    if n <= 1:
        return L
    RecursiveInsertionSort(L, n - 1)
    last = L[n - 1]
    j = n - 2
    while j >= 0 and L[j] > last:
        L[j + 1] = L[j]
        j -= 1
    L[j + 1] = last
    return L

print(RecursiveInsertionSort([5, 2, 8, 1, 9, 11, 19, 18]))