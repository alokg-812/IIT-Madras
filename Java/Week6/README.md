# Week 6

## Lecture 1: The Benefits of Indirection

### 1. Indirection
**Layman Explanation**  
Indirection = "Don't talk directly to the real thing — talk to a middleman who will forward your request."  
It’s like having a personal assistant: you don’t care who actually does the job (driver, courier, cook), you just tell the assistant what you want.

**Technical Explanation**  
In programming, indirection means using a reference/pointer/interface instead of directly using a concrete object. It adds a layer so you can change the actual object behind the scenes without changing the code that uses it.

**Real-Life + Code Example**  
```java
// Without indirection (direct)
CircularArrayQueue<String> q = new CircularArrayQueue<String>();

// With indirection (middleman = interface)
Queue<String> q = new CircularArrayQueue<String>();   // today
q = new LinkedListQueue<String>();                    // tomorrow — same variable!
```
Only one line changes when you switch implementation.

**Key Points to Remember**  
- Indirection = extra layer → more flexibility  
- Famous quote: “All problems in CS can be solved by another level of indirection” – Butler Lampson

### 2. Abstract Data Type (ADT)
**Layman Explanation**  
An ADT is like a menu in a restaurant: you only see what you can order (add, remove, size), not how the chef cooks it in the kitchen.

**Technical Explanation**  
An Abstract Data Type defines WHAT operations are available and WHAT they do, but hides HOW they are implemented.

**Example**  
Queue ADT:  
- add(element) → puts at the back  
- remove() → takes from the front  
- size() → tells how many items

**Key Points**  
- Separates interface (public) from implementation (private)  
- Same ADT → many possible implementations

### 3. Interface in Java
**Layman Explanation**  
A contract that says: “Any class that signs this contract MUST provide these exact methods.”

**Technical Explanation**  
An interface is a 100% abstract type (before Java 8). It only declares method signatures (and constants). Classes implement it using implements keyword.

**Code Example**
```java
public interface Queue<E> {
    void add(E element);    // same as abstract void add(E element);
    E remove();
    int size();
    boolean isEmpty();
}
```

**Key Points**  
- You declare variables using the interface type → indirection!  
- You can swap implementations easily

### 4. Concrete Implementation
**Layman Explanation**  
The actual worker/class that does the real job behind the scenes.

**Technical Explanation**  
A normal class that implements an interface and provides real code for every method.

**Two Queue Examples**
```java
// Implementation 1 – Fast but fixed size
public class CircularArrayQueue<E> implements Queue<E> { ... }

// Implementation 2 – Unlimited size but slightly slower
public class LinkedListQueue<E> implements Queue<E> { ... }
```

### 5. Why Multiple Implementations? (Pros & Cons)

| Feature              | Circular Array Queue                  | Linked List Queue                     |
|----------------------|----------------------------------------|---------------------------------------|
| Memory allocation    | One-time (faster)                     | Dynamic (slower allocation)           |
| Maximum size         | Fixed (you must decide in advance)    | Unlimited (grows as needed)           |
| add/remove speed    | O(1) almost always                    | O(1) always                           |
| Memory overhead      | Very less                             | Extra 2 references per node          |
| Best when            | You know max size & want speed        | Size unknown or very large            |

### 6. Problem WITHOUT Indirection (Bad Way)
```java
CircularArrayQueue<Date> dateq = new CircularArrayQueue<Date>();
// Later you realize you need unlimited size → pain!
dateq.process();      // every method call has to be changed
```
→ You have to change the type everywhere in the code.

### 7. Solution WITH Indirection (Correct Way)
```java
Queue<Date> dateq = new CircularArrayQueue<Date>();   // today
// Tomorrow you want unlimited size → change ONLY ONE line
Queue<Date> dateq = new LinkedListQueue<Date>();
```
Rest of your 1000-line program stays exactly the same!

**Key Advantage**  
Only the new line changes → zero ripple effect.

### 8. Generics <E>
**Layman Explanation**  
<E> = placeholder for any data type. Write the class once, use it for Integer, String, Date, Student — anything.

