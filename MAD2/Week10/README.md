### What is Inter-service Messaging?

* When you have **multiple services** (frontend, backend, database, email sender, image processor, etc.), they need to **communicate with each other**.
* Instead of calling each other directly (tight coupling), we often use **messaging mechanisms**.
* Two main forms:

  1. **Message Queues** (asynchronous, reliable, ordered)
  2. **Lightweight APIs / Webhooks** (direct HTTP calls, simpler but synchronous)


### Message Queues (Recap)

* **Where used**: Services running together, often in the same cluster/server.
* **Characteristics**:
  * **Asynchronous** → sender and receiver don’t wait on each other.
  * **Guarantees delivery** → messages stored in queue until processed.
  * **Order maintained** → transactions handled in sequence.
* **Examples**: RabbitMQ, Kafka, AWS SQS.

👉 Good when services need **reliability + decoupling**.


### Internet-Distributed Services

* Problem: When services are spread across the **internet**, you may not have a common queue or broker.
* Instead, they expose **endpoints** that accept incoming messages.
* Delivery/ordering guarantees may not be strict.
* Uses **lightweight HTTP messaging**.


### Lightweight API Calls

* **Mechanism**: One service calls another’s endpoint (usually `POST`) just to send a message (not fetch data).
* Payload can be small or empty.
* Example:

  * GitHub → (on commit) → sends POST to your server → your server pushes update to Google Chat.
  * Twilio → (after sending SMSs) → calls back your webhook with delivery status.

👉 This is the **core of inter-service messaging on the internet**.


## Webhooks

### What is a Webhook?

* A **webhook** is a way for one application (Service A) to send **real-time information** to another application (Service B).
* It’s basically a **reverse API call**:
  * In APIs, the client **pulls** data from the server.
  * In webhooks, the server **pushes** data to another server.
* Uses **standard HTTP protocols** (mainly `POST`, sometimes `GET`).
* Also called: **HTTP Push API** or **Web Callback**.

👉 Think of it as: *"Don’t call me, I’ll call you when something happens."*


### How it Works

1. **Receiver (Your App/Server)** creates an HTTP endpoint (URL) that can accept requests.
   * Example: `https://myapp.com/github-events`

2. **Provider (External Service)** is configured with that URL.
   * Example: GitHub → Settings → Webhooks → enter your server URL.

3. When an event happens in the provider system, it makes an **HTTP request** (usually `POST`) to your server’s webhook endpoint with event data in the request body.
4. Your server **processes the event** and responds with an HTTP status (`200 OK`, or error code).


### Characteristics of Webhooks

* **One-way communication**
  * Sender → Receiver only.

* **Synchronous**
  * The sender expects an immediate response (even if minimal).

* **No storage/retry**
  * If your server is down, the webhook may be lost (unless the provider has retry logic).

* **Lightweight**
  * Designed for **small payloads**, not large data transfer.

* **Machine-triggered**
  * Requests come from a **server**, not a human client.


### Webhook Request & Response

#### Request (from provider → receiver)

* Sent over HTTP (POST preferred).
* **Headers**: may include metadata or authentication token.
* **Body**: JSON/XML payload describing the event.
  Example (GitHub webhook payload for a push event):

  ```json
  {
    "ref": "refs/heads/main",
    "before": "6dcb09...",
    "after": "1481a2...",
    "repository": {
      "id": 12345,
      "name": "my-repo",
      "owner": { "login": "user123" }
    }
  }
  ```

#### Response (from receiver → provider)

* Minimal — usually just status code:
  * `200 OK` → processed successfully.
  * `4xx` → failure (e.g., unauthorized).
  * `5xx` → server error.
* Provider ignores response body (if any).


### Examples of Webhooks

* **GitHub → Slack**
  * Every time someone pushes to a repo, GitHub sends a webhook to a Slack bot URL → Slack posts the update in a channel.

* **Twilio SMS**
  * You send SMS using Twilio. Instead of polling for delivery status, you give Twilio a webhook URL → Twilio calls back with SMS delivery results.

* **Payment Gateway (Razorpay/Stripe/PayPal)**
  * After a transaction, the payment gateway sends a webhook to your backend to confirm payment success/failure.


### Webhooks vs Alternatives

| Technique                          | Nature                  | Usage                               | Drawbacks                               |
| ---------------------------------- | ----------------------- | ----------------------------------- | --------------------------------------- |
| **Webhooks**                       | Push (HTTP POST)        | Server-to-server event notification | Synchronous, no retries unless built in |
| **Websockets**                     | Persistent 2-way        | Real-time apps (chat, games)        | Requires open connection                |
| **Polling**                        | Pull repeatedly         | Simple, no config                   | Inefficient, wastes resources           |
| **Pub/Sub (Kafka, Google PubSub)** | Async distributed queue | Large scale message systems         | Overkill for simple notifications       |


