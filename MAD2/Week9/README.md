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
- ✅ Receive the request
- ✅ Validate input
- ✅ Dispatch the task to a worker or queue
- ✅ Return a quick response like “Processing…”
- ✅ Optionally update the user later

## Message Queues

### What is a Message Queue?
- A `message queue` is a communication system that allows different parts of an application (or multiple servers) to talk to each other by passing messages asynchronously.
- Instead of sending requests directly and waiting for a reply, one component **produces (publishes)** a message and another **consumes** it later.
- This decouples the sender and receiver, making the system **more scalable, fault-tolerant, and flexible**.

### Why do we need Message Queues?
* `Decoupling` → The sender doesn’t care who processes the task or when.
* `Scalability` → If traffic spikes, messages can wait in the queue until resources are available.
* `Fault tolerance` → If a server is down, messages stay in the queue until it comes back.
* `Batch processing` → Messages can be processed in bulk for efficiency.
* `Monitoring` → The queue itself can be monitored (e.g., how many tasks are pending).

### Communication Models

<img width="454" height="432" alt="image" src="https://github.com/user-attachments/assets/d4531b7c-4528-41ab-ac1b-a633a2a45afa" /> <br>
1. **Client–Server**: Client sends a request message, server processes it later.

<img width="502" height="511" alt="image" src="https://github.com/user-attachments/assets/d72ea46c-f169-4ee4-a36e-189083072c73" /> <br>
* In this image, the four different server may represent like One of them is a web server frontend, one of them is database, one of them is Handling Image recognition, and the last one is taking care of email transfers.
2. **Server–Server**: Multiple servers exchange messages (many-to-many).
   * Avoids too many direct point-to-point connections.
   * Some servers only **produce**, others only **consume**.

### Core Features
* **Asynchronous messaging** → Producer doesn’t block while waiting for consumer.
* **FIFO ordering** → Tasks processed in the order they were sent (first in, first out).
* **Dataflow processing** → The system reacts automatically when messages arrive.
* **Retry/failure handling** → If a consumer is busy/offline, the queue retries later.


### Variants of Messaging

1. *Message Queue (classic)*
   * Point-to-point → **Producer → Queue → Consumer**
   * Example: An email sending service.

2. *Publish/Subscribe (Pub/Sub)*
   * Producer publishes without knowing who reads.
   * Multiple consumers subscribe to the same channel.
   * Example: Notifications to many users.

3. *Message Bus*
   * Shared communication medium, like a hardware bus.
   * Many services talk over the same bus, addressable.

4. *APIs / Web Services*
   * Direct service-to-service calls. Faster but less resilient (no storage).

5. *Databases (used as message store)*
   * Messages stored in a DB table.
   * Not efficient for real messaging (lacks ordering & retry features).

### Message Brokers

<img width="573" height="533" alt="image" src="https://github.com/user-attachments/assets/9aec252d-301a-412a-bc2c-0c6ca7f98f25" />

- A **Message Broker** is the middle layer that manages the transfer of messages between producers and consumers.
* Ensures reliable delivery.
* Provides routing logic.
* Buffers tasks in a queue.

#### **🫴eg#:**
* **RabbitMQ (AMQP protocol)**
  * Full-featured, supports advanced routing.
* **Apache ActiveMQ**
* **Redis (Pub/Sub style)**
  * Very fast (in-memory).
  * Great for small, temporary messages.
  * But lacks persistence (messages lost if server restarts).

### Benefits of Message Queues
* **Scalability** → Add more consumers when workload increases.
* **Traffic handling** → Spikes don’t crash the system; messages wait until processed.
* **Monitoring** → Easy to track pending tasks.
* **Batch operations** → Process tasks in groups for efficiency.

### 🔹 Real-World Examples
* **Email sending**: User hits "Send", message is queued, email server processes later.
* **Order processing in e-commerce**: Orders are queued to handle high traffic during sales.
* **Notification systems**: Pub/Sub delivers notifications to many users.
* **Logging & analytics**: Logs are sent to a message queue for later batch processing.


