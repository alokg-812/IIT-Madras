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


### 2. Operators and Comparisons

- Basic arithmetic and string operations
- Type coercion:
  - `==` allows coercion
  - `===` strict comparison (no coercion)



### 3. Variables and Scoping

- Variable declarations:
  - `let`: block-scoped, mutable
  - `const`: block-scoped, immutable
  - `var`: function-scoped, avoid using
- Scope determines where the variable is accessible



## LECTURE 4:
Basic Frontend Usage

* **Frontend Javscript**: must be invoked from HTML Pages.
  - In context of `Document`.
  - Will not execute if loaded `directly`.
* **Scripting Language**: no compilation step.
* **Loosely Structured**: no specific header, body, etc like in c/c++.

### Identifiers
- **Reserved Words:** 
  - `await`, `break`, `case`, `catch`, `class`, `const`, `continue`, `debugger`, ..., `yield`.(total of 36)
- **Literals:(Values)**
  - `null`, `undefined`, `true`, `false`, `NaN`, `Infinity`
- **Others to avoid:**
  - `eval`, `arguments`, `caller`, `length`, `package`, `prototype`, `public`, etc...

### Variables and Scoping
- Any non-reserved identifier can be used as a `placeholder` or `variable`
- **Scope**:
  - Should the variable be visible everywhere in all scripts or only in a specific area?
  - Namespaces and limiting scope
- `let`, `const` are used for declaring variables
  - Unlike Python, variables MUST be declared
  - Unlike C, their type need NOT be declared
  - `var` was originally used for declaring variables, but has function level scope - avoid

### Control Flow
- **Conditional Statements**: `if`, `else`
- **Loops**: `for`, `while`
- **Flow Control**: `break`, `continue`
- **Switch-case** for multiple condition matching
- **Try-catch** for error handling

### Functions

#### Regular Function
- **Function Declaration**: `function name(parameters) { body }`
#### Named variable Function
- **Function Expression**: `let name = function(parameters) { body }`
#### Arrow Function
- **Function Expression**: `let name = (parameters) =>{}`

#### IIFEs
- **Immediately Invoked Function Expression**: Avoided in modern days because of their poor readability.
```js
(function(){ return 'Hello World'}) // declare and invoked
```

## LECTURE 6 DOM

### Interaction
* `console.log()` is very limited
* But JS was designed for document manipulation
* **Inputs from DOM:** Mouse, text, clicks
* **Outputs to DOM:** Manipulations of text, colours, etc.

[Example-code]()