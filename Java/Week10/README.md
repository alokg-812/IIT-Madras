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

## 📝 Week 10 Java Notes: Race Conditions and Mutual Exclusion ⚠️

This lecture details the problems arising from concurrent execution, specifically **Race Conditions**, and introduces the fundamental concept for solving them: **Mutual Exclusion**.

-----

### 1\. Threads and Shared Variables 🤝

Concurrency allows multiple **lightweight processes (threads)** to run seemingly in parallel, often relying on **shared variables** for communication and state management.

  * **Problem:** When these threads access and update shared variables simultaneously, it can lead to **unpredictable outcomes** and data inconsistency—a situation known as a **Race Condition**.
  * **Requirement:** Shared variables must be updated **consistently**.

-----

### 2\. Example: Data Consistency in Banking 🏦

The lecture uses a banking scenario with a shared array `double accounts[100]` to illustrate a race condition involving data inconsistency.

#### The Functions

1.  **`transfer()`:** Moves an `amount` from a `source` account to a `target` account.
      * It involves multiple steps: checking balance, subtracting from source, and adding to target.
      * ```java
        accounts[source] -= amount;
        accounts[target] += amount;
        ```
2.  **`audit()`:** Calculates the **total balance** across all accounts.
      * The total balance should remain constant throughout the system's operation, regardless of transfers.

#### The Race Condition Scenario

Consider two threads executing concurrently:

  * **Thread 1:** Executes `status = transfer(500.00, 7, 8);`
  * **Thread 2:** Executes `System.out.print(audit());`

If the operating system's time-slicing **interleaves** the actions of `transfer()` and `audit()`, the `audit()` function can report an incorrect total balance.

| Execution Step | Thread | Action | Accounts State | Total Balance Reported by Audit |
| :---: | :---: | :--- | :--- | :--- |
| **1** | Thread 2 | `audit()` starts its loop. | (Correct, Initial Total) | N/A |
| **2** | Thread 1 | `accounts[source] -= 500.00;` | $\text{Source}-500, \text{Target}$ | N/A |
| **3** | **Context Switch** | Thread 2 resumes. | | N/A |
| **4** | Thread 2 | `audit()` continues, summing up $\text{Source}-500$. | | $(\text{Initial Total}) - 500$ |
| **5** | **Context Switch** | Thread 1 resumes. | | N/A |
| **6** | Thread 1 | `accounts[target] += 500.00;` | $\text{Source}-500, \text{Target}+500$ | N/A |
| **7** | Thread 2 | `audit()` finishes (after Thread 1 completed the transfer). | | $(\text{Initial Total}) - 500$ |

  * **Result:** The `audit()` (Thread 2) reports a total that is **500 less** than the actual system assets because it read the source account *after* the subtraction but finished summing *before* the corresponding addition to the target account. Similarly, it could report **500 more** if the interleaving was different.

-----

### 3\. Example: Atomicity of Updates 🧪

A classic example of a race condition involves two threads concurrently incrementing a shared variable, $n$.

| Thread 1 | Thread 2 | **Comments (if $n=10$ initially)** |
| :---: | :---: | :--- |
| $m = n; \quad (m=10)$ | | **Thread 1 reads $n$** |
| | $k = n; \quad (k=10)$ | **Thread 2 reads the same $n$** |
| $m++; \quad (m=11)$ | | Thread 1 prepares the update |
| | $k++; \quad (k=11)$ | Thread 2 prepares the update |
| $n = m; \quad (n=11)$ | | **Thread 1 updates $n$ to 11** |
| | $n = k; \quad (n=11)$ | **Thread 2 overwrites $n$ with its own value (11).** |

  * **Expected Outcome:** If two threads increment $n$ once, $n$ should increase by $2$.
  * **Actual Outcome (due to race condition):** $n$ only increased by **$1$**. The second thread's increment was "lost" because it read an outdated value of $n$ before Thread 1's update was committed.

-----

### 4\. Solving Race Conditions: Mutual Exclusion 🔒

The issue is that the operations within `transfer()` and `audit()` are **not atomic** (they can be broken down into steps that are interleaved by the OS).

To solve this:

  * **Critical Section:** A segment of code where **shared variables are updated**.
      * In the banking example, the bodies of both `transfer()` and `audit()` are critical sections.
  * **Mutual Exclusion:** The mechanism that guarantees that at most **one thread at a time** can be executing within a critical section.
  * **Goal:** Insist that **`transfer()` and `audit()` do not interleave**. Control must never simultaneously be within `transfer()` for one thread and within `audit()` for another.


## 📝 Week 10 Java Notes: Mutual Exclusion Protocols 🔒

This lecture explores low-level protocols for achieving **mutual exclusion**—the ability to ensure that only one thread is inside a **critical section** (code that updates shared variables) at any given time.

