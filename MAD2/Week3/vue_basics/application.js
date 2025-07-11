// let message = "Hello World";
    
var app = new Vue({
    el: "#app",
    data:{
        message:"Hello Vue World",
        // count:0,
        visitor_name : "",
        visitors:[]
    },
    methods:{
        sayHi: function(){
            this.message = "HI";
            // this.count+=1;
            this.visitors.push(this.visitor_name);
            this.visitor_name="";
        }
    },
    computed:{
        count: function(){
            return this.visitors.length;
        }
    }
})