# JavaScript Collections

JavaScript provides powerful structures to manage collections of data. These include:

### 1. **Arrays**

* A **flexible container** to hold ordered items.
* Can hold **any data type**, including mixed types (numbers, strings, objects, functions).

#### Real-life analogy:

Think of an array as a row of boxes labeled with index numbers, where each box can hold any item.

```js
let basket = ['apple', 42, { item: 'banana' }, function greet() { console.log("Hi"); }];
console.log(basket[0]); // "apple"
console.log(basket.length); // 4
```

---

### 2. **Iteration**

JavaScript lets you **loop over** elements using constructs like:

* `for`, `for...of`
* `forEach()`
* `map()`, `filter()`, `find()`

#### Concepts:

* **Iterable**: An object you can loop through (like Array, String, Map, Set)
* **Iterator**: Internal mechanism pointing to the next item in the sequence.

```js
let colors = ['red', 'blue', 'green'];
colors.forEach(color => console.log(color));
```

---

### 3. **Transformation Functions**

* These are **functional programming tools** that take other functions as input.

```js
let numbers = [1, 2, 3, 4, 5];
let squares = numbers.map(x => x * x);  // [1, 4, 9, 16, 25]
let evens = numbers.filter(x => x % 2 === 0); // [2, 4]
```

#### Real-life analogy:

Think of a filter machine that only allows certain marbles to pass through based on rules (functions).

---

### 4. **Other Collections**

* **Map**: A collection of key-value pairs (like a Dictionary).
* **Set**: A collection of **unique** values.
* **WeakMap** & **WeakSet**: Similar to above but keys must be objects and support garbage collection.

```js
let map = new Map();
map.set('name', 'Alok');
map.set('age', 21);

let set = new Set([1, 2, 2, 3]); // Set { 1, 2, 3 }
```

---

### 5. **Destructuring**

Extract values from arrays or properties from objects into distinct variables.

```js
let [x, y] = [10, 20];
let {name, age} = {name: "Alok", age: 21};
```

#### Real-life analogy:

Unpacking a delivery box directly into labeled shelves.

---

### 6. **Generators (Advanced)**

* Functions that can **pause and resume**.
* Useful when working with **custom iterators** or **infinite sequences**.

```js
function* gen() {
  yield 1;
  yield 2;
  yield 3;
}
let g = gen();
console.log(g.next().value); // 1
```

---

# JavaScript Modularity

Modularity helps **organize code**, reuse logic, and keep functionality **separate and maintainable**.

---

### 1. **Modules**

Modules are **self-contained files** with specific functions/objects, which can be **exported and imported**.

```js
// math.js
export function add(a, b) { return a + b; }

// app.js
import { add } from './math.js';
console.log(add(2, 3)); // 5
```

#### Real-life analogy:

Think of modules as separate LEGO blocks you can combine to build something big.

---

### 2. **Ways of Implementing Modules**

* **Script Tag**: Directly include script files.
* **CommonJS**: Used in Node.js (`require`, `module.exports`) – synchronous.
* **AMD (Asynchronous Module Definition)**: Browser-focused.
* **ES6 Modules**: Modern standard (`import/export`).

---

### 3. **npm (Node Package Manager)**

* Manages **third-party packages** (like libraries).
* Used in backend (Node.js) and frontend (with bundlers like Webpack).

```bash
npm install lodash
```

---

### 4. **Objects and Inheritance**

JavaScript is **prototype-based**, meaning objects inherit directly from other objects.

#### Object Example:

```js
let person = {
  name: 'Alok',
  greet: function() { console.log(`Hello, ${this.name}`); }
};
person.greet(); // Hello, Alok
```

#### Class Syntax (Still prototype-based):

```js
class Student extends Person {
  constructor(name, course) {
    super(name);
    this.course = course;
  }
}
```

---

### 5. **Prototype Inheritance**

* Each object can inherit from another using its **prototype** chain.
* Reuse methods/properties from a parent object.

#### Real-life analogy:

A child (object) inheriting traits and behavior from a parent (prototype).

---

# Summary Table

| Collection Type | Description        | Example            |
| --------------- | ------------------ | ------------------ |
| Array           | Ordered collection | `[1, 2, 3]`        |
| Map             | Key-value pairs    | `new Map()`        |
| Set             | Unique values      | `new Set()`        |
| Iterable        | Can be looped      | Arrays, Strings    |
| Destructuring   | Extract values     | `let [a, b] = arr` |
| Generator       | Custom iterables   | `function*()`      |

| Modularity Type | Use Case        | Syntax                   |
| --------------- | --------------- | ------------------------ |
| ES6 Modules     | Modern browsers | `import/export`          |
| CommonJS        | Node.js backend | `require/module.exports` |
| npm             | Package manager | `npm install`            |


Sure! Here's a **detailed breakdown of Asynchrony and JSON in JavaScript**, with **real-world analogies**, **code examples**, and **important concepts** from your slide.

---

# 🔄 JavaScript Asynchrony

JavaScript runs in a **single thread**, meaning it can do **only one thing at a time**. But thanks to **asynchronous programming**, JavaScript can handle **long-running operations** (like network requests or file reading) **without freezing the UI or blocking other tasks**.

---

## 🧠 Key Concepts

