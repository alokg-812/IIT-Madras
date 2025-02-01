def insort(lst):
    n = len(lst)
    for i in range(1, n):
        j =i
        while j>0 and lst[j][1] > lst[j-1][1]:
            lst[j], lst[j-1] = lst[j-1],lst[j]
            j -=1
    return lst

def DishPrepareOrder(arr):
    cnt = {}
    for order in arr:
        cnt[order] = cnt.get(order, 0) + 1
    R = [(ID, count) for ID, count in cnt.items()]
    R.sort()
    R = insort(R)
    return [tup[0] for tup in R]
    
nums = eval(input())
print(DishPrepareOrder(nums))
