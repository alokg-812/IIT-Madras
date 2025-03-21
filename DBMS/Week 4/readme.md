# Week 4: Modelling & Design part of the Databases
## Lect 1: Formal Relational Query Languages/1
![image](https://github.com/user-attachments/assets/f547106a-6d2d-4ed9-9b38-3c7e9e86ce05)

### Relational Algebra:
* Created by Edgar F Codd at IBM in 1970
* Procedural language
* Six basic operators
  - `select: σ`
  - `project: П`
  - `union: U`
  - `set difference -`
  - `Cartesian product: X`
  - `rename: ρ`
* The operators take one or two relations as inputs and produce a new relation as a result
---

#### 🔍 1. Select Operation (σ)
![image](https://github.com/user-attachments/assets/c9f82308-c9ef-41b8-b84d-d2b85c63c635)

- **Description:** Selects rows from a relation that satisfy a given predicate.
- **Definition:**
  ```
  σp(r) = {t | t ∈ r and p(t)}
  ```
- **Predicate Formula:**
  - Uses propositional calculus with terms connected by `∧` (and), `∨` (or), `¬` (not).
  - Example: `σdept_name = 'Physics'(instructor)` selects all instructors from the Physics department.

---

#### 📌 2. Project Operation (Π)
![image](https://github.com/user-attachments/assets/60f05e1d-db61-48dc-9b69-0f122f467ef4)

- **Description:** Retrieves specified columns from a relation, removing duplicates.
- **Example:**
  ```
  ΠID,name,salary (instructor)
  ```
  This eliminates the `dept_name` attribute from the `instructor` relation.

---

#### 🔗 3. Union Operation (∪)
![image](https://github.com/user-attachments/assets/884db722-8c7f-42f0-898e-2e73e6fc9674)

- **Definition:**
  ```
  r ∪ s = {t | t ∈ r or t ∈ s}
  ```
- **Conditions:**
  - Relations `r` and `s` must have the same arity (number of attributes).
  - Attribute domains must be compatible.
- **Example:**
  ```
  Πcourse_id(σsemester='Fall'∧year=2009(section)) ∪ Πcourse_id(σsemester='Spring'∧year=2010(section))
  ```

---

#### ➖ 4. Difference Operation (−)
![image](https://github.com/user-attachments/assets/f402a8da-9240-46b0-a05c-dd310f1f00b6)

- **Definition:**
  ```
  r − s = {t | t ∈ r and t /∈ s}
  ```
- **Conditions:**
  - Both relations must have the same arity and compatible attribute domains.
- **Example:**
  ```
  Πcourse_id(σsemester='Fall'∧year=2009(section)) − Πcourse_id(σsemester='Spring'∧year=2010(section))
  ```

---

#### 🔄 5. Intersection Operation (∩)
![image](https://github.com/user-attachments/assets/74ff377c-b920-46f2-a3be-df1136ea7814)

- **Conditions:**
  - Relations `r` and `s` must have the same arity and compatible attributes.
- **Note:**
  ```
  r ∩ s = r - (r - s)
  ```

---

#### ✖ 6. Cartesian Product Operation (×)
![image](https://github.com/user-attachments/assets/bd7f7dec-26e1-42bf-95f0-883e44c6e9b1)


- **Condition:**
  - Attributes of `r(R)` and `s(S)` should be disjoint.
  - If not, renaming must be used.

---

#### 🔄 7. Rename Operation (ρ)
![image](https://github.com/user-attachments/assets/22b8ac09-8ecb-49f4-99f5-45ce1effb762)

- **Description:** Allows naming of results and using multiple names for a relation.
- **Example:**
  ```
  ρx(E)  → Returns the expression `E` under the name `X`
  ```
  ```
  ρx(A1, A2, ..., An)(E)  → Renames attributes of `E` to `A1, A2, ..., An`
  ```

---

#### ➗ 8. Division Operation
- **Description:** Applies to two relations `R(Z)` and `S(X)` where `X ⊆ Z`.
- **Example Usage:**
  - Finding students who have taken all courses given by a particular instructor.
- **Expression:**
  ```
  r ÷ s ≡ ΠR−S(r) − ΠR−S((ΠR−S(r) × s) − ΠR−S,S(r))
  ```
  **Output:**
![image](https://github.com/user-attachments/assets/11fc1e26-9921-42ea-b0e3-fd1b02c20bd9)

--- 
