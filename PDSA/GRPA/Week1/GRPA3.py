# odd datatype in list

def odd_one(ls):
    n = len(ls)
    inn=[]
    floo = []
    st=[]
    boo = []
    for i in range(n):
        if(type(ls[i]) == int):
            inn.append(ls[i])
        
        elif(type(ls[i]) == float):
            floo.append(ls[i])
        
        elif(type(ls[i]) == str):
            st.append(ls[i])
        
        elif(type(ls[i]) == bool):
            boo.append(ls[i])
    
    if(len(inn) == 1):
        return "int"
    elif(len(floo) == 1):
        return "float"
    elif(len(st) == 1):
        return "str"
    else:
        return "bool"

print(odd_one(eval(input().strip())))
