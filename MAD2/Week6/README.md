# Week 6

## 🧠 What is Persistent Storage?

Imagine you're filling out a form on a website or selecting some settings on a web app. You refresh the page — and poof! Everything resets. That’s because, by default, **Vue data (or any frontend JS data)** is stored in memory, which disappears when the page reloads.

**Persistent storage** solves this by storing data **in the browser** — so even if you refresh the page or close and reopen the tab, some or all of the data can be restored.


## ❓ Why Do We Need Persistent Storage?

1. **Avoid Data Loss on Refresh**

   * Vue data is gone when the user reloads the page.
   * Persistent storage keeps important data intact.

2. **Offline Functionality**

   * Some apps should work even without internet — like note-taking apps or todo lists.

3. **Local Configuration**

   * Settings like "dark mode", language preference, or saved login info can be stored locally.

4. **Lightweight Apps**

   * Not everything needs a server or database. For small apps, using local storage is simpler.

---

## 🧰 How Can You Implement Persistent Storage?

There are 3 major options in the browser:

---

### 🔹 1. **Cookies**

* Small text files stored by the browser.
* Mostly used for sessions, login tracking, analytics.
* **Limited size** (about 4 KB).
* **Often cleared** when browser is restarted.
* Set using JavaScript:

  ```js
  document.cookie = "username=Alok; expires=Fri, 5 Aug 2025 12:00:00 UTC;";
  ```
* Not ideal for complex data or large values.

---

### 🔹 2. **localStorage & sessionStorage** (Web Storage API)

#### 🗂️ Common Features:

* Key-value storage (`"theme" = "dark"`)
* Only stores **strings**, so objects must be converted using `JSON.stringify()` and `JSON.parse()`
* Synchronous, fast access

#### 📦 `localStorage`

* Data is saved even after browser is closed and reopened.
* Limit: \~5MB in most browsers.
* Good for persistent settings, cache, etc.

```js
localStorage.setItem("theme", "dark");
let theme = localStorage.getItem("theme");
```

#### 📦 `sessionStorage`

* Same API as localStorage
* Data is lost when the **tab or window is closed**
* Good for temporary data during a single visit/session

```js
sessionStorage.setItem("user", "alok");
let user = sessionStorage.getItem("user");
```

👉 **Use Case Comparison:**

| Use Case                               | Storage Type   |
| -------------------------------------- | -------------- |
| Remember login settings                | localStorage   |
| Track form inputs before submit        | sessionStorage |
| Save user preferences (theme/language) | localStorage   |

