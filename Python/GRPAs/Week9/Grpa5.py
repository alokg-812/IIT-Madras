def ancestry(P, present, past):
    """
    A recursive function to compute the sequence of ancestors of person

    Arguments:
        P: dict, key and value are strings
        present: string
        past: string
    Return:
        result: list of strings
    """
    
    if present == past:
        return [present]
        
    if present not in P:
        return []
        
    ancestors = []
    parent = P[present]
    
    ancestor_path = ancestry(P, parent, past)
    
    if ancestor_path:
        ancestors = [present] + ancestor_path
        
    return ancestors
