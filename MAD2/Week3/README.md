# Week 3

## Lecture 1 Live Lecture

### Frontend Implementation
*Ques:* What is a frontend? <br>
_Ans:_ 
* The frontend is the part of a software application that users interact with directly. It’s all about `User Interface (UI)` and `User Experience (UX)`.
  * UI is what we see: buttons, images, navigation bars.
  * UX is how we feel while using the app: is it smooth, fast, intuitive?
* **✍️ eg#01:** On Amazon’s homepage, the search bar, product listings, "Add to Cart" button — all of these are frontend elements. How quickly the site loads or how easy it is to check out affects the user experience.

### Frontend Requirements:
These are design constraints for frontend:
* **No complex logic:**
  * Business logic (e.g., calculating prices, processing payments) should be handled by the backend, not the browser.
* **No data storage:**
  * Frontend should not permanently store user data. Temporary data may be held in memory or browser (cookies/local storage), but primary storage happens server-side.
* **Stateless Nature of HTTP**
  * Every HTTP request is independent. Frontend must work with this by using tokens/sessions to maintain continuity.
* **✍️ eg#02:** Imagine you're filling a form online. If your browser crashes, the backend doesn’t remember you were midway. Unless saved in the frontend temporarily (like auto-fill or form saving), it’s gone.

### Desirable Qualities of Frontend
🎨 _Aesthetically Pleasing:_ Clean visuals, intuitive layout.
<br>
⚡ _Responsive:_ Minimal delay; adapts to user inputs instantly.
<br>
📱 _Adaptive:_ Works across devices — desktops, tablets, phones.
<br>
**✍️ eg#03:** Instagram looks different on desktop vs. mobile, but it remains functional and visually pleasing on both. That’s responsive and adaptive design.


### Programming Styles
#### Imperative Programming: 
  * sequence of actions to achieve final result
  * Draw boxes for navigation, main text, fill in text, wait for clicks etc.
  * Functions for each step, composition of functions
* We write instructions step-by-step:
```javascript
  drawBox();
  fillText();
  listenForClick();
```
* We control `how` things happen.
#### 💡Analogy:
Making tea by writing out each step: boil water → add tea leaves → pour milk → stir.

### ✨ **Declarative Programming**:
* Specify desired result: We describe `what` we want, not how to do it.
* The framework handles execution.
  * Compiler / Interpreter knows how to achieve result
  * Function integration automated
* **✍️ eg#04:** This is common in React or Flutter.
```jsx
  return (
    <Button onClick={submitForm}>Submit</Button>
  );
```
#### 💡Analogy:
Telling someone “Make me a cup of tea” instead of giving them a recipe.

![image](https://github.com/user-attachments/assets/f0ef17f3-00a1-4aa1-94b7-ee736cbfff51)
* Credit: Flutter Documentation(start thinking `declarative`).

### Understanding State

* `State` means the current values/data in our system at any point.
* Types of states:
  - System State (Huge, Persistent)
  - Application State (Session-specific)
  - Ephemeral State ()

#### System State
* The full backend database.
* Independent of user sessions.
* Shared across the system. <br>
**✍️ eg#05:**
    * Flipkart’s full inventory.
    * All student data in NPTEL.

#### Application State
* User-specific session info.
* Stored temporarily and usually isolated. <br>
**✍️ eg#06:**
  * Items in your shopping cart.
  * Your preferred language or theme in an app.
  * Dashboard preferences in a data tool.

### UI State (Ephemeral)

* Exists for a short duration.
* Changes rapidly based on user actions. <br>
**✍️ eg#07:**
  * The tab we last clicked on.
  * A spinner that shows "Loading…" for 2 seconds.

### Application and UI Management

**❓Ques:** 💭 Why This Matters? <br>
**🅰️Ans:** The `frontend (browser)` and `backend (server)` communicate using **HTTP**, which is `stateless` by nature. <br>
This means:

* Each HTTP request is *independent*.
* The server doesn’t remember past interactions *unless we explicitly manage state*.
* To overcome this, developers have two strategies:
1. Client Maintains State
2. Server Maintains State

## ✅ 1. **Client Maintains State**

In this model, the **browser (client)** holds user-specific data (called **state**) and includes it with every request.

### 🔑 Technologies used:

* Cookies
* Local Storage / Session Storage
* JWT (JSON Web Tokens)

### 🔧 Example:

A user logs into Flipkart:

1. Server sends a **JWT token** after successful login.
2. Frontend stores this in **local storage**.
3. On every request (like viewing a product), this token is added in headers.
4. Server uses the token to **validate user identity** and serve personalized content.

### ✅ Advantages:

* Reduces server memory usage.
* Faster response (since state is available on client).

### ❌ Disadvantages:

* More prone to tampering (needs encryption).
* Can be lost if browser cache is cleared.

---

## ✅ 2. **Server Maintains State**

Here, the backend stores session information linked to the user.

### 🔧 Example:

After login, server stores a **session ID** in a database table:

```json
{
  sessionId: "xyz123",
  userId: 42,
  cart: ["apple", "mango"]
}
```

* Frontend gets a cookie containing just the session ID (`xyz123`).
* Every future request includes that cookie.
* Server uses it to pull session data and respond accordingly.

### ✅ Advantages:

* Safer: Server fully controls the session.
* Useful for complex workflows like payments, orders, etc.

### ❌ Disadvantages:

* Server needs to track and clean up inactive sessions.
* Less scalable without proper session management.

---

# 🎮 Example: Tic-Tac-Toe

Let’s use a familiar example to illustrate frontend-backend interaction.

---

### 🖥️ **Frontend Role:**

* Draws the 3x3 grid.
* Captures clicks from Player X or O.
* Sends the move to the backend via API.
* Displays updated board.

```js
// Pseudo-code (React or JS)
onClick(cellIndex) {
   sendMoveToServer(cellIndex);
}
```

---

### 🔙 **Backend Role:**

* Receives the cell that was clicked.
* Validates the move (Is it valid? Is it X's turn?).
* Updates the game state.
* Checks for a winner.
* Sends back the updated board and game status.

---

### 🔁 Communication:

```json
Client → Server:
POST /move
{
  "gameId": "abc123",
  "cell": 4
}

Server → Client:
{
  "updatedBoard": ["X", null, null, null, "O", null, null, null, null],
  "status": "ongoing",
  "nextTurn": "X"
}
```

---

### 🧠 Real-World Insight:

This reflects how real-time games, dashboards, and collaborative apps work:
State is either managed in frontend, backend, or both — depending on use case.




## Introduction to VueJS