**Code Example**
```java
Queue<String> names = new LinkedListQueue<String>();
Queue<Integer> numbers = new LinkedListQueue<Integer>();
```
Same class, different types → type safety + code reuse.

### Summary Table (One-Page Revision)

| Concept                  | Without Indirection                          | With Indirection (Interface)                     |
|--------------------------|-----------------------------------------------|---------------------------------------------------|
| Variable declaration     | Concrete class type                           | Interface type                                    |
| Changing implementation  | Change type everywhere → lots of edits        | Change only new line → 1 edit               |
| Flexibility              | Very low                                      | Extremely high                                    |
| Code maintenance         | Painful                                       | Easy                                              |
| Real-world analogy       | Personal car that can break down              | Office car pool / Ola / reimbursement             |

### Famous Quote to Remember (Write in exam!)
> “We can solve any problem in software engineering by adding another level of indirection.”  
> — David Wheeler / Butler Lampson



## Lecture 2: Collection
Here are your **perfect, exam-ready notes** for **Week 6 – Java Collections Framework** (the second lecture).  
Same format as before — super easy to revise!

### 1. Why Java Collections Framework Exists
**Layman Explanation**  
Old Java had many ready-made classes like Vector, Stack, Hashtable — but if you picked one and later wanted to change it → you had to rewrite tons of code.  
So Java said: “Let’s add one level of indirection (just like last lecture!) and make everything swappable.”

**Technical Reason**  
Java Collections Framework = a unified hierarchy of **interfaces + abstract classes + concrete classes** so you code against interfaces (flexible) and swap implementations anytime.

### 2. Collection<E> Interface (The Mother of Most Collections)
**Layman Explanation**  
It’s the “grand contract” that says: “If you’re a collection of objects (list, set, queue, etc.), you MUST support these basic operations.”

**Technical Explanation**  
Root interface for all collections except Map (Map is key-value, not just a bunch of elements).

**Important Methods You Must Remember**
| Method                    | Meaning                                      | Returns      |
|---------------------------|-----------------------------------------------|--------------|
| boolean add(E e)          | Add one element                               | true if added|
| Iterator<E> iterator()    | Give me a way to walk through elements       | Iterator     |
| int size()                | How many elements?                            | int          |
| boolean isEmpty()         | Empty or not?                                 | boolean      |
| boolean contains(Object o)| Is this thing inside?                         | boolean      |
| boolean remove(Object o)  | Remove one occurrence                         | true if removed |
| boolean addAll(Collection c)    | Add all from another collection        | boolean      |
| boolean removeAll(Collection c) | Remove everything that is in c          | boolean      |
| void clear()              | Empty the collection                          | —            |

**Code Example**
```java
Collection<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");

for (String f : fruits) {           // enhanced for-loop uses iterator internally
    System.out.println(f);
}
```

### 3. Iterator<E> Interface – The Walker
**Layman Explanation**  
Like a remote control that lets you walk through a collection one by one and also delete the current item safely.

**Technical Methods**
| Method          | What it does                                    | Important Note                          |
|-----------------|-------------------------------------------------|-----------------------------------------|
| boolean hasNext() | Are there more elements?                      | Check before calling next()            |
| E next()         | Give me the next element & move forward       | Throws NoSuchElementException if none |
| void remove()    | Delete the element returned by last next()    | Can call only once per next()          |

**Correct Way to Remove While Iterating**
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("bad")) {
        it.remove();        // Safe way!
    }
}
// NEVER do list.remove() inside normal for-loop → ConcurrentModificationException
```

**Wrong Way (Will Crash)**
```java
for (String s : list) {
    if (s.equals("bad")) list.remove(s);   // Throws exception!
}
```

### 4. Enhanced For-Loop (for-each)
**Layman Explanation**  
Syntactic sugar — Java secretly creates an Iterator for you.

**Under the Hood**
```java
for (String s : collection) { ... }
// is exactly equal to:
Iterator<String> it = collection.iterator();
while (it.hasNext()) {
    String s = it.next();
    ...
}
```

### 5. Writing Generic Methods That Work on Any Collection
**Example: Generic contains()**
```java
public static <E> boolean contains(Collection<E> c, Object obj) {
    for (E element : c) {
        if (element.equals(obj)) return true;
    }
    return false;
}
```
Works with ArrayList<E>, LinkedList<E>, HashSet<E> — any Collection!

### 6. AbstractCollection<E> – The Helper Class
**Problem**  
Collection interface has 15+ methods → too much work to implement from scratch.

**Solution**  
Java provides AbstractCollection<E> which implements Collection<E> and gives **default implementations** for most methods using only iterator() and size().

**What You Have to Do When Extending It**
```java
public class MyWeirdList<E> extends AbstractCollection<E> {
    // You MUST override these two
    public abstract Iterator<E> iterator();
    public abstract int size();
    
