### Lecture 7.1: Balanced Search Trees
### Lecture 7.2: Greedy Algorithm - Interval Scheduling
<br>
#### 🧠 What is a Greedy Algorithm?

A `Greedy Algorithm` is a problem-solving technique that:
- Builds the solution `step-by-step`.
- Makes the `best local choice` at each step (locally optimal).
- `Never revisits` or changes earlier decisions.
- The hope is that these local choices lead to a `globally optimal` solution.

Greedy algorithms are simple and efficient, but they `only work when` the problem satisfies specific properties.

---

> ✅ **Core Properties for Greedy Algorithms to Work**
1. **Greedy Choice Property** – We can build the final solution by choosing the best option at each step.
2. **Optimal Substructure** – The problem’s solution contains solutions to subproblems.

---

#### 🍫 Easy Example: Coin Change Problem

**Problem:**  
Given coin denominations of ₹1, ₹2, ₹5, and ₹10, find the `minimum number of coins` needed to make ₹27.

**Greedy Strategy:**  
Always pick the `largest coin` less than or equal to the remaining amount.
```
# Step-by-Step:
- ₹27 remaining → take ₹10 → ₹17 left
- ₹17 → take ₹10 → ₹7 left
- ₹7 → take ₹5 → ₹2 left
- ₹2 → take ₹2 → ₹0 left ✅
```

**Result:**  
Coins used = `[10, 10, 5, 2]`  
Total coins = `4`

This is the `optimal solution`, and greedy works here.

---

## ❌ Where Greedy Fails: A Counterexample

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
🚫 Greedy fails here because the greedy choice didn’t lead to the best result.

---

### 🎯 Conclusion

Greedy algorithms are powerful **only when** the problem:
- Has `greedy choice property`
- Has `optimal substructure`

If these hold, greedy algorithms can give optimal solutions with *great efficiency*.

### Lecture 7.3: Greedy Algorithm - Minimizing Lateness
### Lecture 7.4: Greedy Algorithm - Huffman Coding
