<template>
  <div id="app">
    <Counter />
  </div>
</template>

<script>
import Counter from './components/Counter.vue';

export default {
  name: 'App',
  components: {
    Counter
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  margin-top: 60px;
}
</style>
