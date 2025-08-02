# Week 5

## 🔹 What Are APIs and Why Do We Use Them?

Imagine you have a **kitchen (backend)** and a **waiter (frontend)** in a restaurant. You don’t want customers (users) to directly enter the kitchen and cook. Instead, the waiter brings the customer’s order to the kitchen and brings back the food. <br>
**APIs are like that waiter.** <br>
They:
* Carry requests from the frontend to the backend.
* Bring data back to the frontend in a **standard format (like JSON)**.

## 🔹 Separation of Concerns (Frontend vs Backend)
In a well-designed app:
* **Backend** handles:
  * Storing data (like users, bookings, messages)
  * Business logic (like calculating scores or validating passwords)
  * Exposing this data through **APIs**, usually as **JSON**
  * **Does NOT care how the UI looks**

* **Frontend** handles:
  * The design and visuals
  * Buttons, forms, pages
  * Calling backend APIs and displaying results

📌 *This "separation of concerns" makes the code easier to maintain and upgrade.*

## 🔹 How Do They Talk? (Using APIs)

### ➤ Backend gives APIs like:

`` GET /weather?city=Chennai ``
The response might be:

```json
{
  "city": "Chennai",
  "temperature": 33,
  "humidity": 80
}
```

### ➤ Frontend uses this data to show:
> ☀️ Chennai is 33°C with 80% humidity today.

The **frontend doesn’t need to know** *how* the backend got the data. It just uses it.

## 🔹 Fetching the Data – But It’s Not Instant!
Fetching data from an API **is not instant** like reading a variable. The data might come from:
* A slow database
* A remote server
* A weak internet connection

So, JavaScript fetches data **asynchronously**, meaning:
> "I’ve asked for it, I’ll do other things until it arrives."
This is where **callbacks, promises**, etc., come in.

## 🔹 JavaScript is Single-Threaded (Important Point)
JavaScript runs **one thing at a time**. If one function takes too long, it can **freeze the entire app**. That’s why time-consuming stuff (like fetching data) should be done **asynchronously**, so the app doesn’t freeze.

## 🔹 What are Callbacks?
Let’s say you’re ordering pizza 🍕.
* You place the order (function starts).
* The chef starts cooking (long task).
* Instead of standing and waiting, you say: “Hey, when it’s ready, **call me**.”
That’s a **callback** — a function that will be called when the task is complete.

### ✅ Code Example:
```js
function getData(callback) {
  setTimeout(() => {
    let data = "Here’s the API data!";
    callback(data); // Call the callback when data is ready
  }, 2000); // 2 seconds delay
}

getData(function (result) {
  console.log(result);
});
```

🧠 What’s happening?
* `getData` takes time (like fetching from the internet).
* Instead of waiting, we pass a function (`callback`) that gets executed **once the data is ready**.

### 📛 Without Callbacks:
```js
let result = getData(); // BAD if getData is slow
console.log(result); // This may run before data is ready!
```

This causes bugs — the result may be undefined if the data isn't ready yet.


## 🔹 Real-Life Analogy of Callback

You're baking a cake. Instead of staring at the oven for 45 minutes, you:

* Set a timer (setTimeout)
* Do other work
* When the timer rings, you come back (callback is called)

Great! Let’s continue with the **rest of the concepts** from your PDF, again in an easy, human-readable style. You already understand **APIs, separation of frontend/backend, async programming**, and **callbacks** — now let’s go deeper.

---

## 🔹 EVENTS – The Building Blocks of Interactivity

An **event** is anything that happens in the browser:

* Clicking a button
* Pressing a key
* Mouse hovering
* API response received

You can **tell JavaScript what to do when these things happen** using **event handlers**.

### Example:

```js
document.getElementById("myBtn").onclick = function() {
  alert("Button clicked!");
};
```

* You didn't *call* this function yourself.
* You *registered* it to be called *when the event happens*.

This is just another example of a **callback** — you’re saying:

> “Hey browser, when this event happens, run this function.”

---

## 🔹 JS: Event Loop & Call Stack — What Makes Async Possible?

This is how JavaScript handles multiple tasks **without freezing**.

### 🌟 The Stack (Call Stack)

This is like a “To-Do list” for the JS engine. It executes things **one-by-one**.

If you write:

```js
console.log("Start");
alert("Hi!");
console.log("End");
```

It does:

1. Print “Start”
2. Show alert box
3. After you click OK, print “End”

But what if something takes time, like an API call?

---

### 🌟 The Event Loop + Callback Queue

