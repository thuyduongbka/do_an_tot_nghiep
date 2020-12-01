<template>
  <div id="app" v-loading="loading">
    <router-view/>
  </div>
</template>

<script>
  import LoginApi from "@/api/LoginApi";
  import Auth from "@/security/Authentication";

  export default {
    name: 'App',
    data(){
      return {
        loading: false,
      }
    },
    created() {
      this.checkAuth();
    },
    methods : {
      async checkAuth() {
      //   console.log(this.$route.meta)
      //   debugger
      //   this.loading = true;
      //   await LoginApi.isAuthenticated().then((response) => {
      //     if (!response) {
      //       Auth.logout();
      //     }
      //     this.loading = false;
      //   }).catch((e) => {
      //     console.error(e);
      //     this.loading = false;
      //   })
      }
    }

  }
</script>

<style>
  :root {
    --primary-color : #6637EB;
    --radius-value : 30px;
  }
  #app {
    font-family: Tahoma, Geneva, sans-serif;
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
    transition: all .2s ease-in-out;
    cursor: pointer;
  }
  .btn:hover {
    transform: scale(1.1);
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
  .btn-white-pink {
    border: 2px solid #FF275A;
    color: #FF275A;
    background-color: white;
  }

  .float-right {
    float: right;
  }

  .float-left {
    float: left;
  }
  @media only screen and (max-width: 900px) {
    .btn {
      font-size: 12px;
      padding: 4px 7px 4px 7px;
    }

  }
  @media only screen and (max-width: 500px) {
    .btn {
      font-size: 12px;
      padding: 4px 7px 4px 7px;
    }
  }
</style>