### Debugging Webhooks

* **requestbin.com** → Creates a temporary URL to receive and inspect webhook payloads.
* **Postman / curl** → Simulate incoming webhook requests.
* **ngrok** → Expose your local server to the internet (test webhooks without deploying).


### Securing Webhooks

Since webhooks are public-facing, they must be secured. Common strategies:

1. **Secret token in header**
   * Example: GitHub includes an `X-Hub-Signature` header.
   * Your server validates it to ensure authenticity.
2. **API key or access token** in the request.
3. **Restrict by IP** (only allow requests from provider’s IP range).


### Best Practices

* Always return `200 OK` quickly, even if processing takes time → use background workers.
* Log webhook payloads for debugging.
* Validate authenticity (signatures/tokens).
* Keep payload small and only send essential data.


✅ **In short:**
Webhooks are the **simplest, lightweight way** for services to talk to each other in real-time over the internet. They’re widely used for **notifications, event triggers, and integrations**.


## Push to Client (Detailed Explanation)

### The Problem

* The **original HTTP protocol** is **stateless** and **request-response based**.
  * Client → Server → Response → Connection closed.
  * No mechanism for the server to **proactively contact the client**.

* But modern apps need **real-time updates**:
  * Chat messages
  * Live scores
  * Order status / delivery updates
  * Notifications

👉 This is why we need **push mechanisms** to update the client without the client constantly asking.


### Options for Client Updates

There are 4 main techniques:

#### (A) Polling

* The **client repeatedly asks the server**: *“Any new updates?”*
* **Fixed interval polling** (e.g., every 5 seconds).

* **Pros**: Simple to implement.

* **Cons**:
  * Server may not have updates → wasted requests.
  * Scales poorly (if millions of clients poll at once).

#### (B) Long Polling

* An improvement over regular polling.
* Client sends a request and the server **keeps it open** until there is an update.
* When an update happens → server responds → client immediately reopens another long poll.

* **Pros**: Efficient, reduces wasted requests.

* **Cons**:
  * Server must keep many open connections (resource intensive).
  * Still a “hacky” way to simulate push.

👉 Example: Early versions of chat apps used this (Facebook Chat, GMail notifications).


#### (C) Server-Sent Events (SSE)

* A proper **HTTP standard** for server-to-client push.
* The server sends **continuous event streams** over a single HTTP connection.
* Client subscribes using an `EventSource` in JavaScript.

* **Pros**:
  * Real one-way push from server → client.
  * Works over HTTP (firewall friendly).
  * Lightweight compared to WebSockets.

* **Cons**:
  * Only supports server → client (not bidirectional).
  * Not supported in some old browsers.

👉 Example use: Stock tickers, live news feeds, dashboards.

#### (D) Web Push Notifications

* For actual **device/browser notifications** (even if browser is closed).
* Uses the **Push API** + **Service Workers**.
* Steps:
  1. Browser/app registers with a push service (like Firebase Cloud Messaging, Apple Push).
  2. Server sends push message → push provider → client device.
  3. Client’s service worker shows a notification.

* **Pros**:
  * Works even if the app isn’t open.
  * Supports urgency/priority levels.

* **Cons**:
  * Requires registration & authentication with providers (Google, Apple).
  * More setup compared to polling/SSE.

👉 Example: WhatsApp web notifications, food delivery app updates, YouTube notification alerts.

### Push vs Pull

* **Pull (Polling)**: Client asks → Server responds.
* **Push (SSE, Push API)**: Server sends updates automatically when something happens.

**Examples**
* **Polling**: A weather app refreshing data every 10 minutes.
* **Long Polling**: A simple online chat app where messages arrive with delay.
* **SSE**: A live cricket score website continuously streaming updates.
* **Push Notifications**: Swiggy/Zomato sending “Your order is out for delivery” as a browser pop-up.


### Summary Table

| Technique              | Direction                      | Real-time?         | Efficiency | Use Case                |
| ---------------------- | ------------------------------ | ------------------ | ---------- | ----------------------- |
| **Polling**            | Client → Server                | ❌ (delayed)        | Low        | Simple apps             |
| **Long Polling**       | Client → Server (kept open)    | ✅ (near real-time) | Medium     | Chats, notifications    |
| **SSE**                | Server → Client                | ✅                  | High       | Feeds, dashboards       |
| **Push Notifications** | Server → Client (via provider) | ✅                  | Very High  | Alerts, mobile/web apps |


✅ **In short:**

* Polling/Long Polling = client-driven hacks.
* SSE = true HTTP push (one-way).
* Push API = full notification system (via providers like Firebase/Apple).