When you do something **async**, like:

```js
setTimeout(() => console.log("Hello"), 2000);
```

JS doesn’t wait. Here’s what happens:

1. It sends the `setTimeout` task to the **Web API environment** (handled by the browser).
2. It keeps going with other code.
3. When the timer ends, your callback is put in the **callback queue**.
4. The **event loop** checks if the stack is empty, and if so, it pushes your callback to be executed.

### Visual Flow:

```
Main Code 🠒 Stack
Async Task 🠒 Web APIs
After Done 🠒 Callback Queue 🠒 Stack 🠒 Executes
```

✅ **Result**: App feels fast, doesn’t freeze.

---

## 🔹 PROMISES – Cleaner Alternative to Callbacks

### 🔁 Problem with Callbacks:

They get **nested** quickly:

```js
getData(function(result1) {
  processData(result1, function(result2) {
    display(result2, function() {
      console.log("Done!");
    });
  });
});
```

👎 This is called **callback hell**.

---

### ✅ Promises to the Rescue

A **Promise** is an object that represents a value that will be ready *in the future*.

Instead of nesting, we **chain** actions:

```js
getData()
  .then(result1 => processData(result1))
  .then(result2 => display(result2))
  .catch(error => console.error(error));
```

**.then()** handles success, **.catch()** handles errors.

---

### 🔧 How Promises Work:

```js
let myPromise = new Promise((resolve, reject) => {
  let success = true;

  if (success) resolve("Yay! Success");
  else reject("Oops! Failed");
});

myPromise
  .then(data => console.log(data))      // runs on resolve
  .catch(err => console.error(err));    // runs on reject
```

---

## 🔹 CONCURRENCY vs PARALLELISM

These two terms are often mixed up. Here’s the difference:

| Term            | Meaning                                                                                                                 |
| --------------- | ----------------------------------------------------------------------------------------------------------------------- |
| **Concurrency** | Doing multiple things at the same time (but not necessarily at the *exact* same moment). They take turns using the CPU. |
| **Parallelism** | Truly running tasks simultaneously (like on two cores or threads).                                                      |

✅ JavaScript runs **concurrent** code through the event loop, but not **parallel** — unless you use special tools like **Web Workers**.

---

## 🔹 FETCH – Getting Data from an API

This is the modern way to get data in JavaScript. It returns a **Promise**.

### Example:

```js
fetch('https://api.example.com/data')
  .then(response => response.json())  // Convert response to JSON
  .then(data => console.log(data))    // Use the data
  .catch(err => console.log("Error:", err));
```

* `fetch()` is built-in in modern browsers.
* It's **asynchronous** and doesn't block your app.
* Always use `.catch()` to handle errors (like broken URLs, server issues, etc.)

📚 Learn more: [MDN Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch)

---

## 🔹 AXIOS – Another Way to Fetch Data

**Axios** is a library you can use instead of fetch. Why people use it:

✅ Works in older browsers
✅ Automatically parses JSON
✅ Better error messages
✅ Works in both frontend and Node.js

### Example:

```js
axios.get('https://api.example.com/data')
  .then(response => console.log(response.data))
  .catch(error => console.error("Error:", error));
```

📚 Read more: [Axios vs Fetch](https://blog.logrocket.com/axios-or-fetch-api/)

## 🔹 Real APIs You Can Practice With

You can build entire frontend projects using **public APIs** — no backend needed!

Examples:

* 🛰️ [OpenWeatherMap](https://openweathermap.org/api) – Weather data
* 📖 [Wikipedia API](https://www.mediawiki.org/wiki/API:Main_page) – Search articles
* 👨‍💻 [GitHub API](https://docs.github.com/en/rest) – User profiles, repos
* 📰 [HackerNews API](https://github.com/HackerNews/API) – Trending news

## ✅ Final Wrap-up Table

| Concept         | Explanation                                        |
| --------------- | -------------------------------------------------- |
| **Event**       | Something happens (click, API response, etc.)      |
| **Callback**    | A function called *after* a task finishes          |
| **Promise**     | A modern way to handle async tasks                 |
| **fetch()**     | Built-in way to get data from a URL                |
| **axios**       | Library similar to fetch but with extras           |
| **Event Loop**  | The behind-the-scenes system that makes async work |
| **Concurrency** | Many tasks in progress (but one at a time)         |
| **Parallelism** | Truly doing multiple things at the same moment     |

Let me know if you'd like:

* A summarized cheat sheet
* Practice exercises
* A small project to try out these concepts















