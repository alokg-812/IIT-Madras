import psycopg2

try:
    conn = psycopg2.connect(
        dbname="testdb",
        user="postgres",
        password="Test@1234",
        host="localhost",
        port="5432"
    )
    print("Connected successfully")

    # Create a cursor object
    cur = conn.cursor()

    # Execute a test query
    cur.execute("SELECT version();")
    print("PostgreSQL version:", cur.fetchone())

    # Close the connection
    cur.close()
    conn.close()

except psycopg2.OperationalError as e:
    print("Connection failed:", e)
