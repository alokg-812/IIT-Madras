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

## 1. Review of MAD-I

- **Definition of an App**: A program that lets users interact with computing systems and perform useful tasks.
- **Architecture**:
  - **Backend**: Handles data storage, logic, and relationships.
  - **Frontend**: User-facing layer that abstracts system interactions.
  - **Model**: Typically client-server, request-response pattern.

---

## 2. Review of the Web Application Development Model

- **Presentation**: HTML (content), CSS (style)
- **Logic Layer**: Backend (e.g., Flask with Python)
- **MVC Architecture**:
  - **Model-View-Controller** structure
- **System Architecture**:
  - REST principles, session management
  - APIs to separate data from view
- **Other considerations**:
  - Security, authentication, validation
  - RBAC (Role-Based Access Control)
  - Database and frontend choices

---

## 3. Moving Forward

- Advanced frontend with **JavaScript** and **VueJS**
- JAMStack (JavaScript, APIs, Markup)
- Other topics:
  - Async messaging, Email
  - PWAs, SPAs
  - Performance & optimization
  - REST alternatives

---

## 4. JavaScript Overview

- History and Origins
- JS Execution Environments:
  - Browsers and Node.js
- DOM manipulation and scripting
- Interactive usage in tools like Replit

---

## 5. JavaScript Origins

- Created in **1995** for Netscape Navigator
- Designed as a **glue language**
- Tied with Java applets (hence name)
- Initially limited in scope and speed

---

## 6. JavaScript Power and Ajax

- **2005**: Google Maps and Suggest demonstrated dynamic capabilities
- **Ajax** coined by Garrett (Asynchronous JavaScript and XML)
- Enabled fluid, desktop-like web applications

---

## 7. Standardization - ECMAScript

- Maintained by **ECMA** (Standard 262)
- ECMAScript versions define language features
- Significant improvements in **ES6 (2015)** and yearly updates since

---

## 8. Versions and Compatibility

- ES6 is broadly supported
- Compatibility strategies:
  - Drop old browsers
  - Use polyfills
  - Use transpilers (e.g., BabelJS)
  - Package browsers with apps (e.g., Electron)

---

## 9. JavaScript Usage and Environment

- Originally not designed for terminal use
- Needs to be embedded in HTML for frontend use
- Use Node.js to run JavaScript from the command line

---

## 10. DOM - Document Object Model

- JS manipulates webpage structure via DOM
- Inputs: clicks, forms, mouse events
- Outputs: dynamic styles, content updates, drawings

---

## 11. JavaScript Syntax Overview

- Program structure, comments, expressions
- Identifiers and keywords
- Statements (executed for effect)
- Expressions (evaluated to a value)

---

## 12. Data Types and Strings

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
