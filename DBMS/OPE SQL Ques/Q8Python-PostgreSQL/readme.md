* For the database connection, use the following connection string variables:
```
database = sys.argv[1]	//name of the database is obtained from the command line argument
user = os.environ.get('PGUSER') 
password = os.environ.get('PGPASSWORD') 
host = os.environ.get('PGHOST')
port = os.environ.get('PGPORT')
```

### Problem Statement:
![image](https://github.com/user-attachments/assets/ed7466fb-27f5-4991-baed-27d0f3b48046)

- Write a Python program to print the `ISBN numbers` of books which are published in a given year.
Here, the year is obtained as the value of function `L(x)` (given after the sample output) at `x`.
You have to read the value of x from the input file `number.txt`, and use it to find the value of `L(x)`.
Your program must assume that the file `number.txt` resides in the same folder as your Python program.

- You have to iterate through the list and print each value separately as shown in the output below: 

```
9789352921171
9789351343202
9789353333380
```
The line function is given below:
`L5(x) = 2000 + 5*x + 5`
