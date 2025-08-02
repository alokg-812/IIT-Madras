# Week 7


# 🧠 What is State Management?

**State** = the **data** or **status** of your app at a given time.

Examples:

* Is the user logged in?
* What's in the shopping cart?
* Which tab is active?

**Vue State Management** answers the question:

> "How do different parts of my app share, update, and keep track of data — in a clean and manageable way?"

---

## 💡 Core Idea:

> UI = f(State)
> Your app's User Interface should be a direct reflection of its current state.

When the state changes, the UI should automatically update.

---

# 📦 Basic Structure of State Management (Unidirectional Flow)

Vue (and many modern JS frameworks) follow this pattern:

```
State → View → Action → State
```

| Element      | Role                                                               |
| ------------ | ------------------------------------------------------------------ |
| **State**    | The data that drives the UI (cart items, user info)                |
| **View**     | What the user sees, based on the current state                     |
| **Action**   | What the user does (clicks, inputs, etc.) to trigger changes       |
| **Mutation** | (in Vuex) A **committed** function that actually updates the state |

---

# 🧩 State Sharing Between Components

## ✅ Parent to Child:

* Use `props` to pass data down

```vue
<ChildComponent :message="parentMessage" />
```

## ✅ Child to Parent:

* Use `$emit` to send event back up

```vue
this.$emit('updateMessage', newValue);
```

### ⚠️ Problem:

This works fine for **small apps**.
But if you have 10+ components — some nested, some sibling — this becomes:

> **Too complex. Too many props. Too many events. Too hard to debug.**

---

# 🔥 The Problem: Global State Needed

Imagine a cart icon in the header should update when an item is added in a deep product page.

You **could** use global variables, but:

❌ Any component can change them
❌ Hard to track changes
❌ Easily breakable & messy

---

# ✅ The Vuex Solution

**Vuex** is Vue’s official state management library.

It offers:

* A **single global store**
* Centralized & predictable updates
* Tools for debugging and time-travel

---

## 📂 Structure of a Vuex Store

```js
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment(state) {
      state.count++;
    }
  },
  actions: {
    incrementAsync({ commit }) {
      setTimeout(() => commit('increment'), 1000);
    }
  },
  getters: {
    doubleCount(state) {
      return state.count * 2;
    }
  }
});
```

---

## 🧠 Key Concepts in Vuex

| Concept     | Description                                          |
| ----------- | ---------------------------------------------------- |
| `state`     | The actual data (global store)                       |
| `getters`   | Like computed properties for store                   |
| `mutations` | Synchronous functions to modify state                |
| `actions`   | Async logic (e.g., API calls), then commit mutations |
| `dispatch`  | Call actions                                         |
| `commit`    | Trigger a mutation                                   |
| `store`     | The Vuex instance you attach to your Vue app         |

---

## 🔄 How It Works in Practice

### Accessing State

```js
this.$store.state.count;
```

### Committing a Mutation

```js
this.$store.commit('increment');
```

### Dispatching an Action

```js
this.$store.dispatch('incrementAsync');
```

### Using Getters

```js
this.$store.getters.doubleCount;
```

---

## 🛠️ Vuex in a Component

```vue
<template>
  <div>
    <p>Count: {{ count }}</p>
    <button @click="increment">+1</button>
  </div>
</template>

<script>
export default {
  computed: {
    count() {
      return this.$store.state.count;
    }
  },
  methods: {
    increment() {
      this.$store.commit('increment');
    }
  }
};
</script>
```

---

# 🔧 Advanced Concepts

### ✅ **Actions** – for async operations

```js
actions: {
  async fetchUser({ commit }) {
    const data = await fetch("/user").then(r => r.json());
    commit('setUser', data);
  }
}
```

### ✅ **Time Travel Debugging**

With Vue Devtools, you can:

* See all mutations
* Replay them step by step
* Debug like a pro 🕵️‍♂️

---

# 🤔 Why Not Always Use Vuex?

| Pros                                 | Cons                        |
| ------------------------------------ | --------------------------- |
| Centralized state = easier to debug  | Slightly more complex setup |
| Better for large apps                | Overkill for small apps     |
| Works well with Vue Router, Devtools | Adds extra boilerplate      |

Use Vuex **when multiple unrelated components** need to access or modify the same data.

---

## 🧠 Related State Management Patterns

| Pattern              | Key Idea                                                       |
| -------------------- | -------------------------------------------------------------- |
| **Flux** (Facebook)  | Unidirectional flow: View → Action → Dispatcher → Store        |
| **Redux**            | Single state tree, pure functions only, used with React        |
| **Elm Architecture** | Functional, structured: Model → View → Update (inspired Redux) |

Vuex draws inspiration from all of them.


## 🔚 Summary

✅ Vuex = clean solution for **centralized, reactive state**
✅ Prevents chaos in large Vue apps
✅ Uses **mutations** and **actions** to keep updates controlled
✅ Great developer tooling (debugging, devtools)


# 🌐 What is Routing in Vue?

### ➤ In traditional websites:

* Each URL = a different **HTML page** loaded from the server.
* Clicking a link = full page reload (slower, clunkier UX)

### ➤ In Vue apps (and other modern frameworks):

> Routing lets us map URLs to **components**, not separate HTML files.

