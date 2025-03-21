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

- **Definition:**  _Selects rows from a relation that satisfy a given condition._
  ```
  σ p(r) = {t | t ∈ r and p(t)}
  ```
- **Predicate Formula:**
  - Uses propositional calculus with terms connected by `∧` (and), `∨` (or), `¬` (not).
  - Example: `σ dept_name = 'Computer Science'` selects all students from the Computer Science department.
![image](https://github.com/user-attachments/assets/fbd3eb67-bb99-495b-9322-1cadbb8e5be4)

---

#### 📌 2. Project Operation (Π)
![image](https://github.com/user-attachments/assets/60f05e1d-db61-48dc-9b69-0f122f467ef4)

- **Description:** _Selects specific columns._
- **Example:**
  ```
  Π name,dept_name (student)
  ```
  This takes only the `dept_name` and `name` attribute from the `student` relation.
![image](https://github.com/user-attachments/assets/fd8b028c-0f50-423b-a1d8-0608d1162f3a)

---

#### 🔗 3. Union Operation (∪)
![image](https://github.com/user-attachments/assets/884db722-8c7f-42f0-898e-2e73e6fc9674)

- **Definition:** - _Combines two relations with the same schema._
  ```
  r ∪ s = {t | t ∈ r or t ∈ s}
  ```
- **Conditions:**
  - Relations `r` and `s` must have the same arity (number of attributes).
  - Attribute domains must be compatible.
- **Example:**
  ```
  Π dept_name,name (Student)
  ∪
  Π dept_name,course_id (Course)
  ```
![image](https://github.com/user-attachments/assets/6db235aa-f6ab-4968-b6f0-c85ee053b1b2)

---

#### ➖ 4. Difference Operation (−)
![image](https://github.com/user-attachments/assets/f402a8da-9240-46b0-a05c-dd310f1f00b6)

- **Definition:** _Finds rows in one relation but not in another._
  ```
  r − s = {t | t ∈ r and t /∈ s}
  ```
- **Conditions:**
  - Both relations must have the same arity and compatible attribute domains.
- **Example:**
  ```
  Π dept_name(students)) − Π dept_name((courses))
  ```

  ![image](https://github.com/user-attachments/assets/78e4029b-2c5a-454a-a96c-56413de0786d)


---

#### 🔄 5. Intersection Operation (∩)
![image](https://github.com/user-attachments/assets/74ff377c-b920-46f2-a3be-df1136ea7814)

- **Conditions:**
  - Relations `r` and `s` must have the same arity and compatible attributes.
- **Note:**
  ```
  r ∩ s = r - (r - s)
  ```
- **Example:**
  ```
  Π dept_name(students)) ∩ Π dept_name((courses))
  ```
![image](https://github.com/user-attachments/assets/005f5eba-ba84-4b01-a5dc-bc2a8936561f)

---



#### ✖ 6. Cartesian Product Operation (×)
![image](https://github.com/user-attachments/assets/bd7f7dec-26e1-42bf-95f0-883e44c6e9b1)


- **Condition:**
  - Attributes of `r(R)` and `s(S)` should be disjoint.
  - If not, renaming must be used.
- **Example:**
  ```
  σ * (students)) X σ * ((courses))
  ```
  
![image](https://github.com/user-attachments/assets/bd8d204e-f804-4eb5-bed7-9997a12bf3dd)

---

#### 🔄 7. Rename Operation (ρ)
![image](https://github.com/user-attachments/assets/22b8ac09-8ecb-49f4-99f5-45ce1effb762)

- **Description:** Allows naming of results and using multiple names for a relation.
- **Example:**
  ```
  ρ dept_name department_name,
    name as student_name
    (Student)
  ```
![image](https://github.com/user-attachments/assets/74df3687-d445-46e4-bfbf-5bff6cec4e87)

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






## Lect 2: 

#### 1. Introduction to Formal Relational Query Languages
* **Relational Algebra**: A procedural query language that uses operations like selection, projection, and joins.
* **Tuple Relational Calculus (TRC)**: A non-procedural language based on predicate calculus, where queries are specified using logical conditions.
* **Domain Relational Calculus (DRC)**: Another non-procedural approach that operates on attribute values (domains) rather than entire tuples.


#### 2. Predicate Logic: `Predicate Logic` or `Predicate Calculus` is an extension of Propositional Logic or `Boolean Algebra`.
Predicates: Expressions that return `true` or `false` based on input values.

**Example:** `x is greater than 3` → can be written as `P(x)` 
where:
P(x) represents the predicate "is greater than 3".

> **_NOTE:_**  
> P(x) represents the predicate
> > P(5) becomes **proposition** which yields value `true` and P(2) yields `false`.


- Multi-variable predicates can be written as P(x1, x2, ..., xn).


#### 3. Quantifiers
Universal Quantifier (∀): Indicates a property is true for all values in a domain.
Example: ∀x (x + 2 > x) is true for all real numbers.
Existential Quantifier (∃): Indicates that a property is true for at least one value in a domain.
Example: ∃x (x > 10) states that "there exists an x such that x is greater than 10."







