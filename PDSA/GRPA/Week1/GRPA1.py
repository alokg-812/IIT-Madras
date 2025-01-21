# minimum P difference


def find_Min_Difference(L, P):
    L = sorted(L)  # Sort the list and assign it back to L
    ans = []
    i = 0
    j = P - 1
    while j < len(L):
        ans.append(L[j] - L[i])
        i += 1
        j += 1
    return min(ans)

L = eval(input().strip())
P = int(input())
print(find_Min_Difference(L, P))
