<template>
    <h1>Counter</h1>

    <div class="counter-wrapper">
        <button @click="decreament">DECREASE</button>

        <h2 :class="{'default': isActive, 'bg-danger':hasError}">{{ count }}</h2>

        <button @click="increament">INCREASE</button>
    </div>
</template>

<script>
export default{
    data(){
        return{
            count:0,
            isActive:true,
            hasError:true
        }
    },
    methods:{
        increament(){
            this.count++;
        },
        decreament(){
            this.count--;
        },
    },
}
</script>


<style scoped>
h1{
    margin: 20px 0;
    text-align: center;
}

.counter-wrapper{
    display: flex;
    justify-content: center;
    gap: 20px;
}

.default{
    color: red;
}

.bg-danger{
    background-color: rgb(8, 255, 243);
    padding: 10px;
    border-radius: 8px;
}
</style>