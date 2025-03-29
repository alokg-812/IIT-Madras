import psycopg2
import os, sys

def L5(x):
    return 2000 + 5*x + 5
    
with open("number.txt", "r") as file:
    x = int(file.read().strip())
    
year = L5(x)

database = sys.argv[1]	# name of the database is obtained from the command line argument
user = os.environ.get('PGUSER') 
password = os.environ.get('PGPASSWORD') 
host = os.environ.get('PGHOST')
port = os.environ.get('PGPORT')

conn = psycopg2.connect(
    dbname = database, user = user,password = password, host = host, port = port
)
cursor = conn.cursor()

query = "select ISBN_no from book_catalogue where year = %s"
cursor.execute(query, (year, ))

books = cursor.fetchall()
for book in books:
    print(book[0])

cursor.close()
conn.close()
