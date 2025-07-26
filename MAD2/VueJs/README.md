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






