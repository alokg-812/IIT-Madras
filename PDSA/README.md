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
```
    a = 10
    b = 20
    s = a + b
    print(s)
```
**Complexity = `O(1)`**

Complexity for single loop
```
    s = 0
    for i in range(n):
    	s = s + 1
```
**Complexity = `O(n)`**

Complexity for nested two loop
```
    s = 0
    for i in range(n):
        for j in range(n):
            s = s + 1
```
**Complexity = `O(n²)`**

Complexity for nested three loop
```
    s = 0
    for i in range(n):
        for j in range(n):
            for k in range(n)
            	s = s + 1
```
**Complexity = `O(n³)`** 

Complexity for combination of all
```
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
```
    def factorial(n)
    	if (n == 0):
    		return 1
    	return n * factorial(n - 1)
```

Recurrence relation? `T(n) = T(n-1)+O(1) = 1+1+1...n times`

**Complexity `O(n)`**

 

Complexity for recursive solution
```
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


# WEEK 3:

# WEEK 4:
# WEEK 5:
# WEEK 6:
# WEEK 7:
# WEEK 8:
# WEEK 9:
# WEEK 10:
# WEEK 11:
# WEEK 12:
