const promise = new Promise((resolve, reject) => {
    reject("Error!");
    resolve("Success!")
});
promise.then(console.log).catch(console.log);