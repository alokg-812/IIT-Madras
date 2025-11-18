def steps(n):
    """
    A recursive function to compute the number of ways to ascend steps 

    Argument:
        n: integer
    Return:
        result: integer
    """
    
    if n==0 or n==1 :
        return 1
    elif n==2:
        return 2
    elif n==3:
        return 4
    
    return steps(n-1) + steps(n-2) + steps(n-3)