More: [MDN Web Storage API](https://developer.mozilla.org/en-US/docs/Web/API/Web_Storage_API)

---

### 🔹 3. **IndexedDB**

Think of this as a **mini database** in the browser.

* Stores **structured data** (like objects, arrays)
* Can be queried using keys/indexes
* Works asynchronously (like real databases)
* Good for **large, complex** offline data (e.g., storing app cache, images, JSON data)

```js
// Basic idea – actual API is a bit complex
let db = indexedDB.open("myAppDB", 1);
```

Not as beginner-friendly as localStorage, but very powerful.

---

## 🧪 Example Use Case in Vue:

Suppose you want to save a dark mode setting in your Vue app:

```js
mounted() {
  const theme = localStorage.getItem("theme");
  if (theme) this.isDark = theme === "dark";
}

watch: {
  isDark(newVal) {
    localStorage.setItem("theme", newVal ? "dark" : "light");
  }
}
```

✔️ This makes your app **remember the theme** even after the user closes and reopens the browser.

---

## 🔄 Recap Table

| Storage Method   | Persistent?   | Size  | Use For                        | Data Type          |
| ---------------- | ------------- | ----- | ------------------------------ | ------------------ |
| `Cookies`        | Sometimes     | \~4KB | Sessions, login                | Text               |
| `localStorage`   | Yes           | \~5MB | Themes, login state            | Strings only       |
| `sessionStorage` | No (tab-only) | \~5MB | Temporary form data            | Strings only       |
| `IndexedDB`      | Yes           | >50MB | App cache, files, offline data | Structured objects |

---

## 📚 Bonus Vue Guide

Official Vue Cookbook Example (localStorage):
👉 [Client-side storage in Vue](https://vuejs.org/v2/cookbook/client-side-storage.html)


# ✅ PART 1: **Form Validation in Vue**

---

## 🧠 Why Do We Need Form Validation?

When users submit forms — like login, signup, or feedback — we need to make sure:

* The data is **correct** (e.g. email is valid).
* The data is **complete** (no empty fields).
* The data is **safe** (prevent malicious inputs).

Validation can happen:

* In the **browser (frontend)** – faster, good UX
* On the **server (backend)** – essential for security

---

## 🎯 Types of Form Validation

### 1. **Basic Browser Checks**

✅ Examples:

* Required fields
* Minimum length
* Valid email format

```html
<input type="email" required />
<input type="number" min="1" max="100" />
```

### 2. **Custom Validation**

✅ Examples:

* Password must include uppercase, number, and symbol
* Confirm password matches
* Form values satisfy a condition (e.g. age + experience ≤ 100)

---

## 🛠️ How Vue Helps with Validation

Vue makes it easier through:

* **Two-way binding** with `v-model`
* **Reactivity** to show or hide errors dynamically
* **Event handling** with `@submit.prevent`

---

## ✨ Example: Basic Form Validation in Vue

```html
<template>
  <form @submit.prevent="validateForm">
    <input v-model="email" type="email" placeholder="Enter email" />
    <span v-if="!isValidEmail">Invalid email!</span>

    <input v-model="age" type="number" placeholder="Enter age" />
    <span v-if="age < 18">Must be at least 18</span>

    <button type="submit">Submit</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      age: null,
    };
  },
  computed: {
    isValidEmail() {
      return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.email);
    },
  },
  methods: {
    validateForm() {
      if (!this.isValidEmail || this.age < 18) {
        alert("Please fix the errors.");
        return;
      }
      alert("Form submitted!");
    },
  },
};
</script>
```

---

## 🧩 Useful Vue Directives for Validation

| Directive         | Use                                       |
| ----------------- | ----------------------------------------- |
| `v-model`         | Two-way binding of inputs                 |
| `v-if / v-show`   | Show/hide error messages                  |
| `@submit.prevent` | Prevent form from submitting unless valid |

---

## 🧪 Custom Validation Example

You can also validate with logic:

```html
<input v-model="username" />
<span v-if="username.length < 5">Username too short!</span>
```

### 🛑 Disabling Native HTML Validation

To take full control of the process:

```html
<form novalidate @submit.prevent="submitForm">
```

---

# ✅ PART 2: **Managing Components in Vue**

---

## 🧠 Why Use Components?

Imagine building a large web app like Amazon:

* You’ll reuse the **same layout**, **buttons**, **product cards**, **modals**, etc.
* Each part should be **independent**, **modular**, and **reusable**.

That's where Vue **components** come in.

---

## 🧱 What is a Vue Component?

A **component** is a self-contained part of the UI with:

* Its own HTML (template)
* Its own CSS (styles)
* Its own JS (logic/data/methods)

---

## 📄 Types of Components

### ✅ Global Components:

* Registered once, usable anywhere

```js
Vue.component('my-button', { /* ... */ });
```

### ✅ Local Components:

* Declared inside other components

```js
export default {
  components: {
    MyButton,
  },
};
```

---

## 🧩 Why Use **Single File Components (SFCs)**

Instead of writing everything inline in the HTML file, we use `.vue` files with this format:

```vue
<template>
  <div class="user-card">
    {{ name }}
  </div>
</template>

<script>
export default {
  props: ['name'],
};
</script>

<style scoped>
.user-card {
  color: blue;
}
</style>
```

---

## 🛠️ Problems with Not Using SFCs (As Mentioned in the PDF):

1. **Global namespace conflict**
   If all components share the same global space, names can clash.

2. **No scoping in CSS**
   CSS can leak from one component to another.

3. **Difficult to organize**
   Templates as strings are harder to read, debug, or format.

---

## 🔧 SFC Workflow (with tools like Vite/Webpack)

Since browsers don’t understand `.vue` files directly:

1. Tools like Vite or Webpack **compile** them into regular `.js`, `.css`, and `.html`.
2. These can then be loaded by the browser.

---

## 🔌 Tooling Stack for Components

| Tool                         | Purpose                                    |
| ---------------------------- | ------------------------------------------ |
| **Vite / Webpack / ESBuild** | Compile `.vue` files                       |
| **npm / yarn**               | Manage packages                            |
| **babel**                    | Convert modern JS to browser-compatible JS |
| **Vue CLI / Vite CLI**       | Create and run projects easily             |

---

## 🧪 Testing Vue Components

Vue components can also be **unit tested**:

* **Mount** them in a test environment
* Use tools like:

  * `jest` – test runner
  * `mocha` + `chai` – assertions
  * `@vue/test-utils` – for Vue-specific helpers

### 🧩 Example test (pseudocode):

```js
mount(MyComponent);
expect(wrapper.text()).toContain('Hello');
```

---

## 🔄 Summary

### 🔹 Form Validation:

| Feature          | Vue Advantage                        |
| ---------------- | ------------------------------------ |
| Instant feedback | Reactive `v-model`                   |
| Custom rules     | Easy JS logic                        |
| Hide/show errors | `v-if` or `v-show`                   |
| Prevent submit   | `@submit.prevent`                    |
| Full control     | `novalidate` disables browser checks |


### 🔹 Component Management:

| Feature                 | Why it matters                       |
| ----------------------- | ------------------------------------ |
| SFC format (`.vue`)     | Keeps code modular and clean         |
| Scoped CSS              | Prevents styling clashes             |
| Tools like Vite/Webpack | Handle compilation and bundling      |
| Testing tools           | Ensure your components work reliably |




# ✅ Why Testing Matters in Vue Apps

Even though Vue apps are interactive and reactive, bugs still creep in — like:

* UI elements not showing as expected
* Inputs not validating correctly
* Buttons not triggering actions
* Components breaking when reused

**Testing ensures**:

* Your components behave correctly
* Future code changes don’t break existing features
* Peace of mind before deployment 🚀

---

## 🧪 Types of Testing in Vue

---

### 🔹 1. **Unit Testing** (Focuses on *one* component or function)

* Test individual Vue components like buttons, forms, cards, etc.
* Make sure props, events, methods, and rendering all work as expected.
* **Fast** and **isolated** — doesn’t need a backend or browser.

### ✅ Example:

```js
// MyButton.vue
<template>
  <button @click="$emit('clicked')">Click Me</button>
</template>
```

```js
// MyButton.spec.js
import { mount } from '@vue/test-utils';
import MyButton from '@/components/MyButton.vue';

test('emits "clicked" when clicked', () => {
  const wrapper = mount(MyButton);
  wrapper.trigger('click');
  expect(wrapper.emitted().clicked).toBeTruthy();
});
```

---

### 🔹 2. **End-to-End (E2E) Testing**

* Simulates **real user actions** in a browser.
* You test the **full app** (frontend + backend).
* Example: User logs in, navigates, fills form, submits.

🛠 Tools: [Cypress](https://www.cypress.io/), [Playwright](https://playwright.dev/), [Nightwatch.js](https://nightwatchjs.org/)

```js
// Cypress E2E test
cy.visit('/login');
cy.get('input[name=email]').type('user@example.com');
cy.get('input[name=password]').type('secret');
cy.get('button[type=submit]').click();
cy.url().should('include', '/dashboard');
```

---

### 🔹 3. **Cross-Browser Testing**

* Checks that your app works correctly in different browsers (Chrome, Firefox, Safari, Edge)
* Vue works well in modern browsers, but for wider audience, testing in older versions may be needed

⚠️ **Cost vs benefit:** Supporting every old browser may not be worth it.

---

# ⚙️ Setting Up Testing in Vue

---

## 🔧 Tools for Unit Testing:

| Tool              | Use                                               |
| ----------------- | ------------------------------------------------- |
| `@vue/test-utils` | Vue-specific testing helpers                      |
| `jest`            | JavaScript testing framework                      |
| `mocha` + `chai`  | Alternative testing framework (more customizable) |
| `vitest`          | Lightweight test runner for Vite projects         |

---

## ✅ How Unit Testing Works

### 🔁 Steps:

1. **Mount** the component
2. **Simulate** user actions (click, input, etc.)
3. **Check** the result (rendered text, emitted events, etc.)

---

## 🧩 Example: Testing a Login Form

### 🔹 LoginForm.vue

```vue
<template>
  <form @submit.prevent="submitForm">
    <input v-model="email" />
    <input v-model="password" type="password" />
    <button type="submit">Login</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    submitForm() {
      this.$emit('login', { email: this.email, password: this.password });
    },
  },
};
</script>
```

---

### 🔹 LoginForm.spec.js

```js
import { mount } from '@vue/test-utils';
import LoginForm from '@/components/LoginForm.vue';

test('emits login with email and password', async () => {
  const wrapper = mount(LoginForm);
  await wrapper.find('input[type="text"]').setValue('alok@example.com');
  await wrapper.find('input[type="password"]').setValue('123456');
  await wrapper.find('form').trigger('submit.prevent');

  expect(wrapper.emitted().login[0][0]).toEqual({
    email: 'alok@example.com',
    password: '123456',
  });
});
```

---

## 📦 Test Structure

Your project will usually have:

```
src/
  components/
  views/
tests/
  unit/
    MyComponent.spec.js
  e2e/
    LoginFlow.spec.js
```

Run with:

```bash
npm run test:unit
npm run test:e2e
```

---

## 🧪 Creating Test Fixtures

* Fixtures are **sample/mock data** used during tests
* Helps keep tests consistent and reusable

```js
const mockUser = {
  name: "Alok",
  email: "alok@iitm.ac.in",
};
```

---

## 🔁 Test Suite

* A **collection of related tests** grouped together.
* Helps organize testing for a single component or feature.

```js
describe('UserCard.vue', () => {
  it('displays user name', () => { ... });
  it('emits delete when button clicked', () => { ... });
});
```

---

## ✅ Summary Table

| Type of Test  | Focus                 | Tools                  | Scope                       |
| ------------- | --------------------- | ---------------------- | --------------------------- |
| Unit Test     | Individual components | Jest, Vue Test Utils   | Fast, isolated              |
| E2E Test      | Whole app, user flow  | Cypress, Playwright    | Real UI testing             |
| Cross-browser | Compatibility         | Manual, cloud services | Rarely full coverage needed |

---

## 🔗 Helpful Links

* Vue Unit Testing Guide: [https://vuejs.org/v2/cookbook/unit-testing-vue-components.html](https://vuejs.org/v2/cookbook/unit-testing-vue-components.html)
* Cypress Docs: [https://docs.cypress.io](https://docs.cypress.io)
* Vitest (for Vite): [https://vitest.dev/](https://vitest.dev/)