    // You can override contains(), add(), etc. for speed, but not compulsory
}
```
→ 90% of the work is already done for you!

### 7. Quick Comparison Table (Very Important for Exams)

| Concept                | Old Java (pre-Collections) | New Java (Collections Framework) |
|------------------------|----------------------------|----------------------------------|
| Main classes           | Vector, Hashtable, Stack   | ArrayList, HashMap, HashSet      |
| Indirection?           | No → tightly coupled       | Yes → code against interfaces    |
| Changing implementation| Change type everywhere    | Change only new line             |
| Thread-safe by default?| Yes (Vector, Hashtable)    | No (faster, use Collections.synchronized if needed) |

### One-Page Summary You Can Paste in Your Notes

**Java Collections Framework = Indirection + Hierarchy**  
→ Code to interfaces (List, Set, Queue, Map)  
→ Use Iterator or for-each to traverse  
→ Never remove using collection.remove() inside for-each  
→ Use iterator.remove() for safe deletion  
→ AbstractCollection saves you from writing 15 methods  
→ Generics + interfaces = reusable, type-safe, swappable code



## Lecture 3: Concrete Collections

Here are your **exam-ready, beautifully structured notes** for **Week 6 – Concrete Collections** (the most important lecture of the entire Collections topic!).  
This covers **List, Set, Queue** + their concrete implementations with exact differences, time complexity, and when to use what.

### 1. The Three Main Sub-Interfaces of Collection<E>

| Interface     | Layman Meaning                                  | Technical Meaning                                      | Duplicates? | Order Preserved?          |
|---------------|--------------------------------------------------|--------------------------------------------------------|-------------|----------------------------|
| **List<E>**   | A numbered list – like a train with compartments| Ordered collection, indexed access (0,1,2…)           | Yes         | Insertion order            |
| **Set<E>**    | A bag where you can’t put the same thing twice  | Unordered, no duplicates (based on equals() & hashCode()) | No          | No guaranteed order*       |
| **Queue<E>**  | A line at the canteen – first come, first served| Ordered, but restricted add/remove (usually one end each)| Yes         | Insertion order (FIFO)     |

*Except special sets like LinkedHashSet and SortedSet

### 2. List<E> – The Most Commonly Used

**Layman Explanation**  
Like an expandable array where you can insert, delete, and access by position (index).

**Important Concrete Classes**

| Class               | Backed by          | Random Access (get(i)) | Add/Remove in Middle | Best When You Need…                          |
|---------------------|--------------------|-------------------------|----------------------|----------------------------------------------|
| **ArrayList<E>**    | Resizable array    | Very fast O(1)          | Slow O(n)            | Fast access by index, mostly add at end      |
| **LinkedList<E>**   | Doubly-linked list | Slow O(n)               | Very fast O(1)       | Frequent add/remove from beginning or middle |
| **Vector<E>**       | Same as ArrayList  | Fast                    | Slow                 | Legacy + thread-safe (almost never used now) |

**Code Example**
```java
List<String> names = new ArrayList<>();        // Best for 99% cases
names.add("Raj");       // index 0
names.add("Priya");     // index 1
names.add(1, "Ankit");  // shifts Priya → now [Raj, Ankit, Priya]

