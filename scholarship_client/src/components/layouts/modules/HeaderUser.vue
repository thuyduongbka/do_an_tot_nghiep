<template>
  <el-header class="header">
      <div class="header-el float-left">
        <img style="width: 60px" src="@/assets/logo.png"/>
        <a v-if="isUser" class="header-el-link" href="/">TRANG CHỦ</a>
        <a class="header-el-link" href="/search">TÌM KIẾM</a>
        <a v-if="isUser" class="header-el-link" href="/compare">SO SÁNH</a>
      </div>
      <div class="header-el float-right">
        <a v-if="isUser" href="/profile">{{ username }}</a>
        <a v-if="isUser" @click="logout()" href="/login" >
          <button class="btn btn-blue" round>Đăng Xuất</button>
        </a>
        <a v-if="!isUser" href="/login">
          <button class="btn btn-white" round>Đăng Nhập</button>
        </a>
        <a v-if="!isUser" href="/sign-up">
          <button class="btn btn-blue" round>Đăng Ký</button>
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
        this.isUser = false;
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
  }

  @media only screen and (max-width: 450px) {
    .header {
      font-size: 12px;
    }
  }

  @media only screen and (max-width: 450px) {

    .header {
      font-size: 10px;
      min-height: 80px !important;
    }

    .header-el > * {
      margin-right: 5px;
      margin-left: 5px;
      vertical-align: middle;
    }
  }
</style>
