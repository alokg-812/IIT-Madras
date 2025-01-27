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
