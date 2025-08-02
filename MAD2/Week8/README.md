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