| Concept          | Description                                                                           | Analogy                                                                              |
| ---------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| **Synchronous**  | Code runs line by line, waits for each line to finish                                 | A queue at a single billing counter — each person must finish before the next        |
| **Asynchronous** | Code runs without blocking, defers long tasks to be completed later                   | Ordering food at a restaurant — place your order and wait while doing something else |
| **Call Stack**   | Keeps track of function calls                                                         | Stack of plates: Last in, first out                                                  |
| **Event Loop**   | Keeps checking if the call stack is empty and pushes next task from the task queue    | A waiter checking if the chef is free to prepare the next dish                       |
| **Task Queue**   | Stores tasks (like events, timeouts) to be executed after current call stack is empty | A to-do list the system checks once it’s free                                        |


## 🕳️ Problem With Synchronous Code

```js
const fs = require('fs');

const data = fs.readFileSync('file.txt', 'utf8');
console.log(data);  // Blocks everything until file is read
```

### ❗ Problem:

If the file is large or slow to load, your entire app `pauses` until it completes.

## ✅ Solution: Asynchronous Code

```js
const fs = require('fs');

fs.readFile('file.txt', 'utf8', (err, data) => {
  if (err) console.error(err);
  else console.log(data);  // This runs later
});
```

➡ The program **doesn't wait** for file reading to complete. It sets a callback and **keeps running** other code.


## ⏳ Promises (Better Async Syntax)

A Promise is a **placeholder for a value** that may be available now, later, or never.

```js
let promise = new Promise((resolve, reject) => {
  setTimeout(() => resolve("Done!"), 2000);
});

promise.then(result => console.log(result)); // "Done!" after 2 sec
```

---

## 💤 async/await: Cleaner Asynchronous Code

```js
async function getData() {
  let response = await fetch('https://api.example.com/data');
  let data = await response.json();
  console.log(data);
}
getData();
```

➡ Cleaner than chaining `.then()`. It **waits** at each `await` without blocking other code.

## 🔁 Asynchronous Iteration

Used when **each step of a loop waits on an asynchronous task**:

```js
async function processUsers(users) {
  for await (let user of users) {
    await fetchUserData(user);  // waits for each call
  }
}
```


## 🧵 Why Callbacks?

Callbacks are functions passed to other functions to run **after a task completes**.

```js
function sayHello(callback) {
  setTimeout(() => {
    console.log("Hello!");
    callback();
  }, 1000);
}

sayHello(() => console.log("Callback called"));
```

## 🌀 Visual Tools to Understand

* 📽️ [Call Stack and Event Loop (Video)](https://vimeo.com/96425312)
* 🔍 [Loupe Visualizer](http://latentflip.com/loupe/) — excellent visualizer for JS execution

# 🧾 JSON (JavaScript Object Notation)

JSON is a lightweight **data-interchange format** used for **storing, transmitting, and reading structured data**.

---

## 🌍 Why JSON?

* Common language for APIs
* Readable by both **humans and machines**
* Works across languages: Python, Java, JS, etc.


## 🧱 JSON Structure

* Objects: `{ "key": "value" }`
* Arrays: `[ { }, { } ]`
* Data Types: string, number, boolean, null, array, object

```json
{
  "name": "Alok",
  "age": 21,
  "skills": ["JavaScript", "Python"],
  "graduated": false
}
```


## 🔄 JSON Methods in JS

### 1. `JSON.stringify()`

Converts **JavaScript object** → **JSON string**

```js
let user = { name: "Alok", age: 21 };
let jsonString = JSON.stringify(user);
console.log(jsonString); // '{"name":"Alok","age":21}'
```

### 2. `JSON.parse()`

Converts **JSON string** → **JavaScript object**

```js
let jsonString = '{"name":"Alok","age":21}';
let user = JSON.parse(jsonString);
console.log(user.name); // "Alok"
```

## ⚠️ JSON Limitations

* Only supports certain types (no `undefined`, `functions`, `Date`)
* Cannot handle **circular references**
* Trailing commas not allowed (unlike JS objects)

## 📦 Real-life Example: API Response

```js
// API Response
let response = `{
  "status": "success",
  "data": {
    "name": "Guardian of the Roads",
    "activeUsers": 3200
  }
}`;

// Parse into usable object
let parsed = JSON.parse(response);
console.log(parsed.data.name); // Guardian of the Roads
```

---

## 🛠 JSON in APIs

| Use Case               | What Happens               |
| ---------------------- | -------------------------- |
| Sending data to server | `JSON.stringify(userData)` |
| Receiving API response | `JSON.parse(apiResponse)`  |

# ✅ Summary

### 🔄 Asynchrony

| Term          | What it does                              |
| ------------- | ----------------------------------------- |
| `Callback`    | Function called after async task finishes |
| `Promise`     | Object representing future value          |
| `async/await` | Syntactic sugar for promises              |
| `Call Stack`  | Keeps track of running functions          |
| `Event Loop`  | Controls async behavior                   |
| `Task Queue`  | Holds pending events/tasks                |

### 🧾 JSON

| Method             | Purpose                          |
| ------------------ | -------------------------------- |
| `JSON.stringify()` | Convert JS object to JSON string |
| `JSON.parse()`     | Convert JSON string to JS object |
