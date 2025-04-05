import sys, os, psycopg2

with open("name.txt", "r") as file:
    student_fname = file.read().strip()  # Remove newline/extra spaces

conn = psycopg2.connect(
    database=sys.argv[1],
    user=os.environ.get('PGUSER'),
    password=os.environ.get('PGPASSWORD'),
    host=os.environ.get('PGHOST'),
    port=os.environ.get('PGPORT')
)

cur = conn.cursor()

query = """
SELECT s.student_fname, d.department_name, s.dob
FROM students s
JOIN departments d ON s.department_code = d.department_code
WHERE s.student_fname = %s
"""

cur.execute(query, (student_fname,))
rows = cur.fetchall()

for row in rows:
    name = row[0]
    dept = row[1]
    year = row[2].year
    oe = "Even" if year % 2 == 0 else "Odd"
    print(f"{name},{dept},{oe}")

cur.close()
conn.close()
