console.log("start");

Promise.resolve().then(() => {
    console.log("Promise");
})

setTimeout(()=>{
    console.log("Timer");
},0)

console.log("END");