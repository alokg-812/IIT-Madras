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
