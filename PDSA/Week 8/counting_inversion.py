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
