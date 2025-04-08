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

```python
def mergeAndCount(A, B):
    (m, n) = (len(A), len(B))
    (C, i, j, k, count) = ([], 0, 0, 0, 0)
    
    while k < m + n:
        if i == m:
            C.append(B[j])
            j += 1
        elif j == n:
            C.append(A[i])
            i += 1
        elif A[i] < B[j]:
            C.append(A[i])
            i += 1
        else:
            C.append(B[j])
            j += 1
            count += m - i  # All remaining elements in A are greater
        k += 1
        
    return (C, count)

def inversionCount(A):
    n = len(A)
    if n <= 1:
        return (A, 0)
    (L, countL) = inversionCount(A[:n // 2])
    (R, countR) = inversionCount(A[n // 2:])
    (B, countB) = mergeAndCount(L, R)
    return (B, countL + countR + countB)

# Example usage
L = [2, 4, 3, 1, 5]
print(inversionCount(L)[1])  # Output: 4
```
![image](https://github.com/user-attachments/assets/e146a37d-a76b-46f6-9544-4558f31efa5f)
*The array is divided into two halves and both halves are sorted individually*

![image](https://github.com/user-attachments/assets/592f8752-f3bd-48ad-8935-38a691743c72)
*The array is sorted with the help of two halves*


## Lecture 8.2 : Divide & Conquer- Closest Pair of points
## Lecture 8.3 : Divide & Conquer- Integer Multiplication
## Lecture 8.4 : Divide & Conquer- Recursion Trees
## Lecture 8.5 : Divide & Conquer- Quick Select