✅ **In summary:**
Message queues are the backbone of asynchronous task handling. They **decouple senders and receivers**, make systems **scalable and fault-tolerant**, and allow **different communication patterns** like **point-to-point** and **pub/sub**. Tools like **RabbitMQ, Redis, and Celery** are popular implementations in Python ecosystems.


## Asynchronous Tasks with Celery
- Celery is a `Python library` for managing **asynchronous tasks**.
- It is built directly on the idea of **message queues** — Celery itself doesn’t handle messages, but it uses a **message broker** (like **RabbitMQ** or **Redis**) to pass tasks around.

### Why Celery?

Imagine your web server gets a user request like:

* Upload a large photo for face recognition
* Send thousands of emails
* Generate a complex report

👉 If the web server tries to do this **immediately**, the user will wait forever, and other users may get blocked.

Instead:

* The web server **puts the task into a message queue** (via Celery).
* A separate **Celery worker** process (or many workers) will later **pick up the task** from the queue and execute it.
* This way, the server can quickly respond to the user: *“Task received — we’ll notify you when it’s done.”*


## 2. Components of Celery (and how they map to Message Queues)

* **Producer (your web app / API server)**

  * When a request comes, it *produces a task* → pushes it into the **queue**.

* **Broker (Message Queue system: RabbitMQ / Redis)**

  * Stores the task until a worker is ready.
  * Acts as the **mailbox** between web server and worker.

* **Consumer/Worker (Celery workers)**

  * Listens to the queue.
  * When a task arrives, the worker **picks it up and executes it**.

* **Result Backend (Database/Cache)**

  * Stores the result of the task (e.g., success/failure, data output).
  * Lets you check later if the task is complete.

---

## 3. Flow of a Celery Task

1. **User action** → triggers task (e.g., "Send email").
2. **Web app (Flask/Django)** → hands task to **Celery** instead of doing it immediately.
3. **Celery** → pushes the task into a **message broker (queue)**.
4. **Celery workers** → pull tasks from the queue and execute them.
5. **Result backend** → stores the output (optional).

👉 This is literally the **producer → queue → consumer** model you saw in Message Queues.

---

## 4. Task Queues in Celery

* **FIFO by default** → tasks are handled in the order they arrive.
* Can also define **priorities** or **multiple queues** for different kinds of tasks.
* Multiple workers can share the load (scalability).

Example:

* Queue 1: Send emails
* Queue 2: Generate reports
* Queue 3: Process images

Each has dedicated workers → prevents one type of job from blocking others.

---

## 5. Benefits (from Message Queues → Celery)

* **Scalability** → Add more Celery workers when workload spikes.
* **Fault tolerance** → If a worker crashes, the task remains in the queue until retried.
* **Traffic spikes** → Queue absorbs sudden bursts of requests.
* **Monitoring** → Tools like Flower can show pending tasks in Celery queues.

---

## 6. Example (Connecting Message Queue & Celery)

Suppose you’re building a **social media app**:

🔹 Without Celery:

* User clicks “Send Friend Request”.
* Server updates DB + sends notification + sends email → User waits until all done.

🔹 With Celery (using queues):

* Server updates DB immediately.
* **Pushes notification + email tasks into Celery queue**.
* User instantly gets *“Friend request sent”*.
* Celery workers later process the email + push notification.

Here:

* **Producer** = Web server
* **Broker** = RabbitMQ/Redis (queue)
* **Consumer** = Celery workers

✅ **In summary:**
Celery is an **asynchronous task manager for Python** that **relies on message queues** to offload long-running work.

* Message Queues handle **communication + buffering**.
* Celery adds a **task execution layer** (workers, retries, results).
* Together, they enable **responsive, scalable web apps** that don’t block users.
