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
## Lecture 8.3 : Divide & Conquer- Integer Multiplication
## Lecture 8.4 : Divide & Conquer- Recursion Trees
## Lecture 8.5 : Divide & Conquer- Quick Select
