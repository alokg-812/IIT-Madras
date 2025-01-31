import numpy as np
zeromatrix = np.zeros(shape=(3,3))
print(zeromatrix)

onematrix = np.ones(shape=(3,3))
print(onematrix)

# matrix operations:
a = [1,3,5,7]
b = [2,4,6,8]
c = 3*a + b
print(c)

# matrix multiplication inbuilt:
a = [[1,0], [1,1]]
b = [[0,1], [1,0]]
print(np.dot(a,b))
print(np.matmul(a,b))