List<String> queue = new LinkedList<>();       // Also implements Queue!
```

### 3. Set<E> – No Duplicates Allowed

**Layman Explanation**  
Like a club membership list – if your name is already there, you can’t join again.

**Important Concrete Classes**

| Class               | Internal Structure     | Order Preserved?               | add()/contains() Speed | Null Allowed? |
|---------------------|------------------------|--------------------------------|------------------------|---------------|
| **HashSet<E>**      | Hash table             | No order                       | O(1) average           | Yes (one)     |
| **LinkedHashSet<E>**| Hash table + linked list| Insertion order                | O(1)                   | Yes           |
| **TreeSet<E>**      | Red-black tree         | Sorted order (natural/Comparator)| O(log n)              | No            |

**Most Used in Real Life**
```java
Set<String> uniqueNames = new HashSet<>();           // fastest
Set<String> uniqueInOrder = new LinkedHashSet<>();   // order + no duplicates
Set<String> sortedUnique = new TreeSet<>();          // always sorted
```

### 4. Queue<E> & Deque<E> – The Line System

**Layman Explanation**  
Queue = normal line (join back, leave front)  
Deque = double-ended queue (you can join/leave from both ends)

**Important Concrete Classes**

| Class               | Best For                                 | Also Implements |
|---------------------|------------------------------------------|-----------------|
| **LinkedList<E>**   | Queue + Deque + List (most flexible)     | List, Deque     |
| **ArrayDeque<E>**   | Fastest pure Queue/Deque (recommended)   | Deque           |
| **PriorityQueue<E>**| Not FIFO – smallest/largest comes first  | —               |

**Standard Queue Usage**
```java
Queue<String> canteen = new ArrayDeque<>();
canteen.offer("Ram");     // add at back
canteen.offer("Shyam");

String first = canteen.poll();  // removes and returns "Ram"
```

**Stack using Deque (modern way – never use old Stack class)**
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);
stack.push(20);
int top = stack.pop();    // 20
```

### 5. One-Page Decision Table (Golden for Interviews & Exams)

| Requirement                                    | Use This Class                  |
|------------------------------------------------|---------------------------------|
| Need index, fast get(i), mostly add at end     | **ArrayList<E>**                |
| Frequent insert/delete in middle or front      | **LinkedList<E>**               |
| Just store unique items, fastest               | **HashSet<E>**                  |
| Unique + remember insertion order              | **LinkedHashSet<E>**            |
| Unique + always sorted                         | **TreeSet<E>**                  |
| Normal FIFO queue                              | **ArrayDeque<E>** or LinkedList|
| Stack (LIFO)                                   | **ArrayDeque<E>**               |
| Want smallest/largest element first            | **PriorityQueue<E>**            |

### 6. Time Complexity Cheat Sheet (Must Memorize)

| Operation       | ArrayList | LinkedList | HashSet    | TreeSet    | ArrayDeque |
|----------------|-----------|------------|------------|------------|------------|
| add/offer       | O(1)*     | O(1)       | O(1) avg   | O(log n)   | O(1)*      |
| remove/poll     | O(n)      | O(1)       | O(1) avg   | O(log n)   | O(1)       |
| get(index)      | O(1)      | O(n)       | —          | —          | —          |
| contains        | O(n)      | O(n)       | O(1) avg   | O(log n)   | O(n)       |

*Amortized O(1) – occasionally slower when resizing

### Final Summary You Can Stick on Your Wall

**Golden Rule in Java:**  
→ Always code to the **interface**, not concrete class  
```java
List<String> list = new ArrayList<>();      // Good
Set<Integer> set = new HashSet<>();         // Good
Queue<Task> q = new ArrayDeque<>();         // Good
```

Never do this in new code:
```java
ArrayList<String> list = new ArrayList<>(); // Bad – no flexibility
Vector<String> v = new Vector<>();          // Very old style
Stack<Integer> s = new Stack<>();           // Legacy – use Deque
```



## Lecture 4: Maps
# Week 6 – Java Maps (Complete Exam-Ready Notes)

### 1. Map<K, V> – The Dictionary of Java
**Layman Explanation**  
A real-life phone book / marks register / English-to-Hindi dictionary.  
You give a key → you instantly get the value.

**Technical Explanation**  
- Map is NOT a Collection (it does NOT extend Collection interface)  
- It stores key-value pairs (called “entries”)  
- Keys are unique → internally the keys behave exactly like a Set  
- One key can have only one value (putting again overwrites the old value)

