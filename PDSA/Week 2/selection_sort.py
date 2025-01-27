# funtion for sorting without any extra list:
def selection_sort(lst):
    for i in range(len(lst)):
        for j in range(len(lst)):
            if lst[i] > lst[j]:
                lst[i], lst[j] = lst[j], lst[i]
    return lst

# test the function
print(selection_sort([5, 2, 8, 1, 9])) 


def SelectionSort(L):
    n = len(L)
    if n < 1:
        return L
    for i in range(n):
        mpos = i
        for j in range(i + 1, n):
            if L[j] < L[mpos]:
                mpos = j
        L[i], L[mpos] = L[mpos], L[i]
    return L
print(SelectionSort([5, 2, 8, 1, 9])
