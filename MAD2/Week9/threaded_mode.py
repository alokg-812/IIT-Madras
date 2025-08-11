from flask import Flask
app = Flask(__name__)
import time

x = 1

@app.route('/')
def hello():
    global x
    x = x+1
    print(time.asctime())
    time.sleep(2)
    return 'Hellu Buddy, '+ str(x) + time.asctime() + '\n'

if __name__ == '__main__' :
    app.run(threaded=True, host='0.0.0.0')