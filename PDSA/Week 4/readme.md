

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
