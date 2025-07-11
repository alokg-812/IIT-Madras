// let message = "Hello World";
    
var app = new Vue({
    el: "#app",
    data:{
        message:"Hello Vue World",
        count:0
    },
    methods:{
        sayHi: function(){
            this.message = "HI";
            this.count+=1;
        }
    }
})