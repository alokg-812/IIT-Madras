let start=5;
function check(){
    return new Promise((res, rej)=> {
        let a1=setInterval(() => {
            start++;
            if(start==7){
                console.log("Reached");
                clearInterval(a1);
                res("Pass");
                rej("FAIL");
            }
            else{
                console.log("Yet to reach");
            }
        }, 500);
    })
}

check()
.then(
    pass => console.log("passed")
)
.catch(
    fail => console.log("Failed")
)