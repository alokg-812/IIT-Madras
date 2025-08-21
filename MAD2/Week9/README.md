# Week 9 Asynchronous Tasks
The problem of how to schedule/execute asynchronous tasks that was needed to be part of an overall web application

Before getting into the detail of how asynchronous part is done, let's have a look of how web servers work and why we need to look for different ways by which we have need to execute long running tasks.

## Web Servers
A web server is a software that:
* Listens for HTTP requests from clients (like browsers).
* Processes the request (serving an HTML page, data, or file).
* Sends back an HTTP response.

### Blocking vs Threaded Servers

#### Blocking Server (Flask non-threaded mode)
* Only `one request` is processed at a time.
* New requests must `wait` until the current one is finished.
* Example use: Testing or minimal apps.
* 🚫 Bad for real-world web apps with multiple users.
> **🫴eg#01:** If one user uploads a big file, **all other users are blocked**. <br>
> [Code: non_threaded.py](https://github.com/alokg-812/IIT-Madras/blob/main/MAD2/Week9/non_threaded_mode.py) <br>
<img width="734" height="997" alt="image" src="https://github.com/user-attachments/assets/3fa20b3d-becb-4dbe-bcf8-bc9a98cac562" /> <br>
> _The above image shows that once a command is done, then only the next command is starting its execution._ 


#### Threaded Server (Flask default mode)
* Flask runs in **threaded mode by default**`(app.run(threaded=True))`.
* Every request spawns a _new thread_.
* Threads run concurrently.
* Requests are **not blocked** by each other (to some extent).
> This improves performance, but too many threads can overload memory/CPU. <br>
> [Code: threaded.py](https://github.com/alokg-812/IIT-Madras/blob/main/MAD2/Week9/threaded_mode.py) <br>
<img width="689" height="983" alt="image" src="https://github.com/user-attachments/assets/5a125c75-841c-493e-8935-0c73f178ed1e" /> <br>
> _The above image shows that once a command is send, it start executing that command and taking new commands simultaneously._ 


### Key Concepts: Concurrency vs Parallelism
| Concept       | Meaning                                                                |
| ------------- | ---------------------------------------------------------------------- |
| `Concurrency` | Multiple tasks *appear* to run at the same time (via thread switching) |
| `Parallelism` | Multiple tasks run *truly simultaneously* (requires multi-core CPU)    |
> **_Threads are `concurrent`, parallelism depends on hardware._**

### Problem with Long-Running Tasks

Let’s say an app does face recognition when a photo is uploaded.

* That process is **CPU intensive**.
* Takes **a few seconds or more** to finish.
* If handled directly by Flask:
  * It will **block** the current thread.
  * Other users may experience **slowdowns** or **timeouts**.
  * Too many requests will **overload the server**.


### What Should Web Servers Actually Do?
A web server should **not** do:
  * Heavy computation (e.g., machine learning, face detection).
  * Long-running tasks (e.g., sending thousands of emails).
  * File processing or API polling.

Instead, a web server should:
✅ Receive the request
✅ Validate input
✅ Dispatch the task to a worker or queue
✅ Return a quick response like “Processing…”
✅ Optionally update the user later


## Message Queues

## Asynchronous Tasks with Celery

## 
