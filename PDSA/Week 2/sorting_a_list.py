# function to sort the list with following method:
# scanning entire list to get the minimum value
# moving this element to a new pile,
# and repeat the process until the list is sorted
# eventually the list would be in an order:


def sort_list(lst):
    # initialize an empty list to store the sorted elements
    sorted_lst = []
    # loop through the list until it is empty
    while lst:
        # find the minimum value in the list
        min_val = min(lst)
        # remove the minimum value from the list
        lst.remove(min_val)
        # append the minimum value to the sorted list
        sorted_lst.append(min_val)
    # return the sorted list
    return sorted_lst


# test the function
print(sort_list([5, 2, 8, 1, 9]))
# output: [9, 8, 5, 2, 1]