# Week 11

## Performance

### What is Performance?

Performance can be looked at in two dimensions:

* **Speed** → How fast a single user perceives the app/website.
* **Scaling** → How well the system handles many users at once (we’ll skip this now, since you asked *before scaling*).
So here we focus on **speed** → the single-user experience.

### Speed & User Experience

* **UI (User Interface)** vs **UX (User Experience)**

  * UI = how things look (design, layout).
  * UX = how smooth and fast the interaction feels.

👉 Even with a good UI, a **slow response kills the UX**.

* **Quick response is critical**
  * Users don’t like waiting for page loads.
  * Long delays cause confusion, frustration, and abandonment.

### Factors Affecting Speed

1. **Network Conditions**
   * Mobile vs broadband
   * Distance from server
   * Network congestion

2. **Number of Requests**
   * Too many HTTP requests = slower load.

3. **Size of Responses**
   * Larger images, heavy scripts → slower.

4. **HTTP Version**
   * HTTP/1: one request per connection (slower).
   * HTTP/2: multiplexing, keep-alive (faster).
   * HTTP/3: further optimizations.

5. **Compression**
   * Gzip/Brotli compress responses → reduces size, speeds up load.


### Measuring Performance

* Famous saying: *“You can’t optimize what you can’t measure.”*
* We need tools to **measure site speed** systematically.

#### Lighthouse (Google Chrome built-in tool)

* Loads a page (with all resources).
* Measures **time + memory usage**.
* Can **flush caches** to simulate first-time visits.
* Can **emulate network throttling** (e.g., 3G mobile vs broadband).
* Can **emulate devices** (mobile vs desktop).

* Produces a **weighted score** across:
  * Performance
  * Accessibility
  * Best Practices
  * SEO
  * Progressive Web App (if relevant)

👉 Essentially, Lighthouse gives a **report card** for your webpage.

### Performance Metrics

Here are the most common metrics Lighthouse and modern tools check:

* **First Contentful Paint (FCP)**
  * When *something* (text/image) first appears.

* **First Meaningful Paint (FMP)**
  * When *useful content* (not just background) is shown.

* **Speed Index**
  * Measures how quickly the visible parts of a page load.

* **Largest Contentful Paint (LCP)**
  * When the *biggest visible element* is rendered.

* **Time to Interactive (TTI)**
  * When the page becomes usable (buttons/links actually work).

* **Total Blocking Time (TBT)**
  * Time the main thread is blocked (often due to heavy JavaScript).

* **Cumulative Layout Shift (CLS)**
  * Measures how much the layout *shifts* after rendering (annoying issue where things move around after load).

👉 Together, these metrics define how smooth and fast the user perceives the site.

### Other Aspects Checked

* **Accessibility**
  * Alt text for images, color contrast, screen-reader support, etc.

* **Best Practices**
  * Proper image sizes, HTTPS usage, security practices, etc.

* **Search Engine Optimization (SEO)**
  * Proper meta tags, descriptive titles, alt attributes.


### Problems with Automated Metrics

* Tools like Lighthouse are useful, but **not perfect**.
* Example: Gmail scores *poorly* on Lighthouse performance but is still a **great app** (because real usability depends on context, not just raw speed).
* Another example: People have built deliberately **bad sites** that still score *perfectly* on Lighthouse by gaming the metrics.

👉 So metrics are helpful, but you should also test **real-world usability**.

✅ **Summary of “Before Scaling” section**

* Focus is on **Speed** and **User Experience**.
* Main factors: **network, requests, response size, HTTP version, compression**.
* Use **Lighthouse** (and similar tools) to measure performance.
* Key metrics: **FCP, LCP, TTI, CLS, etc.**
* Don’t rely blindly on scores → always consider **real user experience**.

## Scaling

### What is Scaling?

Scaling means:
👉 How your system performs **under increased load** (more users, more requests).
* **Speed** = single-user experience.
* **Scaling** = multi-user experience.

So, even if your site feels fast to one user, it may crash or slow down when **10,000 users** come at once.

### Key Dimensions of Scaling

