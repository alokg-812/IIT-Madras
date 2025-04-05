For the database connection, use the following connection string variables:
```
database = sys.argv[1]	//name of the database is obtained from the command line argument
user = os.environ.get('PGUSER') 
password = os.environ.get('PGPASSWORD') 
host = os.environ.get('PGHOST')
port = os.environ.get('PGPORT')
```
![image](https://github.com/user-attachments/assets/1bf67b83-c564-45eb-a2cd-d246b727aa91)

## Problem Statement:
-> write a Python program to print the `playground` of the given team id. team_id is given in a file named `team.txt` resides in the same folder as python program file.* The output of the python program is only playground name.
* For example, if the team_id is `T0002`
* Then output must be `Villa Park` only
