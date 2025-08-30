let starterName=(name)=>{
    let x=name.split('').reverse(); // x = ['e','g','n','a','r','o']
    let handler =setInterval(() => {
        let y = x.pop();
        console.log(y);
    }, 1000);

    setTimeout(()=>{
        clearInterval(handler)
    },(name.length+1)*1000)
}

starterName('orange');


/*
Answer:
o
r
a
n
g
e
*/