1. **Response under load (requests per second)**
   * Constant high-rate systems: e.g., Google search, Bing.
   * They need to serve **millions of queries every second** consistently.

2. **Response under sudden spikes**
   * Traffic may suddenly jump.
   * Examples:
     * CBSE exam results day → millions log in at once.
     * IPL final → traffic surges during key match moments.

3. **Predictable vs Unpredictable Spikes**
   * **Predictable** → exam results, final matches (prepare in advance).
   * **Unpredictable** → sudden events (like a batsman about to hit a century).

👉 Systems must be designed to handle **both steady and burst loads**.

## Components of an App (relevant to scaling)

Scaling depends on **all layers of the application**:
**Server, Network, and Application itself.**

### Server Components

#### Frontend Server
* Handles client connections.
* Serves **HTML, CSS, JS**.

#### Database Server
* Stores models/data.
* Frontend queries it.

#### Load Balancer
* Distributes requests across multiple frontend servers.
* Simple strategies: **round-robin**, **least load**, etc.
* Cloud providers (AWS ELB, Google Cloud Load Balancing) can spread across VMs, containers, even across data centers.

#### Proxy Servers
* Intermediate servers between client and main server.
* Can cache responses → reduce load on main server.
* **CDN (Content Delivery Network)** is a special type of proxy, optimized for static content delivery (images, CSS, JS).

### Network Factors

* **Mobile networks**
  * Speed, signal quality, congestion, movement affect performance.

* **Broadband networks**
  * Shared connections, cable quality, and upstream providers matter.
👉 Even if the server is fast, poor network conditions slow down the user experience.

### Application-Level Factors

* **Data intensive apps**
  * Large datasets (like analytics, ML-based apps) slow down queries.

* **Image/script intensive apps**
  * Too many heavy images or JS files increase page load time.

* **Browser/client-side limitations**
  * Weak devices (low RAM, old browsers) may struggle with heavy apps.

### Database Choices & Scaling

* Databases differ in **read vs write performance**.
* Examples:
  * **SQLite** → lightweight, great for reads, poor for scaling writes.
  * **PostgreSQL, MySQL, Oracle** → relational DBs, can scale with replication/sharding.
  * **MongoDB, Cassandra, DynamoDB** → NoSQL DBs designed for **horizontal scaling**.
👉 Issues: **synchronization & replication** (keeping multiple copies consistent).


### Programming Language & Runtime

* **Interpreted languages** (Python, JavaScript):
  * Easier to develop, but slower.

* **Compiled languages** (C, Golang, Java):
  * Harder to develop, but much faster.

* **Concurrency models** matter:
  * Python → threading/async limited by GIL (though frameworks like asyncio help).
  * Golang → goroutines (lightweight threads, highly scalable).
  * Erlang/Elixir → built-in concurrency & fault tolerance.

* **Programming paradigms**:
  * Functional (Elixir, Haskell) → good for concurrency.
  * Declarative (SQL) → good for data querying.
  * Imperative (C, Java) → good for performance-critical code.

👉 The **language + concurrency model** can affect how well the app scales.

### Monitoring and Measuring Scaling

* You can’t improve scaling without measuring it.
* **Logs** → give post-facto analysis (what happened after a failure).
* **Live monitoring tools**:

  * **ELK stack (Elasticsearch, Logstash, Kibana)** → log aggregation & visualization.
  * **Grafana + InfluxDB + Prometheus** → monitor real-time metrics (CPU, memory, response times).

👉 Monitoring is essential to detect bottlenecks early and adjust capacity.


### ✅ Summary (Scaling + Components)

* **Scaling** = how well your system handles **many users + spikes**.
* Needs coordination across **server architecture, network reliability, database design, language/runtime efficiency, and monitoring tools**.
* Key strategies:

  * **Load balancing**
  * **Proxy/CDNs**
  * **Choosing the right database**
  * **Efficient languages + concurrency models**
  * **Continuous monitoring**


## Caching

### What is Caching?

* **Caching = storing a response** so that it can be reused later, instead of recomputing or refetching it.
* Goal: **reduce repeated work → improve speed & scalability**.
* Caching can happen at different layers:
  * **Server** → app server stores results of expensive operations.
  * **Proxy/frontend** → reverse proxies or CDNs cache responses.
  * **Network router** → may cache DNS or HTTP responses.
  * **Client** → browser caches images, scripts, responses.
