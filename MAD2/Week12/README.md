# Week 12

## Privacy vs Security

### Privacy
* Refers to **control over personal information**.
* Covers **Personally Identifiable Information (PII)** like:
  * Name, email, phone, address, Aadhaar/passport, etc.
* Privacy rights are mostly governed by **regulations**:
  * **Government mandates** (laws like GDPR, HIPAA).
  * **End-user agreements** (terms & conditions).
* Developers must respect these rights → decide **what data to collect, what not to collect, and how it can be shared**.

👉 Privacy is about **what data should or should not be exposed.**

### Security

* Refers to **how data is safeguarded technically** once it’s collected.
* More about **implementation**:
  * Secure coding practices.
  * Infrastructure monitoring to detect breaches.
* Example: Encrypting passwords, using HTTPS, patching vulnerabilities.

👉 Security is about **protecting data**, privacy is about **deciding what data can exist and be used.**

### Privacy without Security

* Theoretically: “Don’t collect PII at all → nothing to leak.”
* Problem:
  * In practice, we **need services** (email, banking, shopping).
  * Even if you don’t directly give data, info can still leak indirectly.
    * Example:
      * **Truecaller** knows names from other people’s contacts.
      * **Cambridge Analytica scandal**: Only 270,000 users installed a quiz app, but their **friend networks exposed data of 87 million people**.

### Security without Privacy

* A system may be technically secure but still violate privacy.
* Example:
  * A company securely stores your browsing history, but **sells it to advertisers** (no privacy).
  * Users agreed to this in fine-print **End-User Agreements**.

## Sensitive Information

### Direct Sensitive Data
* Passwords.
* Banking details, account numbers.
* Medical records.

### Indirect Sensitive Data
* Shopping patterns: “Customers who bought this also bought…”
* Inferences from behavior:
  * Order many pizzas → get gym ads.
  * View job profiles on LinkedIn → get job recommendations.

### Metadata
* Even if direct content isn’t leaked, metadata (time, frequency, location of actions) reveals a lot about users.

## Regulations
Different industries & regions have specific rules:

* **GDPR (General Data Protection Regulation, EU)**
  * Applies to any company handling EU citizen data.
  * Requires explicit consent, right to be forgotten, breach notification.

* **HIPAA (US Healthcare)**
  * Protects patient health data.
  * Ensures secure storage, restricted access, and breach notification.

* **Other country/domain-specific rules**
  * Example: India’s **Digital Personal Data Protection Act (DPDP, 2023)**.
  * Financial sector rules (like PCI DSS for credit card data).

👉 Developers **cannot plead ignorance** → If an app leaks data, you can lose certifications, face lawsuits, or penalties.

## Security Measures

* **Regulations specify what to protect, not how.**
* Developers must implement safeguards:
  1. **Don’t collect unnecessary data**
     * If you don’t need it, don’t store it.
     * Example: Signal messenger collects minimal metadata vs WhatsApp (which collects more).
  2. **Best practices in development**
     * **Frontend**: in-browser security measures (e.g., secure cookies, CSRF protection).
     * **Backend**: server-side measures (e.g., encryption, access controls).

<br> 👉 Privacy tells you **what not to collect/share**.
<br> 👉 Security tells you **how to protect what you do collect**.

## What “frontend security” is about

On the client side (browser), the threats and defenses revolve around (a) how interactive your site is, (b) what third-party resources you pull in, and (c) stopping malicious code or requests from running in/through the user’s browser. The notes frame this with scenarios (static → forms → JS-heavy apps), cross-site resources/trackers, and browser-level malware risks.&#x20;


### Cookies (sessions, tracking, and safety flags)

**Session vs. Permanent cookies.** Sessions keep you logged in; “Remember me” usually sets a longer-lived cookie. GDPR is why you often see those cookie banners.&#x20;

**First-party vs. Third-party.**

* First-party: set by the site you’re on (sessions, preferences).
* Third-party: typically ad/analytics trackers; modern browsers largely block them due to privacy concerns.&#x20;

**Hardening tips (developer practice):**

* Set **Secure** (HTTPS-only), **HttpOnly** (inaccessible to JS), and **SameSite** (e.g., `Lax`/`Strict`) where possible.
* Rotate/expire session cookies sensibly; don’t store PII in cookies.

> Example:
> `Set-Cookie: sid=abc123; Path=/; Secure; HttpOnly; SameSite=Lax`

### Cross-Site Scripting (XSS)

**The core issue:** untrusted input gets reflected into the page as HTML/JS and executes in the user’s browser. The notes show a query parameter that injects a `<script>` tag—classic reflected XSS; the same idea can be stored in DB content (forums/comments). Server-side validation + client-side blocking of cross-site script loads are called out.&#x20;

**Mitigations (practical checklist):**

