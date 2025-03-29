# install pyscopg2
# create connection
# creator cursor
# execute the query
# close the cursor
# close connection

# Step 1: Installing psycopg2(using pip install psycopg2 on terminal)
import psycopg2
import os, sys


# Step 2: Creating Connection
conn = None

conn = psycopg2.connect(
    database = "test_db",
    user = "postgres",
    password = "admin", 
    host = "localhost", 
    port = "5432"
)

print("connected!")