👉 It’s a **core part of REST architecture** → REST encourages caching of responses wherever possible.

### Server Support for Caching

HTTP supports caching via headers:

* **Cache-Control header**
  * `max-age` → how long response can be cached.
  * `no-cache` → doesn’t mean *no caching*; means client must revalidate.
* **E-Tag header** (Entity Tag)
  * Unique ID for a resource version.
  * Helps cache check if it has the latest copy.
* **Freshness checking**
  * If no explicit expiry, cache estimates freshness.
* **Explicit revisions**
  * Versioning resources in URL, e.g.

    ```
    https://cdnjs.cloudflare.com/ajax/libs/vue/3.2.31/vue.cjs.js
    ```

👉 This ensures browsers/CDNs can cache aggressively but still get updates when needed.


### Is Caching Bad for Website Popularity?

* **Misconception**: If responses are cached, fewer requests hit my server → less “traffic.”
* Reality: That’s **good**!

  * Server load is reduced.
  * Users get faster responses.
* Analytics can still be collected via indirect methods (not dependent on raw server hits).


### Caching in Flask (Python Example)

Flask provides extensions for caching (e.g., `Flask-Caching`).

#### Caching Views

```python
@app.route("/")
@cache.cached(timeout=50)
def index():
    return render_template("index.html")
```

* Decorator caches the output of `index()` for 50 seconds.

#### Cache Key
* Works like a dictionary: **key → value**.
* For view functions, the **route path** is the key.

### Caching Non-view Functions

We
```python
@cache.cached(timeout=50, key_prefix='all_comments')
def get_all_comments():
    comments = do_serious_dbio()
    return [x.author for x in comments]
```

* First call → runs DB query, stores result.
* Later calls (within 50s) → served from cache.


### Memoization

* **Memo = note to be remembered.**
* Memoization = cache function results based on arguments.

Example:

```python
class Person(db.Model):
    @cache.memoize(50)
    def has_membership(self, role_id):
        return Group.query.filter_by(user=self, role_id=role_id).count() >= 1
```

* Different `role_id` values get **separate cache entries**.

### Jinja Caching (Template Caching)

Cache entire HTML blocks in templates:

```jinja
{% cache 60*5 %}
<div>
    <form>
      {% render_form_field(form.username) %}
      {% render_submit() %}
    </form>
</div>
{% endcache %}
```

* Cached for 5 minutes.
* Useful for expensive UI elements (like dashboards).

### Caching Backends

Different backends can store cached data:

* **NullCache** → no caching (testing only).
* **SimpleCache** → Python dictionary (not thread safe).
* **FileSystemCache** → stores cached data on disk.
* **RedisCache** → stores in Redis (in-memory DB, super fast).
  * Can be shared across multiple app servers.
  * Common in production setups (also used with Celery).


### Summary on Caching

* Caching is **under developer control**.
* Implement caching **wherever possible** (views, DB queries, templates).
* Two models:

  * **Transparent caching** → handled by proxies/browsers.
  * **Explicit caching** → developer sets cache rules.
* Essential for **scalability and performance**.
* REST design encourages **cacheable responses** (e.g., static assets preferred over dynamic heavy JS).

### 🔹 Final Summary

1. **Scaling needs caching**
   * Without caching, servers handle every request from scratch → poor scalability.
   * With caching, repeated data is served quickly (from memory, disk, or proxy).

2. **REST encourages caching**
   * RESTful APIs are designed to make responses **idempotent and cacheable** where possible.
   * E.g., `GET /users/123` → can be cached safely.
   * `POST /users` → shouldn’t be cached (creates new data).

3. **Caching improves both speed & scalability**
   * Faster responses for users.
   * Lower load on servers.
   * Lower cost for infrastructure.

✅ **In short:**
Caching is one of the **most powerful performance optimizations**. It applies at multiple layers (server, proxy, client) and is deeply tied to REST’s philosophy of **cacheable, stateless interactions**.