* **Escape/encode** untrusted data *on output* (HTML/text/URL/JS contexts differ).
* Prefer setting text via `textContent`/`innerText` (avoid `innerHTML`).
* Sanitize rich HTML (only if you truly must allow it).
* Use **CSP** (below) to restrict script sources and disallow inline scripts.
* Framework auto-escaping (React/Angular/etc.) helps—avoid bypasses.

> Safe render pattern (JS):
>
> ```js
> // Instead of element.innerHTML = userInput;
> element.textContent = userInput; // escapes safely
> ```

### Cross-Site Request Forgery (CSRF)

**What it is:** an attacker causes a logged-in user’s browser to hit a state-changing endpoint on another tab/site (e.g., bank transfer), piggy-backing on your session cookie. The notes show a GET-based transfer URL to illustrate the problem and introduce **CSRF tokens** with short validity.&#x20;

**Mitigations (together, not either/or):**

* **CSRF tokens** in forms/requests, verified server-side.
* Prefer **POST/PUT/DELETE** (avoid sensitive GETs).
* **SameSite** cookies (e.g., `Lax/Strict`) reduce cross-site sends.
* Custom request headers (e.g., `X-Requested-With`) checked server-side.
* Double-submit cookie pattern where appropriate.

> Token flow (simplified):
>
> ```html
> <form method="POST" action="/transfer">
>   <input type="hidden" name="csrf" value="{{ csrf_token }}">
>   ...
> </form>
> ```
>
> Server verifies `csrf` matches the user/session.&#x20;


### CORS (Cross-Origin Resource Sharing)

**Problem modeled in notes:** page at `www.example.com` wants to call `api.example.com`. By default, browsers enforce the **Same-Origin Policy**; **CORS** lets the API explicitly opt-in to specific origins.&#x20;

**Key pieces:**

* API responds with `Access-Control-Allow-Origin` (and often `Vary: Origin`).
* Preflight (`OPTIONS`) for non-simple requests via `Access-Control-Request-*` headers.
* With cookies/credentials: must use `Access-Control-Allow-Credentials: true` and a non-`*` origin; front-end must send `fetch(..., { credentials: 'include' })`.

> Minimal allow (example API response):
>
> ```
> Access-Control-Allow-Origin: https://www.example.com
> Access-Control-Allow-Methods: GET, POST
> Access-Control-Allow-Headers: Content-Type
> ```

### Content Security Policy (CSP)

**What/why:** a declarative browser policy to say “only load code/resources from these places,” which massively limits XSS impact. The notes point to CSP as a **generic policy approach**.&#x20;

**Starter policy (tighten over time):**

```
Content-Security-Policy:
  default-src 'self';
  script-src 'self' 'nonce-r4nd0m';
  style-src 'self';
  img-src 'self' data:;
  object-src 'none';
  base-uri 'self';
  frame-ancestors 'none';
```
* Use **nonces** or **hashes** for any inline scripts you deliberately allow.
* Start with `Content-Security-Policy-Report-Only` to gather violations safely.&#x20;

### Secure Contexts
Some powerful web APIs only work on **secure contexts** (HTTPS or localhost). This reduces abuse by requiring transport security for sensitive capabilities. The notes explicitly reference Secure Contexts guidance.&#x20;

**Action:** Serve everything over **HTTPS**, including subresources; upgrade mixed content.

### Sandbox

Browsers run content in a **sandbox** to contain harm. As a developer you can further isolate untrusted embeds with the `sandbox` attribute on iframes (disables scripts/alerts/forms/etc. until you selectively re-enable). The notes highlight the broader browser sandbox model.&#x20;

> Example (very restricted iframe):
>
> ```html
> <iframe src="untrusted.html" sandbox></iframe>
> ```
>
> Or allow only what you must:
>
> ```html
> <iframe src="widget.html" sandbox="allow-scripts allow-same-origin"></iframe>
> ```


## Backend Security

Backend security is a **vast topic**, since it spans everything from **code and libraries** to **OS, servers, and networks**. The notes give you a structured set of issues and best practices.

### Overview

* Backends run on **servers** → large attack surface:
  * Languages, frameworks, compilers, OS, dev tools.
* Good backend security needs **both developer discipline** (code, package usage) and **system administration knowledge** (server setup, monitoring).
* Think of it as “every layer can leak.”

### Package Management Issues

Modern apps rely heavily on **third-party libraries** (pip in Python, npm in JS, etc.).
* Example in notes: Flask uses packages like `requests`, `google APIs`, `markdown`.
* Developers declare them in `requirements.txt`.

**Problems:**
* **Version incompatibility** → Upgrading Python or Flask may break library dependencies.
* **Security bugs** in libraries → if you don’t upgrade, you remain vulnerable.
* **Maintaining delicate balances** → can cause fragile builds or security holes.

