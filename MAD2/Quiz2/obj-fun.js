let a = 10;
let obj = {
    a:20,
    fn:function(){
        let a =30;
        return()=>{
            let a = 40;
            return this.a;
        };
    }
};
console.log(obj.fn()())