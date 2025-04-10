## Divide & Conquer:

`Ques:` What is Divide and Conquer? <br>
`Ans:` Divide and Conquer is a problem-solving technique where:
* **Divide** the problem into smaller disjoint subproblems.
* **Conquer** each subproblem by solving it recursively.
* **Combine** the solutions of the subproblems to solve the original problem.

> Applications of Divide and Conquer

| Problem |	Description |
|---------|-------------|
| Merge Sort | Sorts an array by dividing it into halves and merging sorted halves. |
| Quicksort	| Picks a pivot and recursively sorts left and right subarrays. |
| Counting Inversions |	Count how far the array is from being sorted. |
| Closest Pair of Points | Efficiently find two closest points in a plane. |
| Integer Multiplication | Multiplies two large integers using Karatsuba's algorithm. |
| Quick Select | Finds the k-th smallest/largest element in an array. |


## Lecture 8.1 : Divide & Conquer- Counting Inversions


### What is an Inversion?

An `inversion` is a pair of indices `(i, j)` such that:
- `i < j`, and  
- `A[i] > A[j]`

This means the array is out of order at that position.  
The `total number of inversions` gives us an idea of how far the array is from being sorted.

#### ✅ Example:

![image](https://github.com/user-attachments/assets/90491a52-5b1e-46cd-9f32-6576efd0904a)

```python
A = [2, 4, 3, 1, 5]

# Inversions:
(2, 1)
(4, 3)
(4, 1)
(3, 1)
🔢 Total = 4 inversions
```

Approach: Divide and Conquer
> This uses a similar strategy to Merge Sort:
- **Divide** the array into halves.
- **Conquer** by recursively solving for each half.
- **Combine** by merging and counting inversions between left and right halves (cross-inversions).

* [Python program](https://github.com/alokg-812/IIT-Madras/blob/main/PDSA/Week%208/counting_inversion.py)
```python
L = [2, 4, 3, 1, 5]
print(inversionCount(L)[1])  # Output: 4
```
![image](https://github.com/user-attachments/assets/6668d61e-12b6-49ef-b6da-b5da811fcb4d)   <br>
*Given array*

![image](https://github.com/user-attachments/assets/e146a37d-a76b-46f6-9544-4558f31efa5f)
*The array is divided into two halves and both halves are sorted individually*

![image](https://github.com/user-attachments/assets/592f8752-f3bd-48ad-8935-38a691743c72)
*The array is sorted with the help of two halves*


## Lecture 8.2 : Divide & Conquer- Closest Pair of points

You are given `n` points on a 2D plane.  
The task is to `find the pair of points with the smallest Euclidean distance`.

### Naive Approach (Brute Force)
- Compute the distance between every possible pair.
- Time Complexity: `O(n^2)`

### Optimized Approach – Divide and Conquer
This algorithm improves time to `O(n log n)`:

### Idea:
1. `Sort` points by x-coordinate and y-coordinate.
2. `Divide` the set into two halves.
3. `Recurse` into each half.
4. `Merge`: Check across the dividing line using a narrow vertical strip of width `2 * delta` where `delta` is the minimum distance found so far.

### Recurrence:
```text
T(n) = 2T(n/2) + O(n)
```
### PseudoCode: `O(n log n)`
```text
def ClosestPair(Px, Py):
    if len(Px) <= 3:
        compute pairwise distances
        return closest pair and distance
    Construct (Qx, Qy), (Rx, Ry)
    (q1, q2, dQ) = ClosestPair(Qx, Qy)
    (r1, r2, dR) = ClosestPair(Rx, Ry)
    delta = min(dQ, dR)
    Construct Sy from Qy and Ry such that x within delta
    Check Sy for closer pairs
    return minimum of all three
```

[Python Code](https://github.com/alokg-812/IIT-Madras/blob/main/PDSA/Week%208/closest_pair_of_points.py)
```python
pts = [(2, 15), (40, 5), (20, 1), (21, 14), (1, 4), (3, 11)]
result = minDistance(pts)
print(result)
```
_Output:_
```yaml
4.12
```
Given Problem statement:
![image](https://github.com/user-attachments/assets/8a688000-fbbc-4000-b4fd-56f147079400)

![image](https://github.com/user-attachments/assets/7aeeea7f-6858-4b61-a97c-5b388ae7c6fb)
_sorting with respect to Px_

![image](https://github.com/user-attachments/assets/44b893c7-0326-401b-9e35-79e0f165889c)
_sorting with respect to Py_

![image](https://github.com/user-attachments/assets/09b7c2ea-93ef-49ef-a50f-33a1a9a42432)
![image](https://github.com/user-attachments/assets/f3b6d8ef-1c84-4140-88d8-95fdc32483b0)

_Output:`4.12`_
![image](https://github.com/user-attachments/assets/5f4f5829-019e-4015-a7a3-119a3366acfd)


## Lecture 8.3 : Divide & Conquer- Integer Multiplication
## Lecture 8.4 : Divide & Conquer- Recursion Trees
## Lecture 8.5 : Divide & Conquer- Quick Select
