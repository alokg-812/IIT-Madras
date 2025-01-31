# ALGORITHMS:

# WEEK 1:

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
# WEEK 3:

##### What is QuickSort?
QuickSort is a highly efficient sorting algorithm that uses a divide-and-conquer strategy to sort elements in an array or list. It's known for its speed and is widely used in practice.
##### How QuickSort Works:
1. Choose a **pivot**: Select an element from the array as the pivot.
2. **Partitioning**: Rearrange the array so that:
   * All elements less than the pivot are on its left
   * All elements greater than the pivot are on its right
3. **Recursion**: Apply the same process to the sub-arrays on the left and right of the pivot.

##### Key Features-
- **Average Time Complexity**: `O(nlogn)`
- **Worst Case Time Complexity**: `O(n²)` (rare, occurs with poor pivot choices)
- **Space Complexity**: `O(logn)`
- **In-place sorting**: Requires little additional memory

Pseudocode:
```py
def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]  # swap
    arr[i + 1], arr[high] = arr[high], arr[i + 1]  # swap
    return i + 1

def quickSort(arr, low, high):
    if low < high:
        pivot = partition(arr, low, high)
        quickSort(arr, low, pivot - 1)
        quickSort(arr, pivot + 1, high)
```
* Advantages:
* - Fast on average
  - Efficient for large datasets
  - In-place sorting (doesn't require much extra memory)
* Disadvantages:
* - Worst-case performance is poor
  - Not stable (may change the relative order of equal elements)

Qs. When to Use QuickSort??
- When average-case performance is important
- For large datasets
- When memory usage is a concern
## Comparision of Sorting Algorithms

| Parameter      | Best Case   | Average Case | Worst Case | In-place | Stable |
|----------------|-------------------------|--------------------------|-----------------------|-------------------|-------------------|
| **Selection Sort** | O(n²)              | O(n²)                   | O(n²)                | Yes             | No               |
| **Insertion Sort** | O(n)               | O(n²)                   | O(n²)                | Yes             | Yes              |
| **Merge Sort** | O(nlogn)               | O(nlogn)                | O(nlogn)             | No              | Yes              |
| **Quick Sort** | O(nlogn)               | O(nlogn)                | O(n²)                | Yes             | No               |

## Linked List:

### Why Not Use Arrays?
Before understanding linked lists, let's see the disadvantages of arrays:
- **Fixed Size**: Once an array is created, its size cannot be changed.
- **Memory Waste**: If we allocate extra space, it might remain unused.
- **Insertion & Deletion**: Adding or removing elements in the middle requires shifting elements, which is slow.
- **Contiguous Memory**: Arrays require continuous memory allocation, which may not always be available.

### What is a Linked List?
A linked list is a data structure where elements (called **nodes**) are linked using pointers.
Each node has two parts:
1. **Data**: Stores the actual value.
2. **Next**: Stores the address of the next node.

### Representation of a Node
A node in a linked list looks like this:
```
[ Data | Address ] --> [ Data | Address ] --> [ Data | NULL ]
```
- The last node points to `NULL`, indicating the end of the list.
![image](https://github.com/user-attachments/assets/614c702b-b134-4a8a-b982-054af5c23fc8)

### Types of Linked Lists
1. **Singly Linked List**: Each node points to the next node.
2. **Doubly Linked List**: Each node has pointers to both previous and next nodes.
3. **Circular Linked List**: The last node points back to the first node.

### Implementing a Singly Linked List in Python
Let's implement a basic singly linked list in Python:

```python
class Node:
    def __init__(self, data):
        self.data = data  # Store data
        self.next = None  # Initialize next as NULL

class LinkedList:
    def __init__(self):
        self.head = None  # Initialize head as NULL

    def insert_at_end(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return
        temp = self.head
        while temp.next:
            temp = temp.next
        temp.next = new_node
    
    def display(self):
        temp = self.head
        while temp:
            print(temp.data, end=' -> ')
            temp = temp.next
        print('NULL')

# Example usage
ll = LinkedList()
ll.insert_at_end(10)
ll.insert_at_end(20)
ll.insert_at_end(30)
ll.display()
```

#### Explanation
- We create a `Node` class with `data` and `next`.
- We create a `LinkedList` class with a `head` pointer.
- `insert_at_end(data)`: Adds a new node at the end.
- `display()`: Prints the linked list.

**Output:**
```
10 -> 20 -> 30 -> NULL
```

### Advantages of Linked List
- **Dynamic Size**: No need to specify size in advance.
- **Efficient Insertions/Deletions**: No shifting required.
- **Memory Utilization**: No extra unused memory.

### Disadvantages of Linked List
- **Extra Memory**: Requires extra space for pointers.
- **Slower Access**: Cannot access elements using index directly.

### Conclusion
Linked lists overcome array limitations but have their own drawbacks. They are useful for scenarios where frequent insertions and deletions are required.

### Inbuilt `list` in Python-
- Python lists are not implemented as flexible linked lists.
- Underlying interpretation maps the list to an array:
  * Assign a fixed block when you create a list.
  * Double the size if the list overflows the array.
- Keep track of the last position of the list in the array:
  * `l.append()` and `l.pop()` are constant time, amortised — `O(1)`.
  * Insertion/deletion require time `O(n)`
- Effectively, Python lists behave more like arrays than lists.

#### The Numpy library provides arrays as a basic type:
```py
    import numpy as np
    zeromatrix = np.zeros(shape=(3,3))
    print(zeromatrix)

    **Output:**
    [[0. 0. 0.]
     [0. 0. 0.]
     [0. 0. 0.]]
```

## Dictionaries, Hash Functions, and HashMaps

### Why Not Use Arrays or Lists?
Arrays and lists have limitations when storing key-value pairs like names and roll numbers:
- **Index-based Access**: Arrays/lists use integer indices, so searching by name is inefficient.
- **Slow Lookup**: Searching an item requires iterating over the entire list.
- **No Direct Mapping**: We need a structure that efficiently maps keys to values.

### What is a Dictionary?
A **dictionary** (also known as a hashmap) is a data structure that stores `key-value` pairs. It allows efficient retrieval, insertion, and deletion of data.

#### Representation of a Dictionary
A dictionary in Python is represented as:
```python
{
    "Alice": 101,
    "Bob": 102,
    "Charlie": 103
}
```
`Each key(name) maps to a unique value (roll number)`

### Hash Functions & HashMaps
A **hash function** converts a key into an index in an array. This index stores the value, ensuring quick lookups.

#### Example Implementation of a Dictionary in Python
```python
class HashMap:
    def __init__(self):
        self.map = {}  # Using Python dictionary for simplicity

    def insert(self, key, value):
        self.map[key] = value
    
    def get(self, key):
        return self.map.get(key, "Key not found")
    
    def display(self):
        for key, value in self.map.items():
            print(f"{key} -> {value}")

# Example usage
students = HashMap()
students.insert("Alice", 101)
students.insert("Bob", 102)
students.insert("Charlie", 103)

students.display()
print(students.get("Alice"))
```

### Explanation
- We create a `HashMap` class using Python’s dictionary.
- `insert(key, value)`: Adds a key-value pair.
- `get(key)`: Retrieves the value for a key.
- `display()`: Prints all key-value pairs.

**Output:**
```
Alice -> 101
Bob -> 102
Charlie -> 103
101
```

## Advantages of Dictionaries (HashMaps)
- **Fast Lookup**: Searching is `O(1)` on average.
- **Efficient Insertion/Deletion**: No need to shift elements.
- **Flexible Keys**: Supports any immutable data type as keys.

## Disadvantages of Dictionaries (HashMaps)
- **Memory Overhead**: Uses extra space for hashing.
- **Collision Handling**: Needs strategies like chaining or open addressing.

### Conclusion
Dictionaries and hashmaps provide an efficient way to store and retrieve data using key-value pairs, overcoming the limitations of arrays and lists.


# WEEK 4:
# WEEK 5:
# WEEK 6:
# WEEK 7:
# WEEK 8:
# WEEK 9:
# WEEK 10:
# WEEK 11:
# WEEK 12:
