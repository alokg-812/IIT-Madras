# Week 8

# 🌐 What is an API?

**API (Application Programming Interface)** is:

> A set of rules that lets applications talk to each other.

In the web world, a **Web API** is usually a set of **URLs** that return or manipulate **data** (e.g., in JSON), often used by frontend apps, mobile apps, or other services.

---

# 🛠️ What Makes a Good API?

> “Good APIs are designed for **developers**, not end-users.”

While there's no strict rulebook, great APIs are:

| Property         | Description                                    |
| ---------------- | ---------------------------------------------- |
| **Consistent**   | Follows naming and structure conventions       |
| **Intuitive**    | Easy to understand and guess                   |
| **Discoverable** | Easy to explore via tools or documentation     |
| **Flexible**     | Allows clients to access only what they need   |
| **Secure**       | Uses standard auth methods (OAuth2, JWT, etc.) |

🧠 Reference: [Google’s Apigee eBook](https://cloud.google.com/apigee)

---

# 🏗️ Designing a RESTful API (Resource-Oriented)

REST APIs are the most common style of web API.

## ✅ Data-Oriented Design

Think in **entities** and **actions**.

### Example Entities:

* Students
* Courses
* Grades

### Basic Actions:

* Add
* Edit
* Delete
* Get summary (e.g., GPA)

---

## 🧭 Conventions: URLs + HTTP Methods

Use **nouns** in URLs and **verbs** as HTTP methods.

| Action              | URL             | Method   |
| ------------------- | --------------- | -------- |
| Get all students    | `/students`     | `GET`    |
| Get student by ID   | `/students/123` | `GET`    |
| Create new student  | `/students`     | `POST`   |
| Update student info | `/students/123` | `PATCH`  |
| Delete student      | `/students/123` | `DELETE` |

❌ Bad example:
`/getListOfStudents`, `/editStudent?id=123`

✅ Good example:
`GET /students`, `PATCH /students/123`

---

## 🧮 Query Parameters

Use them for **filtering or searching**:

```bash
/search?course=CS101&type=student
```

Better yet, structure the URL:

```bash
/courses/CS101/students
```

---

## 🧪 Output Format: JSON (Mostly)

### JSON is:

* Simple
* Human-readable
* Easy to parse in any programming language

🔸 Example response from GitHub API:

```json
{
  "login": "alokg812",
  "id": 12345,
  "url": "https://api.github.com/users/alokg812",
  "repos_url": "https://api.github.com/users/alokg812/repos"
}
```

🧩 Tip: Include helpful **links** in your response (`repos_url`, `followers_url`) to guide developers!

---

## 🔐 Authentication

### ✅ Common approaches:

* **API Keys** – simple, not very secure
* **OAuth2** – standard for most major services (Google, GitHub)
* **JWT (JSON Web Token)** – compact, self-contained token

Use industry-standard methods so developers don't have to reinvent the wheel.

---

# ⚠️ Problems with REST APIs

Even well-designed REST APIs have some challenges:

| Problem                            | Example                                                            |
| ---------------------------------- | ------------------------------------------------------------------ |
| **Too many requests**              | Want to fetch student + all their courses + GPA? Need 3+ API calls |
| **Over-fetching / under-fetching** | You may get too much data, or not enough                           |
| **Limited query expressiveness**   | You can't describe complex queries easily in a single request      |

---

# 🌟 GraphQL: A Smarter API Option

---

## ❓ Why GraphQL?

GraphQL solves REST’s limitations:

✅ Ask for **exactly the data** you need
✅ Send **one request** instead of multiple
✅ Get **multiple related entities** in one query
✅ Great for **modern apps** (mobile, SPAs, dashboards)

---

## 🔧 How GraphQL Works

* Uses **POST** request with a **query string**
* Backend engine parses the query, fetches data, and responds with just what was asked

---

## 📘 GraphQL Query Example

```graphql
{
  student(id: "123") {
    name
    courses {
      name
      grade
    }
  }
}
```

🔁 Response:

```json
{
  "data": {
    "student": {
      "name": "Alok",
      "courses": [
        { "name": "Math", "grade": "A" },
        { "name": "Physics", "grade": "B+" }
      ]
    }
  }
}
```

---

## 📐 GraphQL Schema and Types

GraphQL has a **type system**:

```graphql
type Student {
  id: ID!
  name: String!
  courses: [Course]
}

type Course {
  name: String
  grade: String
}
```

You define:

* **Queries** (read operations)
* **Mutations** (create/update/delete)
* **Resolvers** (functions that fetch the data)

---

## 🔨 Tools for Building GraphQL APIs

| Tool                              | Purpose                              |
| --------------------------------- | ------------------------------------ |
| **Apollo Server**                 | Popular GraphQL backend library      |
| **GraphiQL / GraphQL Playground** | GUI to explore and test GraphQL APIs |
| **Relay / Apollo Client**         | GraphQL frontend libraries           |

---

# 🔄 API Versioning

* REST APIs often use versioned URLs: `/api/v1/users`
* GraphQL doesn’t need this: new fields can be added without breaking clients
* Deprecate old fields when needed

---

# 🧱 API Summary

| Feature              | REST               | GraphQL             |
| -------------------- | ------------------ | ------------------- |
| Structure            | URL-based          | Query-based         |
| Response size        | Fixed              | Precise control     |
| Queries              | Multiple endpoints | Single request      |
| Flexibility          | Medium             | High                |
| Developer experience | Familiar           | Powerful            |
| Caching              | Easy with URLs     | Harder but possible |
| Tooling              | Widespread         | Growing rapidly     |

---

# ✨ Best Practices for API Design

✅ Use **RESTful** or **GraphQL** thoughtfully — choose based on needs
✅ Stick to conventions for naming, methods, structure
✅ Always include **clear documentation**
✅ Use **auth standards** — no hardcoded secrets
✅ Test and version your API carefully
✅ Include **useful links** in responses
✅ Think of the API as a **developer-facing product**


# 🔷 What is GraphQL?

> **GraphQL** is a query language for APIs and a runtime for fulfilling those queries with your existing data.

It was developed by **Facebook in 2012** and released in **2015** as open-source.

---

## 🔍 Why was GraphQL created?

### Problems with traditional REST APIs:

1. **Over-fetching** – Getting more data than needed
2. **Under-fetching** – Needing to make multiple requests to get all required data
3. **Rigid structure** – One endpoint = one data shape
4. **High latency in complex apps** – Especially mobile or dashboard apps

### Solution?

> A flexible system where clients **ask for exactly what they want**.

---

# 🧱 Core Concepts of GraphQL

---

## 🔸 1. **Schema**

A GraphQL API starts with a **schema** – it defines:

* Types (e.g., `User`, `Post`)
* Relationships
* What queries/mutations can be made

### Example:

```graphql
type User {
  id: ID!
  name: String!
  posts: [Post]
}

type Post {
  id: ID!
  title: String!
  content: String
  author: User
}
```

---

## 🔸 2. **Queries**

Clients use **GraphQL queries** to request data from the server.

### Example:

```graphql
{
  user(id: "123") {
    name
    posts {
      title
    }
  }
}
```

📦 The response:

```json
{
  "data": {
    "user": {
      "name": "Alok",
      "posts": [
        { "title": "My First Post" },
        { "title": "Vue vs React" }
      ]
    }
  }
}
```

✅ Only the requested fields are returned
✅ No need for `/user/123`, `/user/123/posts`, etc.

---

## 🔸 3. **Mutations**

Used to **change data** (Create, Update, Delete). Like `POST`, `PATCH`, `DELETE` in REST.

### Example:

```graphql
mutation {
  createUser(name: "Akanksha") {
    id
    name
  }
}
```

Returns:

```json
{
  "data": {
    "createUser": {
      "id": "456",
      "name": "Akanksha"
    }
  }
}
```

---

## 🔸 4. **Resolvers**

> Functions on the backend that resolve each field in your schema.

Example:

```js
const resolvers = {
  Query: {
    user: (parent, args, context, info) => {
      return database.getUserById(args.id);
    }
  },
  User: {
    posts: (user) => {
      return database.getPostsByUser(user.id);
    }
  }
};
```

---

## 🔸 5. **Type System**

GraphQL is **strongly typed**.

### Common types:

| Type                      | Description                            |
| ------------------------- | -------------------------------------- |
| `ID`                      | Unique identifier                      |
| `String`                  | Text                                   |
| `Int`, `Float`, `Boolean` | Primitives                             |
| `[Type]`                  | List of values                         |
| `!`                       | Non-null (e.g. `String!` = must exist) |

---

## 🔸 6. **Fragments**

Used to **reuse parts** of queries.

```graphql
fragment postFields on Post {
  title
  content
}

query {
  user(id: "123") {
    posts {
      ...postFields
    }
  }
}
```

---

## 🔸 7. **Variables**

GraphQL supports variables so you don’t hardcode values.

```graphql
query getUser($userId: ID!) {
  user(id: $userId) {
    name
  }
}
```

Used with:

```json
{
  "userId": "123"
}
```

---

# 🧰 Tools in GraphQL Ecosystem

| Tool                              | Use                                             |
| --------------------------------- | ----------------------------------------------- |
| **Apollo Server**                 | Node.js GraphQL backend                         |
| **Apollo Client**                 | Frontend GraphQL client for React/Vue/Angular   |
| **GraphQL Yoga**                  | Full-featured backend server                    |
| **GraphiQL / GraphQL Playground** | In-browser IDE for testing queries              |
| **Hasura**                        | Auto-generates GraphQL from PostgreSQL database |
| **Relay**                         | Facebook’s GraphQL client (more complex)        |

---

# ⚙️ GraphQL vs REST

| Feature            | REST                 | GraphQL                       |
| ------------------ | -------------------- | ----------------------------- |
| Structure          | Multiple endpoints   | Single endpoint               |
| Data               | Fixed response shape | Client defines shape          |
| Overfetching       | Common               | None                          |
| Multiple Resources | Multiple requests    | Single query                  |
| Caching            | Easy (via URLs)      | Harder (requires extra logic) |
| Learning curve     | Lower                | Higher                        |

---

# 🔐 Authentication in GraphQL

GraphQL doesn’t dictate auth — you use standard web methods:

✅ **JWT tokens**
✅ **OAuth2 flows**
✅ Add auth checks inside resolvers:

```js
if (!context.user) throw new Error("Not Authenticated");
```

---

# 📌 Real-World Use Case Example

## Let’s say you’re building a **student portal**.

### REST:

* GET `/students`
* GET `/students/42`
* GET `/students/42/courses`
* GET `/courses/CS101`
* GET `/students/42/grades`

### GraphQL:

```graphql
query {
  student(id: "42") {
    name
    courses {
      name
      grade
    }
  }
}
```

One query. One response. Clean, structured, efficient.

---

# 🌍 GraphQL + Vue Example

Install:

```bash
npm install @apollo/client graphql
```

Connect to GraphQL backend and run:

```js
import { useQuery, gql } from '@apollo/client';

const GET_USERS = gql`
  {
    users {
      id
      name
    }
  }
`;

const { loading, error, data } = useQuery(GET_USERS);
```

---

# 🚀 Advanced Features

✅ **Subscriptions** – real-time updates over WebSockets
✅ **Introspection** – APIs are self-documenting
✅ **Schema stitching** – combine multiple GraphQL APIs into one
✅ **Federation** – modular GraphQL APIs across teams (used by Netflix, Airbnb)

---

# ⚠️ Challenges of GraphQL

| Challenge                | Solution                                 |
| ------------------------ | ---------------------------------------- |
| Complex backend          | Use code generators or Hasura            |
| Caching harder           | Use Apollo cache or client-side logic    |
| Learning curve           | Start small (query-only), grow gradually |
| Batching and performance | Use Dataloader for optimized DB calls    |

---

# ✅ Summary: Why Use GraphQL?

✅ Ask for exactly the data you want
✅ Combine multiple resources into one query
✅ Easier API evolution (no breaking changes)
✅ Better developer experience (auto-docs, IDEs)
✅ Ideal for frontend-heavy apps and mobile apps
