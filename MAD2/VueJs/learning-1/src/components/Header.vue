<script>
export default{
    data(){
        return{
            countp:0,
            countm:0,
        }
    },
}
</script>

<template>
    <button @click="countp++">Count is: {{ countp }}</button>
</template>

<style scoped>
button{
    background-color: black;
    color: white;
}
</style>