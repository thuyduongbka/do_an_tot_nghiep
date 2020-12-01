<template>
  <el-header class="header">
      <div class="header-el float-left">
        <a class="header-el-link" href="/">
          <img style="width: 150px" src="@/assets/logo-2.png">
        </a>
        <a class="header-el-link" href="/" >
          home
        </a>
        <a class="header-el-link" href="/search" >
          search
        </a>
        <a  class="header-el-link" v-if="isUser" href="/compare">compare</a>
      </div>
      <div class="header-el float-right">
        <a  class="header-el-link" v-if="isUser" href="/profile">{{ username }}</a>
        <a  class="header-el-link" v-if="isUser" @click="logout()"  >
          <button class="btn btn-blue" round>Logout</button>
        </a>
        <a  class="header-el-link" v-if="!isUser" href="/login">
          <button class="btn btn-white" round>Login</button>
        </a>
        <a  class="header-el-link" v-if="!isUser" href="/sign-up">
          <button class="btn btn-blue" round>Signup</button>
        </a>
      </div>
  </el-header>
</template>
<script>
  import Auth from "@/security/Authentication";
  import router from "@/router";
  import Pages from "@/router/Pages";
  import Roles from "@/security/Roles";

  export default {
    name: "HeaderUser",
    data(){
      return {
        isUser: Auth.getCurrentRole() === Roles.ROLE_END_USER,
        username: Auth.getCurrentUser().name,
      }
    },
    methods: {
      logout(){
        Auth.logout();
        location.reload();
      }
    }
  }
</script>
<style scoped>
  .header {
    border-bottom: 0.5px solid #6637EB;
    font-weight: bold;
    min-height: 80px !important;
  }

  .header-el {
    height: 100%;
    display: flex;
    align-items: center;
  }

  .header-el > * {
    margin-right: 10px;
    margin-left: 10px;
    vertical-align: middle;
  }

  .header-el-link {
    color: #6637EB;
    min-width: 80px;
    transition: all .2s ease-in-out;
  }
  .header-el-link:hover {
    color: #FF275A;
    transform: scale(1.1);
  }

  @media only screen and (max-width: 500px) {

    .header {
      font-size: 10px;
      min-height: 100px !important;
      display: flow-root;
    }

    .header-el > * {
      margin-right: 5px;
      margin-left: 5px;
      vertical-align: middle;
    }
    .header-el-link {
      min-width: 0px;
    }
    img {
      width: 60px !important;
    }
  }
  @media only screen and (max-width: 400px) {
    .header {
      font-size: 10px;
      min-height: 100px !important;
      display: flex;
      flex-direction: column;
    }
  }
</style>
