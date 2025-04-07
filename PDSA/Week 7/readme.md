### Lecture 7.1: Balanced Search Trees
### Lecture 7.2: Greedy Algorithm - Interval Scheduling
<br>

![image](https://github.com/user-attachments/assets/6c08b3b4-fada-4cbf-8fdb-651e38807dbd)

#### What is a Greedy Algorithm?

A `Greedy Algorithm` is a problem-solving technique that:
- Builds the solution `step-by-step`.
- Makes the `best local choice` at each step (locally optimal).
- `Never revisits` or changes earlier decisions.
- The hope is that these local choices lead to a `globally optimal` solution.

Greedy algorithms are simple and efficient, but they `only work when` the problem satisfies specific properties.

---

>  **Core Properties for Greedy Algorithms to Work**
1. **Greedy Choice Property** – We can build the final solution by choosing the best option at each step.
2. **Optimal Substructure** – The problem’s solution contains solutions to subproblems.

---

#### Example: Coin Change Problem

**Problem:**  
Given coin denominations of ₹1, ₹2, ₹5, and ₹10, find the `minimum number of coins` needed to make ₹27.

**Greedy Strategy:**  
Always pick the `largest coin` less than or equal to the remaining amount.
```
# Step-by-Step:
- ₹27 remaining → take ₹10 → ₹17 left
- ₹17 → take ₹10 → ₹7 left
- ₹7 → take ₹5 → ₹2 left
- ₹2 → take ₹2 → ₹0 left
```

**Result:**  
Coins used = `[10, 10, 5, 2]`  
Total coins = `4`

This is the `optimal solution`, and greedy works here.

---

## Where Greedy Fails: A Counterexample

**Coins available:** ₹1, ₹3, ₹4  
**Target:** ₹6
```
# Greedy strategy:
- Take ₹4 → ₹2 left
- Take ₹1 → ₹1 left
- Take ₹1 → ₹0 left  
→ Total coins = 3

# Optimal strategy:
- Take ₹3 + ₹3  
→ Total coins = 2
```
Greedy fails here because the greedy choice didn’t lead to the best result.

---

### Conclusion

Greedy algorithms are powerful **only when** the problem:
- Has `greedy choice property`
- Has `optimal substructure`

If these hold, greedy algorithms can give optimal solutions with *great efficiency*.
## Interval Scheduling:
---

### What is Interval Scheduling?

Interval Scheduling is a classic `Greedy Algorithm problem` where we are given a set of tasks or jobs, each with a `start time` and an `end time`.

The goal is to **schedule as many non-overlapping jobs as possible** using a `single resource` (like a room, machine, or printer).

---

### Imagine of an IIT Madras 3D Printer

> IIT Madras has a `single` 3D printer, and multiple students want to use it for different durations.  
> The printer can only serve **one student at a time**, and everyone may not finish before their personal deadline.

**Objective:**  
Minimize `lateness` or, in some variations, `maximize the number of users served`.

---

### Problem Input

Each job has:
- A `start time s` & an `end time e` (or a deadline)

**Constraints**:
- Only one job can be running at a time.
- If two jobs overlap, only one can be chosen.

---

### Greedy Strategies Compared

| Strategy | Description | Optimal? |
|----------|-------------|----------|
| **Shortest Job First** | Schedule jobs in increasing order of duration. | ❌ |
| **Least Slack Time First** | Slack = deadline - duration. Schedule jobs with smaller slack first. | ❌ |
| **Earliest Finish Time First** | Schedule jobs in increasing order of end time. | ✅ |

---

#### Correct Greedy Strategy: Earliest Finish Time First

### Steps:

1. **Sort** all jobs by their **end times**.
2. **Pick** the job with the earliest end time.
3. **Skip** any job that overlaps with the previous job.
4. Repeat until all jobs are considered.

---

### Example: Given the jobs:

| Job | Start | End |
|-----|-------|-----|
| A   | 1     | 4   |
| B   | 3     | 5   |
| C   | 0     | 6   |
| D   | 5     | 7   |
| E   | 8     | 9   |
| F   | 5     | 9   |

➡️ After sorting by end time:

| Job | Start | End |
|-----|-------|-----|
| A   | 1     | 4   |
| B   | 3     | 5   |
| D   | 5     | 7   |
| E   | 8     | 9   |
| F   | 5     | 9   |
| C   | 0     | 6   |

 **Selected Jobs**:
- ✅ A
- ❌ B (overlaps with A)
- ✅ D
- ✅ E

 **Max Non-overlapping Jobs**: `3`

---

## Python Implementation

```python
# Interval Scheduling using Greedy Algorithm

def interval_scheduling(jobs):
    # Sort jobs by end time
    jobs.sort(key=lambda x: x[1])

    selected = []
    current_end = 0

    for job in jobs:
        start, end = job
        if start >= current_end:
            selected.append(job)
            current_end = end

    return selected

# Example jobs as (start, end)
jobs = [(1, 4), (3, 5), (0, 6), (5, 7), (8, 9), (5, 9)]

# Get the optimal schedule
optimal_schedule = interval_scheduling(jobs)

print("Selected non-overlapping jobs:")
for start, end in optimal_schedule:
    print(f"Start: {start}, End: {end}")
```
_Output:_
```yaml
Selected non-overlapping jobs:
Start: 1, End: 4
Start: 5, End: 7
Start: 8, End: 9
```

-> Time & Space Complexity

| Operation | Complexity |
|-----------|------------|
| Sorting jobs by end time | `O(n log n)` |
| Iterating over sorted jobs | `O(n)` |
| **Total Time Complexity** | ✅ `O(n log n)` |
| Space | `O(1)` (or `O(n)` if we store the selected jobs) |

- Applications of Interval Scheduling in Real life:
  * CPU job scheduling
  * Classroom allocations
  * Meeting room bookings
  * Ad slot placements
  * Bandwidth allocation
![image](https://github.com/user-attachments/assets/1fce043f-8eae-4846-a8e8-a8dbf94c036b)

### Lecture 7.3: Greedy Algorithm - Minimizing Lateness
### Lecture 7.4: Greedy Algorithm - Huffman Coding