So we can build a **Single Page Application (SPA)** where:

* The page never fully reloads
* Clicking a link just **replaces a part of the page** with a different Vue component

---

# 🚦 Introducing `vue-router`

This is the official library used in Vue.js to handle client-side routing.

---

## 🛠 How Vue Routing Works (Step-by-Step)

### ✅ 1. Define your components

```js
const Home = { template: '<h2>Home Page</h2>' };
const About = { template: '<h2>About Page</h2>' };
```

### ✅ 2. Define routes

Each route maps a **path (URL)** to a **component**.

```js
const routes = [
  { path: '/', component: Home },
  { path: '/about', component: About }
];
```

### ✅ 3. Create a `VueRouter` instance

```js
const router = new VueRouter({
  routes // shorthand for routes: routes
});
```

### ✅ 4. Use `<router-view>` and `<router-link>` in your app

```html
<div id="app">
  <router-link to="/">Home</router-link>
  <router-link to="/about">About</router-link>
  
  <router-view></router-view>
</div>
```

### ✅ 5. Attach the router to your Vue instance

```js
new Vue({
  router
}).$mount('#app');
```

---

## 🧠 Core Components of Vue Router

| Feature              | Purpose                                                |
| -------------------- | ------------------------------------------------------ |
| `<router-view>`      | Where the matched component is displayed               |
| `<router-link>`      | Replaces `<a>` tag to avoid page reload                |
| `VueRouter` instance | Manages route definitions and history                  |
| `$route`             | The route object (contains path, params, query)        |
| `$router`            | The router instance (use to programmatically navigate) |

---

# 📦 Dynamic Routes

You can use **params** in routes for dynamic behavior.

### 🔁 Example:

```js
const User = { template: '<p>User ID: {{ $route.params.id }}</p>' };

const routes = [
  { path: '/user/:id', component: User }
];
```

* `/user/10` will show: **User ID: 10**
* Use `this.$route.params.id` to access dynamic segments.

---

# 🔍 Watching Route Changes

Sometimes navigating between routes reuses the same component, so it won’t re-render automatically. To handle this, use a **watcher**:

```js
watch: {
  $route(to, from) {
    // react to route changes
    this.loadUser(to.params.id);
  }
}
```

---

# 🔄 Programmatic Navigation

Use `$router.push()` to change the route via code.

```js
this.$router.push('/about');
this.$router.push({ name: 'user', params: { id: 5 } });
```

---

# ⚡ Advanced Features

### ✅ 1. **Nested Routes**

Routes inside routes.

```js
const routes = [
  {
    path: '/dashboard',
    component: Dashboard,
    children: [
      { path: 'profile', component: Profile },
      { path: 'settings', component: Settings }
    ]
  }
];
```

### ✅ 2. **Named Routes**

Instead of referring to `/user/42`, use:

```js
{ name: 'user', path: '/user/:id', component: User }

this.$router.push({ name: 'user', params: { id: 42 } });
```

### ✅ 3. **Named Views**

You can load multiple components **simultaneously** in different `<router-view>`s:

```js
{
  path: '/split',
  components: {
    default: MainView,
    sidebar: Sidebar
  }
}
```

### ✅ 4. **HTML5 History Mode**

Removes the `#` from URLs (`/#/about` → `/about`).

```js
const router = new VueRouter({
  mode: 'history',
  routes
});
```

⚠️ Requires proper server config (e.g., redirecting all paths to `index.html`).

---

# 🔥 Why Routing is Crucial in SPAs

### SPA = Single Page Application

Instead of loading multiple pages from server:

* Load one HTML file
* Replace content dynamically
* Much **faster**, feels like a native app

Routing makes this possible without constantly fetching full pages.

---

# ✅ Example Structure

```html
App.vue
  ├── <router-view />
      ├── Home.vue
      ├── About.vue
      ├── User.vue (dynamic with :id)
```

---

# 🚧 Challenges with Routing

| Issue                                                                 | Solution                                         |
| --------------------------------------------------------------------- | ------------------------------------------------ |
| Search engines may not index dynamic pages                            | Use server-side rendering or prerendering        |
| Browser back/forward history may behave unexpectedly                  | Use Vue Router’s built-in history API            |
| Deep linking (refreshing on nested route) may break in `history` mode | Configure web server to redirect to `index.html` |

---

# 🧪 Real-World Use Cases

| Feature             | Example                                                         |
| ------------------- | --------------------------------------------------------------- |
| Dynamic Route       | `/product/42`                                                   |
| Nested Routes       | `/dashboard/settings`                                           |
| Navigation Guards   | Stop users if not logged in                                     |
| Lazy loading routes | Load code only when user navigates there (improves performance) |

---

## 🚀 Summary

| Topic               | Description                                     |
| ------------------- | ----------------------------------------------- |
| **Vue Router**      | Enables SPA-like page navigation                |
| **`<router-view>`** | Placeholder where matched component is rendered |
| **`<router-link>`** | Vue-aware `<a>` tag                             |
| **Dynamic Routes**  | Use `:id`, etc. to handle user-specific pages   |
| **Watchers**        | React to route changes manually                 |
| **History Mode**    | Clean URLs, requires server setup               |
| **SPA Benefit**     | Fast transitions, smooth UX                     |

