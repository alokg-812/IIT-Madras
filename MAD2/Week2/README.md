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
