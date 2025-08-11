# Week 9 Asynchronous Tasks
The problem of how to schedule | execute asynchronous tasks that was needed to be part of an overall web application

Before getting into the detail of how asynchronous part is done, let's have a look of how web servers work and why we need to look for different ways by which we have need to execute long running tasks.

## Web Servers
A web server is a software that:
* Listens for HTTP requests from clients (like browsers).
* Processes the request (serving an HTML page, data, or file).
* Sends back an HTTP response.

### 🔹 2. Basic Working of a Simple HTTP Server

Let’s break it into steps:

| Step | Action                                                         |
| ---- | -------------------------------------------------------------- |
| 1️⃣  | Open a port (usually 80 or 5000) to **listen** for connections |
| 2️⃣  | Wait for a client to connect                                   |
| 3️⃣  | When a request arrives, read the **HTTP request**              |
| 4️⃣  | **Process the request** (example: fetch a file, run a script)  |
| 5️⃣  | Send back an **HTTP response**                                 |
| 6️⃣  | Go back to step 2 to handle next request                       |

> ❗ In this mode, the server handles one request at a time if no concurrency is involved.

---

### 🔹 3. Blocking vs Threaded Servers

#### 🔸 Blocking Server (Flask non-threaded mode)

* Only **one request** is processed at a time.
* New requests must **wait** until the current one is finished.
* Example use: Testing or minimal apps.
* 🚫 Bad for real-world web apps with multiple users.

> Example: If one user uploads a big file, **all other users are blocked**.

#### 🔸 Threaded Server (Flask default mode)

* Flask runs in **threaded mode by default**.
* Every request spawns a **new thread**.
* Threads run concurrently.
* Requests are **not blocked** by each other (to some extent).

> This improves performance, but too many threads can overload memory/CPU.

---

### 🔹 4. Key Concepts: Concurrency vs Parallelism

| Concept         | Meaning                                                                |
| --------------- | ---------------------------------------------------------------------- |
| **Concurrency** | Multiple tasks *appear* to run at the same time (via thread switching) |
| **Parallelism** | Multiple tasks run *truly simultaneously* (requires multi-core CPU)    |

Flask’s threaded mode offers **concurrency**, not true parallelism.

---

### 🔹 5. Problem with Long-Running Tasks

Let’s say your app does face recognition when a photo is uploaded.

* That process is **CPU intensive**.
* Takes **a few seconds or more** to finish.
* If handled directly by Flask:

  * It will **block** the current thread.
  * Other users may experience **slowdowns** or **timeouts**.
  * Too many requests will **overload the server**.

---

### 🔹 6. What Should Web Servers Actually Do?

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

---

## 📝 NOTES FOR REVISION / EXAM

---

### 🔸 Web Server Basics

* Listens on a port (like 80 or 5000)
* Accepts HTTP requests from clients
* Sends back HTTP responses (HTML, JSON, files)

---

### 🔸 Flask Server Modes

| Mode     | Description                     | Pros                   | Cons                                    |
| -------- | ------------------------------- | ---------------------- | --------------------------------------- |
| Blocking | Processes one request at a time | Simple                 | Other users must wait                   |
| Threaded | Each request in separate thread | Handles multiple users | Can overload server if too many threads |

---

### 🔸 Key Definitions

* **Blocking**: Server waits until task finishes before handling next request.
* **Threading**: Creates separate threads to allow concurrent handling of multiple requests.
* **Concurrency**: Tasks progress simultaneously but not at the same instant.
* **Parallelism**: Tasks run at the same time on different cores.

---

### 🔸 When Not to Use Flask Directly for a Task

Examples of long-running or blocking tasks:

* Image processing (face recognition)
* Large file uploads
* Sending emails to a large list
* Generating reports
* Crawling external APIs

---

### 🔸 Best Practices

* Use Flask only for quick response logic.
* Offload heavy tasks to background workers (like Celery).
* Use messaging systems (like Redis, RabbitMQ) to pass tasks to workers.
* Always consider user experience – don’t make users wait for long operations.

---

## ✅ Summary in One Sentence

> A web server like Flask should **only handle user requests and responses quickly**, and must **offload long or resource-intensive tasks** to background workers or async systems.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Message Queues

## Asynchronous Tasks with Celery

## 