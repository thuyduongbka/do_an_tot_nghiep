<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  :root {
    --primary-color : #6637EB;
    --radius-value : 30px;
  }
  #app {
    font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #6637EB;
    font-size: 16px;
  }

  .btn {
    padding: 10px 20px 10px 20px;
    border-radius: 20px;
    font-size: 16px;
  }

  .btn-white {
    border: 2px solid #6637EB;
    color: #6637EB;
    background-color: white;
  }
  .btn-blue {
    border: 2px solid #6637EB;
    color: white;
    background-color: #6637EB;
  }
  .btn-pink{
    border: 2px solid #FF275A;
    color: white;
    background-color: #FF275A;
  }

  .float-right {
    float: right;
  }

  .float-left {
    float: left;
  }
</style>
