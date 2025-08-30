const obj={
    name:'Rohit',
    arrowFunction:null,
    normalFunction:function(){
        console.log(this.name);
        this.arrowFunction=()=>{
            console.log(this.name);            
        }
    }
}

obj.normalFunction();
obj.arrowFunction(); 

// Answer: Rohit