def linear(P, Q, k):
    """
    A recursive function to determine if a list is scalar multiple of the other

    Arguments:
        P: list of integers
        Q: list of integers
        k: integer
    Return:
        result: bool
    """
    if len(P) != len(Q):
        return False
    
    if len(P) == 0:
        return True
        
    if P[0] != k*Q[0]:
        return False
    
    return linear(P[1:], Q[1:], k)
