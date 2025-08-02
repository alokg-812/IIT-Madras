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