**Declaration**
```java
Map<String, Integer> marks = new HashMap<>();
Map<Integer, String> rollToName = new HashMap<>();
Map<String, Student> idToStudent = new TreeMap<>();
```

### 2. Most Important Methods (Memorize These 8)

| Method                          | What it does                                          | Returns                     |
|---------------------------------|-------------------------------------------------------|-----------------------------|
| V get(Object key)               | Fetch value for this key                              | value or null               |
| V put(K key, V value)           | Insert or update key-value pair                       | previous value or null      |
| V remove(Object key)            | Delete this key and its value                         | removed value or null       |
| boolean containsKey(Object k)   | Is this key present?                                  | true/false                  |
| boolean containsValue(Object v) | Is this value present anywhere?                       | true/false                  |
| int size()                      | Total number of key-value pairs                       | int                         |
| boolean isEmpty()               | Empty map?                                            | true/false                  |
| void clear()                    | Remove everything                                     | —                           |

**Example**
```java
Map<String, Integer> age = new HashMap<>();
age.put("Rohan", 21);           // returns null
age.put("Rohan", 22);           // returns 21 (old value)
Integer old = age.put("Priya", 20);  // old = null
age.get("Rohan");               // → 22
```

### 3. Three Ways to Iterate Over a Map (All 3 are asked in exams!)

**Way 1 – Iterate over keys (most common)**
```java
for (String name : age.keySet()) {
    System.out.println(name + " → " + age.get(name));
}
```

**Way 2 – Iterate over values only**
```java
for (Integer a : age.values()) {
    System.out.println("Age: " + a);
}
```

**Way 3 – Iterate over entries (best when you need both key & value)**
```java
for (Map.Entry<String, Integer> entry : age.entrySet()) {
    String name = entry.getKey();
    Integer a   = entry.getValue();
    entry.setValue(a + 1);        // you can even modify value here!
}
```

### 4. Concrete Map Implementations (When to Use What)

| Class                | Internal Structure      | Key Order Preserved?         | Speed (get/put) | Null Key? | Best When You Need…                     |
|----------------------|-------------------------|------------------------------|-----------------|-----------|-----------------------------------------|
| **HashMap<K,V>**     | Hash table              | No order                     | O(1) average    | Yes       | General purpose, fastest (99% cases)    |
| **LinkedHashMap<K,V>**| Hash table + linked list| Insertion order (or access order)| O(1)         | Yes       | Want insertion order (LRU cache)        |
| **TreeMap<K,V>**     | Red-black tree          | Sorted order (natural/Comparator)| O(log n)   | No        | Keys always sorted                      |
| **Hashtable<K,V>**   | Old hash table          | No order                     | O(1)            | No        | Legacy + thread-safe (avoid in new code)|

**Golden Rule (write in every program)**
```java
Map<String, Integer> map = new HashMap<>();        // Good – flexible
// NEVER do this in new code:
// HashMap<String, Integer> map = new HashMap<>();
```

### 5. Quick Decision Table (Stick on Your Desk)

| Requirement                              | Use This Map                     |
|------------------------------------------|----------------------------------|
| Just need fast key-value, no order       | **HashMap**                      |
| Want to remember insertion order         | **LinkedHashMap**                |
| Want keys always sorted                  | **TreeMap**                      |
| Need thread-safety in old code           | **Hashtable** (or Collections.synchronizedMap) |
| Building LRU Cache                        | **LinkedHashMap** (override removeEldestEntry) |

### 6. Time Complexity Cheat Sheet

| Operation   | HashMap    | LinkedHashMap | TreeMap    |
|-------------|------------|---------------|------------|
| get()       | O(1) avg   | O(1)          | O(log n)   |
| put()       | O(1) avg   | O(1)          | O(log n)   |
| remove()    | O(1) avg   | O(1)          | O(log n)   |
| containsKey | O(1) avg   | O(1)          | O(log n)   |

### 7. One-Liner Summary for Exams

“Map<K,V> is a key-value dictionary. Keys are unique (like a Set).  
Always declare Map interface type, use HashMap 99% of the time.  
Iterate using entrySet() when you need both key and value.”