**Best practices:**
* Pin versions (`==`) to ensure reproducible builds.
* Regularly check for vulnerabilities (tools like `pip-audit`, `npm audit`).
* Automate dependency updates with CI/CD pipelines.

### Supply Chain Attacks
* Attackers compromise **the libraries you import** instead of your code.
* XKCD famously joked: “Everything depends on a tiny library maintained by one person in Nebraska.”
* Examples:
  * **Log4j (Java)**: logging library had a critical RCE bug from 2013 → only discovered in 2021. Affected millions of apps.
  * **faker.js (JavaScript)**: maintainer pulled the plug, breaking dependent packages.

**Mitigation:**
* Reduce dependencies when possible.
* Pin versions.
* Monitor advisories (GitHub Dependabot, Snyk).
* Still: some attacks (like Log4j) are unavoidable → quick patching/response is key.

### Server Communications
* Servers must **talk securely with each other**, not just with browsers.

**Key points:**
* **Endpoint security**: maintain servers with patches and strong access controls.
* **TLS everywhere**:
  * HTTPS secures browser ↔ server.
  * But also use TLS for **server ↔ server**.
* **Authorized communication**:
  * Accept only known client requests.
  * Filter by IP or use API keys/certificates.

### Denial of Service (DoS / DDoS)

* **DoS**: Attack floods your server with traffic, not to steal data but to knock it offline.
* **DDoS**: Coordinated attack using thousands of compromised machines (botnets).
* Impact: Even seconds of downtime = major losses for big apps.

**Mitigation:**
* Rate limiting (per user/IP).
* Web Application Firewalls (WAFs).
* ISP/cloud-level DDoS protection (e.g., Cloudflare, AWS Shield).

### DevOps Security
* DevOps = managing code + servers in production.
* Security in DevOps is about **automation & safe configuration**.

**Best practices:**
* Use **CI/CD pipelines** (GitHub Actions, GitLab pipelines) to auto-deploy with tests.
* Automate installation & server provisioning (Ansible, Terraform).
* Never keep secrets (API keys, tokens) in version control.
  * Use **vaults** (HashiCorp Vault) or **environment variables**.

### Password Guidelines
* Traditional “complexity rules” (lots of symbols, frequent changes) → don’t help much.
* **New NIST guidelines** (SP 800-63B):
  * Allow longer passphrases.
  * Don’t force frequent changes unless there’s a breach.
* Server-side:
  * Always store **hashed passwords** (never plaintext).
  * Use modern hash functions (bcrypt, scrypt, Argon2).
  * Add **salt** to prevent dictionary/rainbow table attacks.


### App Deployment Security

* **Automate deployments** to reduce human error.
* Access:
  * Use **SSH (encrypted)** only, no plaintext remote logins.
  * Restrict SSH keys.
* **Secret management**:
  * Use vaults or encrypted env vars.
  * Never commit tokens to GitHub.
* **Database access**:
  * Secure connections.
  * Use least-privilege accounts (e.g., read-only for reporting apps).

### Logging

* Logs are essential for debugging & forensics.
* But:
  * Too much logging = storage costs, performance overhead.
  * Logs may contain sensitive data (sanitize them).

**Best practices:**

* Keep balanced logs.
* Use summaries and dashboards (e.g., Kibana, Grafana).
* Use **log rotation** to avoid runaway file growth.
* Use **time-series analysis** on logs to spot abnormal activity (spikes, errors).

### Final Summary 

* **Privacy** = user rights + regulations (what data can exist).
* **Security** = implementation measures (how to protect data).
* **Both needed**: Without privacy → legal/ethical violations; without security → leaks and hacks.
* **Sensitive information** can be direct (passwords, bank data) or indirect (metadata, recommendations).
* **Regulations like GDPR/HIPAA** force developers to follow strict rules.
* **Best practice**: Collect only necessary data, and protect it using secure coding + infrastructure.
* **Cookies**: `Secure`, `HttpOnly`, `SameSite`, minimal lifetime.&#x20;
* **XSS**: output-escape, avoid `innerHTML`, sanitize rich input, enforce strong CSP.&#x20;
* **CSRF**: tokens + POST + SameSite; verify server-side.&#x20;
* **CORS**: least-privilege `Allow-Origin`, careful with credentials.&#x20;
* **HTTPS** everywhere (Secure Contexts).
* **Sandbox** untrusted iframes/widgets.
* **Dependencies**: Pin, monitor, reduce; beware of supply-chain attacks.
* **Server communications**: Use TLS everywhere, restrict endpoints.
* **DoS/DDoS**: Protect with rate-limiting, WAFs, and ISP/cloud shields.
* **DevOps**: Automate deployments, protect secrets.
* **Passwords**: Store only hashed + salted, allow long passphrases.
* **Deployment**: Use SSH, manage tokens securely, restrict DB access.
* **Logging**: Balance debugging vs performance, rotate and monitor logs.
