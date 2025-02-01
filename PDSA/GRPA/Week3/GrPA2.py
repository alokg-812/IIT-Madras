def EvaluateExpression(exp):
    st = []
    lst = exp.split()
    for i in lst:
        if i not in ['+', '-', '*', '/', '**']:
            st.append(float(i))
        else:
            b = st.pop()
            a = st.pop()
            if i == '+':
                res = a + b
            elif i == '-':
                res = a - b
            elif i == '*':
                res = a * b
            elif i == '/':
                res = a / b
            elif i == '**':
                res = a ** b
            st.append(res)
    return st.pop()

print(float(EvaluateExpression(input())))
