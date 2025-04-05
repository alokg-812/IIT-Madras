import psycopg2, os, sys

file = open("team.txt", "r")
x = file.read()

conn = psycopg2.connect(
    database = sys.argv[1], user = os.environ.get('PGUSER'), password = os.environ.get('PGPASSWORD'),host = os.environ.get('PGHOST'), port = os.environ.get('PGPORT')
)
    
cur = conn.cursor()
query = "select playground from teams where team_id = '%s' " %x
cur.execute(query)
res = cur.fetchall()

for i in res:
    print(i[0])

conn.close()