---

### 1\. The Goal: Mutual Exclusion

  * **Critical Section:** Sections of code where **shared variables are updated**.
  * **Mutual Exclusion:** A guarantee that **at most one thread** at a time can be executing in a critical section.
  * **Protocols:** Algorithms designed to achieve mutual exclusion, often using simple shared variables.

---

### 2\. Mutual Exclusion Protocols for Two Processes

The lecture presents attempts to solve the two-process mutual exclusion problem using simple shared variables, highlighting the pitfalls of **Starvation** and **Deadlock**.

#### A. First Attempt (Using `turn`)

This attempt ensures mutual exclusion but has a serious flaw.

| Thread 1 (T1) | Thread 2 (T2) | Purpose & Flaw |
| :--- | :--- | :--- |
| `while (turn != 1) {}` | `while (turn != 2) {}` | **Entry:** T1 waits until `turn` is 1; T2 waits until `turn` is 2. |
| **Critical Section** | **Critical Section** | Only one thread can enter at a time. |
| `turn = 2;` | `turn = 1;` | **Exit:** The thread explicitly passes the turn to the other thread. |

  * **Guarantees:** **Mutually exclusive access is guaranteed.**
  * **Flaw:** **Starvation**. If T2 finishes its critical section and sets `turn = 1`, but T1 then permanently shuts down outside its critical section, T2 will reach its entry loop (`while (turn != 2) {}`) and wait forever for T1 to set `turn = 2`. T2 is permanently locked out.

---

#### B. Second Attempt (Using `request` flags)

This attempt also ensures mutual exclusion but risks both threads blocking simultaneously.

| Thread 1 (T1) | Thread 2 (T2) | Purpose & Flaw |
| :--- | :--- | :--- |
| `request_1 = true;` | `request_2 = true;` | **Declare Intent:** Both threads set a flag indicating they want to enter. |
| `while (request_2) {}` | `while (request_1) {}` | **Wait:** T1 waits if T2 has requested access; T2 waits if T1 has requested access. |
| **Critical Section** | **Critical Section** | |
| `request_1 = false;` | `request_2 = false;` | **Exit:** The thread clears its request flag. |

  * **Guarantees:** **Mutually exclusive access is guaranteed.**
  * **Flaw:** **Deadlock**. If both threads try to enter **simultaneously**, they both set their request flags to `true`. Then, T1 sees `request_2` is `true` and waits, and T2 sees `request_1` is `true` and waits. They are now both waiting for the other, resulting in a **deadlock**.

---

### 3\. Peterson's Algorithm (The Solution) 🏆

Peterson's algorithm cleverly **combines the previous two approaches** to achieve mutual exclusion without starvation or deadlock for two processes.

| Thread 1 (T1) | Thread 2 (T2) | Logic |
| :--- | :--- | :--- |
| `request_1 = true;` | `request_2 = true;` | **Declare Intent (Flag)** |
| `turn = 2;` | `turn = 1;` | **Yield to Other (Turn):** T1 sets `turn = 2`, giving priority to T2. T2 sets `turn = 1`, giving priority to T1. The last one to execute this line determines the initial priority. |
| `while (request_2 && turn == 2) {}` | `while (request_1 && turn == 1) {}` | **Wait Condition:** T1 waits only if T2 is requesting *AND* T2 has the turn. If `turn` is set to T1 (by T1's instruction `turn=2` being overwritten by T2's `turn=1`), T1 enters. |
| **Critical Section** | **Critical Section** | |
| `request_1 = false;` | `request_2 = false;` | **Clear Flag** |

  * **Mechanism:** If both threads try simultaneously, the `turn` variable decides who goes first. If only one thread is attempting to enter, the `request` flag of the other thread remains `false`, allowing the active thread to proceed immediately.
  * **Correctness:** Peterson's algorithm guarantees:
    1.  **Mutual Exclusion:** Only one process in the critical section.
    2.  **Progress:** A thread waiting to enter will eventually get to enter.
    3.  **Bounded Waiting:** A thread that wants to enter won't wait forever.

---

### 4\. Beyond Two Processes 🌐

Generalizing solutions like Peterson's algorithm to **$n$ processes** (where $n > 2$) is **not trivial**.

  * **Lamport's Bakery Algorithm:** A well-known solution for $n$ processes that uses a shared system of tickets/tokens.
      * Each process "takes a number" (increments a shared counter).
      * The lowest number is served next.
      * This is complex because the token counter update is itself not atomic, requiring clever tie-breaking logic.
  * **Conclusion:** Because it is difficult to construct and argue the correctness of these low-level protocols for arbitrary situations, modern software relies on **higher-level support** provided directly by the programming language (like Java's synchronization mechanisms) or the operating system.

