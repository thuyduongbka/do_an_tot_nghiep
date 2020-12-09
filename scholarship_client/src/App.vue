<template>
  <div id="app" v-loading="loading">
    <router-view/>
    <div v-if="backTop" style="position: fixed; bottom: 20px; right: 20px; ">
      <el-button circle style="background-color: #b69de5; color: white" icon="el-icon-top" @click="scrollToTop"></el-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      loading: false,
      backTop: false,
    }
  },
  created() {
    this.checkAuth();
    window.addEventListener("scroll", this.changeTop);
  },
  methods: {
    changeTop(){
      if (window.document.documentElement.scrollTop > 0)
        this.backTop = true
      else this.backTop = false;
    },
    scrollToTop(){
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth'
      });
    },
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
    scroll-behavior: smooth;
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
    background-color: transparent;
  }
  .btn-blue {
    border: 2px solid #beaaef;
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
