Topics:
1. VueJS - Introduction and Features
2. Prerequisites
   - Node and NPM
3. API styles
4. Methods to use VueJS
5. Getting Started
   - VS Code Extensions
   - Project Structure
   - Code Flow
6. Class and Style binding
   - v-bind with class
   - v-bind with style
7. Reactivity Fundamentals
   - Reactive state
   - this keyword
8. Methods
   - Arrow function Restriction
9. Deep Reactivity
10. Reactive Proxy vs original




### What is VueJS?
_Ans:_ Javascript framework used to develop interactive UIs.
> [Framework vs Library](https://medium.com/@alokg7055/understanding-frameworks-vs-libraries-cricket-code-control-dcc0236963bf)

### Introduction 
* Open Source
* It is used to create single page application
* Created by Ecan You (Ex-google employee)
* First version - Feb 2014
* `VueJS 3` is the latest version

### Features
* Lightweight, Easiest to learn & Good performance.
* Declarative Rendering & Reactivity
* Virtual DOM.
  * DOM manipulation is costly, hence we use _virtual dom_
* Event handling(Simplified from JS).
* Directives & Watchers.
* Routing (Vue routers).
* State management(Vuex and Pinia). 

### Api styles
1. `Options API` 
2. `Composition API`

**eg#01:**
* Everything is done in a formatted style as per our wish.
```js
data(){
   name: "...";
   count: 0;
}
methods:{
   increase();
   decrease();
}
watcher(){
   ...;
}
```
**eg#02:** 
* Everything is done manually and hence if a small issue came, it could lead to big errors in the code.
```js
name= "...";
count = 0;
increase();
decrease();
...
```

### Ways to use Vue
1. Without Build tool:
   - [Using CDN link](https://github.com/alokg-812/IIT-Madras/blob/main/MAD2/VueJs/cdn-link.html) <br>
_Output:_
<img width="875" height="205" alt="image" src="https://github.com/user-attachments/assets/6761ad68-1c01-4c54-bc61-4362dbb4aa06" />

2. With Build tool:
   - [Using vite](https://github.com/alokg-812/IIT-Madras/tree/main/MAD2/VueJs/learning-1)
<br> _How to do?:_ <br>
<img width="1533" height="859" alt="image" src="https://github.com/user-attachments/assets/acab53d2-b257-4f27-99a6-c711ae8d0c09" />
<br> 
_Output:_  <br>
<img width="1919" height="1018" alt="image" src="https://github.com/user-attachments/assets/ab7f6bae-5590-4778-9e45-a0cd002df347" />

### Getting Started
* A `vue.js` file is referred to as a _single-file component._
* It contains the `component's logic`(Javascript), `template`(HTML), and `styles`(CSS) in a single file.
* It has three blocks:
  1. `<template>...</template>`
  2. `<script>...</script>`
  3. `<style>...</style>`
* This file is pre-compiled by @vue/compiler-sfc into standard Javascript and CSS to make it usable.
* The compiler is typically integrated within the buil tool.
#### Traditional Approach
<img width="668" height="299" alt="image" src="https://github.com/user-attachments/assets/6ee4af52-95bc-46a8-a6c7-c4ef5ff1d0b8" />

#### VueJS Approach
<img width="610" height="392" alt="image" src="https://github.com/user-attachments/assets/d0d25553-b4c8-45cb-851a-47386f4d04a3" />












