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

IIT Madras has a `single 3D printer` that multiple users need to access.

Each request has:
- A **processing time** (`p_i`)
- A **deadline** (`d_i`)

**Goal:** Minimize the `maximum lateness` across all jobs.

### What is Lateness?

For each job:

- `finish_time` = when it completes
- `lateness` = `max(0, finish_time - deadline)`

We aim to `schedule jobs` such that the `maximum lateness` among all jobs is minimized.

### Failed Greedy Strategies

| Strategy                        | Optimal? |
|--------------------------------|----------|
| Shortest Processing Time First | ❌       |
| Smallest Slack First           | ❌       |

### Correct Strategy: Earliest Deadline First (EDF)

Sort jobs by `increasing deadline`, and execute them in that order.


#### Example

| Job | Processing Time (`p`) | Deadline (`d`) |
|-----|------------------------|----------------|
| A   | 4                      | 10             |
| B   | 2                      | 6              |
| C   | 3                      | 8              |

**Step-by-step**:

1. Sort by deadlines ⇒ B (6), C (8), A (10)
2. Compute finish times:
   - B: `0 + 2 = 2`
   - C: `2 + 3 = 5`
   - A: `5 + 4 = 9`
3. Compute lateness:
   - B: `max(0, 2 - 6) = 0`
   - C: `max(0, 5 - 8) = 0`
   - A: `max(0, 9 - 10) = 0`

`Maximum lateness = 0`

#### Python Implementation

```python
# Minimizing Lateness using Greedy (Earliest Deadline First)

def minimize_lateness(jobs):
    # Sort jobs by deadline
    jobs.sort(key=lambda x: x[1])  # x = (processing_time, deadline)

    current_time = 0
    max_lateness = 0
    schedule = []

    for p, d in jobs:
        finish_time = current_time + p
        lateness = max(0, finish_time - d)
        max_lateness = max(max_lateness, lateness)
        schedule.append((p, d, finish_time, lateness))
        current_time = finish_time

    return schedule, max_lateness

# Example jobs: (processing_time, deadline)
jobs = [(4, 10), (2, 6), (3, 8)]

schedule, max_lateness = minimize_lateness(jobs)

print("Job schedule with finish times and lateness:")
for p, d, f, l in schedule:
    print(f"Processing: {p}, Deadline: {d}, Finish: {f}, Lateness: {l}")

print(f"\nMaximum Lateness: {max_lateness}")
```
_Output:_
```yaml
Job schedule with finish times and lateness:
Processing: 2, Deadline: 6, Finish: 2, Lateness: 0
Processing: 3, Deadline: 8, Finish: 5, Lateness: 0
Processing: 4, Deadline: 10, Finish: 9, Lateness: 0

Maximum Lateness: 0
```
> Time & Space Complexity

| Task | Complexity |
|-----------|------------|
| Sorting jobs | `O(n log n)` |
| Processing jobs | `O(n)` |
| **Total Time Complexity** | ✅ `O(n log n)` |
| Space | `O(n)` |


> Real-World Applications-
 * Assignment submission schedulers
 * Print queue optimization
 * Manufacturing with delivery deadlines
 * Job scheduling in operating systems

> Summary
  * Greedy Strategy: Earliest Deadline First
  * Ensures minimum possible maximum lateness
  * Works perfectly for single-machine deadline-sensitive scheduling


### Lecture 7.4: Greedy Algorithm - Huffman Coding

Huffman Coding is a greedy algorithm used for `optimal prefix code generation` — perfect for `data compression` (like zip files, MP3, etc.).

### Key Features:
- `Greedy Strategy`: Always merge the two lowest frequency nodes.
- `No Backtracking`: Once a node is merged, it's not revisited.
- `Prefix-Free Encoding`: No code is a prefix of another, ensuring unambiguous decoding.


### Algorithm Steps

1. Create a priority queue and insert all characters with their frequencies.
2. While the size of the queue is more than 1:
   - Extract two nodes with the lowest frequency.
   - Merge them into a new node.
   - Insert the new node back into the queue.
3. The final node is the **root** of the Huffman Tree.
4. Traverse the tree to assign binary codes (left = 0, right = 1).

**Algorithm:**
1. Calculate the frequency of each character in the string.
2. Sort the characters in increasing order of the frequency.
3. Make each unique character as a leaf node.
4. Create an empty node z. Assign the minimum frequency to the left child of z and assign the second minimum frequency to the right child of z. Set the value of the z as the sum of the above two minimum frequencies.
5. Remove these two minimum frequencies from Q and add the sum into the list of frequencies.
6. Insert node z into the tree.
7. Repeat steps 3 to 5 for all the characters.
8. For each non-leaf node, assign 0 to the left edge and 1 to the right edge.
 
![image](https://github.com/user-attachments/assets/769d91c5-fe4b-4e58-9223-d882e5c8c5dc)

#### Python Implementation

```python
import heapq

class Node:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    # Define less than for priority queue (min-heap)
    def __lt__(self, other):
        return self.freq < other.freq

def build_huffman_tree(freq_map):
    heap = [Node(char, freq) for char, freq in freq_map.items()]
    heapq.heapify(heap)

    while len(heap) > 1:
        node1 = heapq.heappop(heap)
        node2 = heapq.heappop(heap)

        merged = Node(None, node1.freq + node2.freq)
        merged.left = node1
        merged.right = node2

        heapq.heappush(heap, merged)

    return heap[0]  # Root of the Huffman tree

def generate_codes(node, current_code="", code_map={}):
    if node is None:
        return

    if node.char is not None:
        code_map[node.char] = current_code
        return

    generate_codes(node.left, current_code + "0", code_map)
    generate_codes(node.right, current_code + "1", code_map)

    return code_map

# Example usage
frequencies = {'a': 5, 'b': 9, 'c': 12, 'd': 13, 'e': 16, 'f': 45}
root = build_huffman_tree(frequencies)
codes = generate_codes(root)

print("Huffman Codes:")
for char in sorted(codes):
    print(f"{char}: {codes[char]}")
```
![image](https://github.com/user-attachments/assets/93c57ca4-4ebe-4b20-a750-b965c95b41e1)

```yaml
Huffman Codes:
a: 1100
b: 1101
c: 100
d: 101
e: 111
f: 0
```


> Time & Space Complexity

| Operation | Complexity |
|-----------|------------|
| Heap Operations | `O(log k)` |
| Total Steps | `O(k log k)` |

> Real-world Applications
 * Text compression (ZIP, GZIP)
 * Image compression (JPEG, PNG)
 * Audio formats (MP3)
 * Video codecs (MPEG, H.264)


