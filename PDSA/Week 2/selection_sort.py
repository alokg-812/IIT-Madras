# funtion for sorting without any extra list:
def selection_sort(lst):
    for i in range(len(lst)):
        for j in range(len(lst)):
            if lst[i] > lst[j]:
                lst[i], lst[j] = lst[j], lst[i]
    return lst

# test the function
print(selection_sort([5, 2, 8, 1, 9])) 