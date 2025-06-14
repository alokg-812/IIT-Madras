# Modern Application Development - II  
### Week 1 - JavaScript and Advanced Frontend Concepts  

---

## 📑 Index

1. [Review of MAD-I](#1-review-of-mad-i)
2. [Review of the Web Application Development Model](#2-review-of-the-web-application-development-model)
3. [Moving Forward](#3-moving-forward)
4. [JavaScript Overview](#4-javascript-overview)
5. [JavaScript Origins](#5-javascript-origins)
6. [JavaScript Power and Ajax](#6-javascript-power-and-ajax)
7. [Standardization - ECMAScript](#7-standardization---ecmascript)
8. [Versions and Compatibility](#8-versions-and-compatibility)
9. [JavaScript Usage and Environment](#9-javascript-usage-and-environment)
10. [DOM - Document Object Model](#10-dom---document-object-model)
11. [JavaScript Syntax Overview](#11-javascript-syntax-overview)
12. [Data Types and Strings](#12-data-types-and-strings)
13. [Operators and Comparisons](#13-operators-and-comparisons)
14. [Variables and Scoping](#14-variables-and-scoping)
15. [Control Flow](#15-control-flow)
16. [Functions and Notation](#16-functions-and-notation)
17. [Anonymous Functions and IIFE](#17-anonymous-functions-and-iife)
18. [DOM APIs and Interaction](#18-dom-apis-and-interaction)
19. [Useful References and Tools](#19-useful-references-and-tools)

---

## lECT 1:
### 1. Review of MAD-I

- **Definition of an App**: A program that lets users interact with computing systems and perform useful tasks.
- **Architecture**:
  - **Backend**: Handles data storage, logic, and relationships.
  - **Frontend**: User-facing layer that abstracts system interactions.
  - **Model**: Typically client-server, request-response pattern.

---

### 2. Review of the Web Application Development Model

- **Presentation**: HTML for semantic content, CSS for styling purposes
- **Logic Layer**: Backend logic highly flexible (we used Python with Flask)
- **Application Architecture**:
  - `Model-View-Controller`: Good compromise between understandability and flexibility
- **System Architecture**:
  - `REST principles`, session management
  - `APIs` to separate data from view
  - `Restful APIs` useful for basic understanding, but not strict adhere to *REST*
- **Other considerations**:
  - Security, authentication, validation
  - RBAC (Role-Based Access Control)
  - Database and frontend choices

---

### 3. Moving Forward

- Advanced frontend with `JavaScript`.
- `VueJS` for frontend framework.
- `JAMStack` (JavaScript, APIs, Markup)
- Other topics of interest:
  - Asynchronous messaging, Email
  - Mobile/ Standalone Apps: PWAs/ SPAs
  - Performance & optimization
  - Alternatives of REST
  - etc...

---

### 4. JavaScript Overview

- History and Origins
- JS Execution Environments:
  - Browsers and Node.js
- DOM manipulation and scripting
- Interactive usage in tools like Replit

---

## lECT 2. JavaScript Origins

- Created in **1995** for Netscape Navigator
- Designed as a **glue language**
- Tied with Java applets (hence name)
- Initially limited in scope and speed


### 1. JavaScript Power and Ajax

- **2005**: Google Maps and Suggest demonstrated dynamic capabilities
- **Ajax** coined by Garrett (Asynchronous JavaScript and XML)
- Enabled fluid, desktop-like web applications


### 2. Standardization - ECMAScript

- Move beyond Netscape needed
- Maintained by **ECMA** (Standard 262)
- `ECMA - European Computer Manufacturers Association`
- ECMAScript versions define language features
- Significant improvements in **ES6 (2015)** and yearly updates since then
- _Feature Readiness_ oriented approach.


### 3. Versions and Compatibility

- **ES6** is broadly supported with most features of modern languages(modules, scoping, class, etc.)
- Compatibility strategies:
  - Drop old browsers
  - Use polyfills
  - Use transpilers (e.g., BabelJS)
  - Package browsers with apps (e.g., Electron)


### 4. JavaScript Usage and Environment

- Not Originally meant for designed scripting
- Needs to be embedded in HTML for frontend use
- Use `Node.js` to run JavaScript from the command line


### 5. DOM - Document Object Model

- JS manipulates webpage structure via DOM
- Inputs: clicks, forms, mouse events
- Outputs: dynamic styles, content updates, drawings

#### References:
* [exploringjs.com](https://exploringjs.com/js/index.html) detailed reference material, focused on language, not frontend or GUI - very up to date.
---

## LECT 3: JavaScript Syntax Overview

- Program structure, comments, expressions
- Identifiers and keywords
- Statements (executed for effect)
- Expressions (evaluated to a value)


### 1. Data Types and Strings

- **Primitive Types**: `undefined`, `null`, `boolean`, `number`, `string`, `bigint`, `symbol`
- **Objects**: Collections of key-value pairs
- **Functions**: First-class objects
- Strings use **Unicode**, often UTF-16 encoding

---

## 13. Operators and Comparisons

- Basic arithmetic and string operations
- Type coercion:
  - `==` allows coercion
  - `===` strict comparison (no coercion)

---

## 14. Variables and Scoping

- Variable declarations:
  - `let`: block-scoped, mutable
  - `const`: block-scoped, immutable
  - `var`: function-scoped, avoid using
- Scope determines where the variable is accessible

---

## 15. Control Flow

- **Conditional Statements**: `if`, `else`
- **Loops**: `for`, `while`
- **Flow Control**: `break`, `continue`
- **Switch-case** for multiple condition matching

---

## 16. Functions and Notation

- **Declaration**:
  ```js
  function add(x, y) {
    return x + y;
  }
```
