## 📝 Week 10 Java Notes: Concurrency (Threads and Processes) 🏃

This lecture introduces **Concurrent Programming** in Java, focusing on the concepts of **Processes** and **Threads** to enable parallel execution within an application.

-----

### 1\. Concurrent Programming Concepts 🔄

**Concurrent Programming** involves managing multiple computations that run at the same time, either truly simultaneously (on multiple cores) or interleaved (on a single core).

#### Multiprocessing

  * **Definition:** A single processor executes several computations **"in parallel"** (logically).
  * **Mechanism:** Achieved via **Time-slicing**, where the processor rapidly switches between different computations, sharing access and giving the illusion of simultaneous execution.

#### Logically Parallel Actions

Concurrency is essential for applications to remain responsive while performing long-running tasks:

  * **Example:** In a browser, the **User-interface (UI)** runs in parallel with a **network access (download)** task.
  * The UI thread can respond to a user clicking **"Stop"** to terminate the network download, demonstrating two actions running concurrently within the same application.

-----

### 2\. Processes vs. Threads 💡

| Feature | Process | Thread |
| :--- | :--- | :--- |
| **Isolation** | Has a **private set of local variables** and memory. | Operates on **same local variables** (shared memory). |
| **Communication**| Communication is complex (e.g., inter-process communication). | Communication is easy via **shared memory**. |
| **Context Switch** | Involves saving and loading the complete state, making it **heavyweight**. | Requires saving less state, making it **easier and lightweight**. |
| **Scope** | Typically represents a whole application instance. | Represents an independent path of execution **within a process**. |

**Note:** In this context, the terms "process" and "thread" are used somewhat **interchangeably** when discussing general concurrent execution paths.

-----

### 3\. Shared Variables and Race Conditions 🤝

Concurrent execution paths often rely on **shared variables** for communication and control.

  * **Example:** A shared `boolean` variable, `terminate`, is used:
      * The **User-interface thread** sets `terminate` to `true` when the Stop button is clicked.
      * The **Download thread** periodically checks the value of `terminate` and aborts if it is `true`.
  * **Caution:** When multiple threads read and write to shared variables, it creates a risk of **Race Conditions**. Shared variables must be updated **consistently** to prevent errors.

-----

### 4\. Creating Threads in Java 🧵

There are two primary ways to create a concurrent path of execution (a thread) in Java:

#### A. Extending the `Thread` Class

1.  **Class Definition:** Create a class that **extends `Thread`**.
2.  **Define Execution:** Override the **`public void run()`** method. This method contains the code that will execute in the separate thread.
3.  **Start Execution:** To launch the thread, call the **`start()`** method on the `Thread` object.

> **Crucial Distinction:** Calling **`p[i].start()`** initiates `p[i].run()` in a new concurrent thread. Directly calling `p[i].run()` will execute the method sequentially in the **current thread** (no concurrency).

```java
// Example
public class Parallel extends Thread {
    // ... constructor, fields ...
    public void run() { 
        // Code to run concurrently 
    }
}
// In main:
Parallel p = new Parallel(i);
p.start(); // Initiates run() in a new thread
```

#### B. Implementing the `Runnable` Interface

This is often preferred because Java only allows single inheritance (`class X extends Y`), meaning you cannot extend `Thread` if your class already extends another class.

1.  **Class Definition:** Create a class that **implements `Runnable`**.
2.  **Define Execution:** Define the **`public void run()`** method.
3.  **Start Execution:** Since a `Runnable` object is *not* a `Thread`, you must **explicitly create a `Thread` object** using the `Runnable` instance, and then call `start()` on the `Thread` object.

<!-- end list -->

```java
// Example
public class Parallel implements Runnable {
    // ... constructor, fields ...
    public void run() { 
        // Code to run concurrently 
    }
}
// In main:
Parallel p = new Parallel(i);
Thread t = new Thread(p); // Make a thread t from runnable p
t.start();               // Start off p.run()
```

#### Thread Utility Method

  * **`sleep(t)`:** This is a **static function** that suspends the **current thread** for $t$ milliseconds. It must be enclosed in a `try-catch` block because it throws an `InterruptedException`. Use **`Thread.sleep(t)`** if your class does not extend `Thread`.

