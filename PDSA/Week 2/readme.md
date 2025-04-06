# WEEK 2:

- When comparing `t(n)` , we generally focus on the **order of the magnitude**-
- * Ex-
  * `f(n)=n^3` eventually grows faster than `g(n)=5000(n^2)`
### How to Compare?🤔
- Upper Bound- `Big-Oh[O]`
  * shows the worst case scenario for how long the algorithm might take.
  * even for the largest input, Big-Oh is the maximum specific rate.
- Lower Bound- `Omega[Ω]`
  * shows the best case scenario
- Tightly Bound- `Theta[Θ]`
  * describes the exact time growth, representing both upper and lower limits.
  * most realistics estimate

### Calculating complexities:
```py
    a = 10
    b = 20
    s = a + b
    print(s)
```
**Complexity = `O(1)`**

Complexity for single loop
```py
    s = 0
    for i in range(n):
    	s = s + 1
```
**Complexity = `O(n)`**

Complexity for nested two loop
```py
    s = 0
    for i in range(n):
        for j in range(n):
            s = s + 1
```
**Complexity = `O(n²)`**

Complexity for nested three loop
```py
    s = 0
    for i in range(n):
        for j in range(n):
            for k in range(n)
            	s = s + 1
```
**Complexity = `O(n³)`** 

Complexity for combination of all
```py
    s = 0
    for i in range(n):
    	s = s + 1
    s = 0
    for i in range(n):
        for j in range(n):
            s = s + 1
    s = 0
    for i in range(n):
        for j in range(n):
            for k in range(n)
            	s = s + 1
```
**Complexity = `O(n³)`**


Complexity for recursive solution
```py
    def factorial(n)
    	if (n == 0):
    		return 1
    	return n * factorial(n - 1)
```

Recurrence relation? `T(n) = T(n-1)+O(1) = 1+1+1...n times`

**Complexity `O(n)`**

 

Complexity for recursive solution
```py
    def merge(A,B):
        #statement block for merging two sorted array
    def mergesort(A):
        n = len(A)
        if n <= 1:
            return(A)
        L = mergesort(A[:n//2])
        R = mergesort(A[n//2:])
        B = merge(L,R)
        return(B) 
```
Recurrence relation - `T(n)=2T(n/2) + O(n)`

**Complexity `O(nlogn)`**

## Searching Algorithms:
### Naive Approach:
Searching through all values:
```py
    def naiveSearch(val,ls):
        for x in ls:
            if x == val:
                return True
        return False
        
    ls = [1,3,5,7,9,11,13,15]
    print(naiveSearch(8,ls))
    print(naiveSearch(13,ls))
```
`Best Case: O(n)`
`Worst Case: O(n)`
`Average Case: O(n)`

### Binary Search Approach:
Binary search is an efficient search algorithm used to find the position of a target value within a sorted list. The algorithm compares the target value to the middle element of the list. If the target value is equal to the middle element, the search is complete. Otherwise, the algorithm recursively searches either the left or right half of the list, depending on whether the target value is greater or less than the middle element.
```py
# Searching in a sorted list
def binary_search(sorted_list, target):
    low = 0
    high = len(sorted_list) - 1
    while low <= high:
        mid = (low + high) // 2
        if sorted_list[mid] == target:
            return mid
        elif sorted_list[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return -1

# Searching in the list through recursion
def recursive_binary_search(sorted_list, target):
    if len(sorted_list) == 0:
        return -1
    mid = len(sorted_list) // 2
    if sorted_list[mid] == target:
        return mid
    elif sorted_list[mid] < target:
        return recursive_binary_search(sorted_list[mid+1:], target)
    else:
        return recursive_binary_search(sorted_list[:mid], target)

ls = [1,3,5,7,9,11,13,15]
print(binary_search(ls, 11))
print(recursive_binary_search(ls, 11))
```
`Best Case: O(1)`
`Worst Case: O(logn)`
`Average Case: O(logn)`


## Sorting Algorithms:
### Selection Sort:
```py
    def selectionsort(L):
        n = len(L)
        if n < 1:
            return(L)
        for i in range(n):
            minpos = i
            for j in range(i+1,n):
                if L[j] < L[minpos]:
                    minpos = j
            (L[i],L[minpos]) = (L[minpos],L[i])
        return(L)
```
`Best Case: O(n²)`
`Worst Case: O(n²)`
`Average Case: O(n²)`


### Insertion Sort:
```py
    def insertionsort(L):
        n = len(L)
        if n < 1:
            return(L)
        for i in range(n):
            j = i
            while(j > 0 and L[j] < L[j-1]):
                (L[j],L[j-1]) = (L[j-1],L[j])
                j = j-1
        return(L)
```
`Best Case: O(n)`
`Worst Case: O(n²)`
`Average Case: O(n²)`


### Merge Sort:
Merge sort is a popular sorting algorithm that uses the divide-and-conquer technique. It works by recursively dividing an list into two halves, sorting each half separately, and then merging them back together into a single sorted list.

```py
    def merge(A,B): # Merge two sorted list A and B
        (m,n) = (len(A),len(B))
        (C,i,j) = ([],0,0)
        
        #Case 1 :- When both lists A and B have elements for comparing
        while i < m and j < n:
            if A[i] <= B[j]:
                C.append(A[i])
                i += 1
            else:
                C.append(B[j])
                j += 1
        
        #Case 2 :- If list B is over, shift all elements of A to C 
        while i < m:
            C.append(A[i])
            i += 1
        
        #Case 3 :- If list A is over, shift all elements of B to C 
        while j < n:
            C.append(B[j])
            j += 1
        
        # Return sorted merged list   
        return C
    
    
    
    # Recursively divide the problem into sub-problems to sort the input list L    
    def mergesort(L): 
        n = len(L)
        if n <= 1: #If the list contains only one element or is empty return the list.
            return(L)
        Left_Half = mergesort(L[:n//2]) #Recursively sort the left half of the list
        Right_Half = mergesort(L[n//2:]) #Recursively sort the rightt half of the list
        Sorted_Merged_List = merge(Left_Half, Right_Half) # Merge two sorted list Left_Half and Right_Half
        return(Sorted_Merged_List)
```
`Best Case: O(nlogn)`
`Worst Case: O(nlogn)`
`Average Case: O(nlogn)`

# Sorting and Searching Algorithms
---

## Searching Algorithms

| Algorithm      | Best Time Complexity   | Average Time Complexity | Worst Time Complexity | Space Complexity |
|----------------|-------------------------|--------------------------|-----------------------|-------------------|
| **Linear Search (Naive)** | O(1)        | O(n)                    | O(n)                 | O(1)             |
| **Binary Search** (on sorted data) | O(1) | O(log n)               | O(log n)             | O(1)             |
---
## Sorting Algorithms

| Algorithm      | Best Time Complexity   | Average Time Complexity | Worst Time Complexity | Space Complexity |
|----------------|-------------------------|--------------------------|-----------------------|-------------------|
| **Merge Sort** | O(n log n)             | O(n log n)              | O(n log n)           | O(n)             |
| **Quick Sort** | O(n log n)             | O(n log n)              | O(n²)                | O(log n)         |
| **Selection Sort** | O(n²)              | O(n²)                   | O(n²)                | O(1)             |
| **Insertion Sort** | O(n)               | O(n²)                   | O(n²)                | O(1)             |

---
