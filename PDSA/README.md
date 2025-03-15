# ALGORITHMS:

# WEEK 1:

`revision of python language whatever taught in foundation level`

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

# Graphs

Graphs are mathematical structures used to model relationships between objects. A graph consists of **vertices (nodes)** and **edges (connections between nodes)**. Graphs are widely used in various fields, including computer science, social networks, transportation, and telecommunications.

![image](https://github.com/user-attachments/assets/1566a3ba-9a8e-4541-9301-8dbe9b44f45f)
`G = (V, E)`
`V = {A,B,C,D,E}`
`E = {e1,e2,e3,e4,e5} or {(A,B), (A,C), (B,C), (C,D), (C,E)}`


## 1. Visualization of Relationships as Graphs
Graphs can be categorized based on the type of relationships they represent:

- **Directed Graphs**: These graphs have edges with a direction, meaning a connection exists **from one node to another** but **not necessarily in the reverse direction**. 
  - Example: A teacher teaching multiple courses. The teacher is a **node**, and the courses are **nodes** connected by directed edges.
  
- **Undirected Graphs**: These graphs have edges without direction, meaning the relationship is **bidirectional**.
  - Example: A friendship network where two people are connected if they are friends. The friendship is mutual, making it an undirected graph.

![image](https://github.com/user-attachments/assets/0ac5f380-db31-4755-95db-7fbc65900355)


## 2. Reachability in Graphs
Reachability is a crucial concept in graphs that helps determine if there is a way to travel from one vertex to another.

- **Example**: Suppose there is no direct flight from **Madurai to Delhi**. A traveler must take one or more connecting flights to reach the destination.
- Important Questions:
  - **Is vertex `v` reachable from vertex `u`?** That is, can we get from one node to another using available edges?
  - **Among all possible ways, which is the shortest path from `u` to `v`?** Shortest path algorithms, like Dijkstra’s Algorithm, help find the optimal path.
  - **What are all the vertices reachable from `u`?** Finding all reachable vertices helps understand the connectivity of a graph.
  - **Are all vertices reachable from each other?** If every vertex is reachable from every other vertex, the graph is called a **strongly connected graph** (for directed graphs) or a **connected graph** (for undirected graphs).

## 3. Graph Colouring
Graph coloring is the process of assigning colors to vertices such that **no two adjacent vertices share the same color**.

- **Example**: Coloring all **Indian states** on a map so that no two adjacent states have the same color. This prevents confusion in distinguishing regions.
- **Key Concepts:**
  - **Minimum Color Set:** What is the **smallest number of colors** needed to color all vertices while maintaining the no-adjacent rule?
  - **Four Color Theorem:** For **planar graphs** (graphs that can be drawn on a plane without edges crossing), **four colors suffice** to color the graph.
  - **What about non-planar graphs?** Some graphs require more than four colors depending on their structure and complexity.

## 4. Vertex Cover
A **vertex cover** is a subset of vertices such that every edge in the graph has at least one of its endpoints in this subset.

- **Example**: Suppose we want to monitor **all roads in a city** using the fewest possible security cameras. Placing a camera at an intersection (vertex) covers all connected roads (edges). The goal is to find the **smallest subset of vertices** that ensures all edges are covered.

## 5. Independent Set
An **independent set** in a graph is a set of vertices such that **no two vertices in the set are adjacent** (i.e., they do not share an edge).

- **Example**: In a social network, an independent set could represent a group of people who are not directly connected.
- Finding the **largest independent set** in a graph is useful for applications like **wireless network scheduling** and **clustering problems**.

## 6. Matching
Matching is a subset of edges in which **no two edges share a common vertex**.

- **Example**: In a job hiring scenario, suppose we have a set of applicants and a set of job positions, where an edge represents a valid job assignment. A **maximum matching** ensures that as many applicants as possible get jobs without conflicts.

## Lecture 2: Working with Graphs

1. Graph Representation: _Avoid reflexive graphs_ (no self-loops).
2. Computing with Adjacency Matrix

_Adjacency Matrix Representation:_
```python
[[0, 1, 1, 0, 0],
 [0, 0, 0, 1, 1],
 [0, 0, 0, 1, 1],
 [0, 0, 0, 0, 1],
 [0, 0, 0, 0, 0]]
```
* **Key points:**
  * `AMat[i, j] == 1` means there is an edge from i to j.
  * Symmetric Matrix for undirected graphs.

* Finding Neighbours:
```python
def neighbours(AMat, i):
    nbrs = []
    rows, cols = AMat.shape
    for j in range(cols):
        if AMat[i, j] == 1:
            nbrs.append(j)
    return nbrs
```
Example: `neighbours(A, 7) # Output: [4, 8]`

3. Checking Reachability

Example: Finding if Delhi (0) is reachable from Madurai (9).

Challenges: Avoiding infinite loops (e.g., 9 → 8 → 9 → 8 ...).

Graph Traversal Algorithms:

Breadth-First Search (BFS) - Marks nodes in layers.

Depth-First Search (DFS) - Explores paths deeply before backtracking.

4. Adjacency List Representation

Graph Representation Using Dictionary:

V = [0,1,2,3,4]
E = [(0, 1), (0, 2), (1, 3), (1, 4), (2, 4), (2, 3), (3, 4)]
size = len(V)
AList = {}
for i in range(size):
    AList[i] = []
for (i,j) in E:
    AList[i].append(j)
print(AList)

Output Adjacency List:

{0: [1, 2], 1: [3, 4], 2: [4, 3], 3: [4], 4: []}

Lecture 3: Breadth-First Search (BFS)

Introduction to BFS:

Explores graph level by level.

Uses Queue (FIFO) structure.

Applications:

Finding shortest paths in an unweighted graph.

Checking connectivity in a graph.



---


# WEEK 5:

# Shortest Paths in Weighted Graphs

## Weighted Graphs

A weighted graph assigns a numerical value (weight) to each edge, representing distance, cost, or another relevant metric.

- **Graph Representation:**
  - **Adjacency Matrix:** A 2D array where `matrix[i][j]` stores the weight of the edge from vertex `i` to `j`.
  - **Adjacency List:** A list of lists, where `list[i]` stores pairs `(neighbor, weight)` for each connected vertex.

```cpp
// Example: Adjacency Matrix Representation
#include <iostream>
using namespace std;
const int INF = 1e9; // Representation of no edge

int main() {
    int graph[4][4] = {{0, 3, INF, 7},
                       {3, 0, 2, INF},
                       {INF, 2, 0, 1},
                       {7, INF, 1, 0}};
    
    // Display Graph
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            cout << (graph[i][j] == INF ? "INF" : to_string(graph[i][j])) << " ";
        }
        cout << endl;
    }
    return 0;
}
```

## Shortest Paths in Weighted Graphs

- BFS computes shortest paths in **unweighted graphs** based on the number of edges.
- In **weighted graphs**, shortest paths are determined by the sum of edge weights.

### Single Source Shortest Paths

Finds the shortest path from a fixed vertex to all others. Applications:
- **Transportation networks** (e.g., routing packages from a hub to destinations).
- **Navigation systems** (e.g., finding the shortest driving route).

### All Pairs Shortest Paths

Finds the shortest paths between every pair of vertices. Applications:
- **Optimizing airline routes.**
- **Analyzing network latencies.**

## Negative Edge Weights

- **Example Use Case:**
  - Roads where traffic congestion increases cost (positive weight).
  - Routes with toll discounts (negative weight).
- **Problem with Negative Cycles:**
  - If a cycle has a negative total weight, the path cost can be reduced indefinitely.

## Dijkstra's Algorithm: Single Source Shortest Path

- **Works for:** Graphs with **non-negative** weights.
- **Does NOT work for:** Graphs with **negative** weight edges.
- **Uses:** Priority Queue to pick the next vertex with the smallest known distance.
- **Time Complexity:** O(n²) with a naive approach, O((n + m) log n) with a priority queue.

```cpp
// Dijkstra's Algorithm using Priority Queue
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int INF = 1e9;
vector<pair<int, int>> adj[1000];

void dijkstra(int src, int n) {
    vector<int> dist(n, INF);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    dist[src] = 0;
    pq.push({0, src});

    while (!pq.empty()) {
        int d = pq.top().first;
        int u = pq.top().second;
        pq.pop();

        if (d > dist[u]) continue;

        for (auto [v, w] : adj[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }
}
```

## Bellman-Ford Algorithm: Single Source Shortest Path with Negative Weights

- **Works for:** Graphs with **negative edges** (but no negative cycles).
- **Time Complexity:** O(mn), where `m` is the number of edges and `n` is the number of vertices.

```cpp
// Bellman-Ford Algorithm
#include <iostream>
#include <vector>
using namespace std;
const int INF = 1e9;

struct Edge {
    int u, v, w;
};

void bellmanFord(int n, int src, vector<Edge> &edges) {
    vector<int> dist(n, INF);
    dist[src] = 0;

    for (int i = 0; i < n - 1; i++) {
        for (Edge e : edges) {
            if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                dist[e.v] = dist[e.u] + e.w;
            }
        }
    }
}
```

## Floyd-Warshall Algorithm: All Pairs Shortest Paths

- **Time Complexity:** O(n³).
- **Use Case:** Computing shortest paths between all pairs of nodes.

```cpp
// Floyd-Warshall Algorithm
#include <iostream>
using namespace std;
const int INF = 1e9;
int graph[100][100];

void floydWarshall(int n) {
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][k] != INF && graph[k][j] != INF)
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
            }
        }
    }
}
```

## Minimum Cost Spanning Tree (MCST)

### Prim’s Algorithm

- **Grows a tree from an arbitrary starting vertex.**
- **Similar to Dijkstra’s Algorithm,** but updates distances differently.
- **Time Complexity:** O(n²), or O(m log n) with priority queues.

```cpp
// Prim's Algorithm
#include <iostream>
#include <vector>
#include <queue>
using namespace std;
const int INF = 1e9;
vector<pair<int, int>> adj[1000];

void prim(int n) {
    vector<int> key(n, INF), parent(n, -1);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, 0});
    key[0] = 0;

    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        for (auto [v, weight] : adj[u]) {
            if (weight < key[v]) {
                key[v] = weight;
                pq.push({key[v], v});
                parent[v] = u;
            }
        }
    }
}
```

### Kruskal’s Algorithm

- **Sort edges by weight and add edges without forming a cycle.**
- **Uses Union-Find to detect cycles.**
- **Time Complexity:** O(m log n).

```cpp
// Kruskal's Algorithm
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
struct Edge { int u, v, w; };
vector<Edge> edges;

bool cmp(Edge a, Edge b) { return a.w < b.w; }

void kruskal(int n) {
    sort(edges.begin(), edges.end(), cmp);
}
```

# WEEK 6:
# WEEK 7:
# WEEK 8:
# WEEK 9:
# WEEK 10:
# WEEK 11:
# WEEK 12:
