<template>
  <el-header class="header">
    <div class="header-el float-left">
      <img style="width: 60px" src="@/assets/logo.png" />
      <a v-if="isUser" class="header-el-link" href="/">TRANG CHỦ</a>
      <a class="header-el-link" href="/search">TÌM KIẾM</a>
      <a v-if="isUser" class="header-el-link" href="/compare">SO SÁNH</a>
    </div>
    <div class="header-el float-right">
      <a v-if="isUser" href="/profile">TRANG CÁ NHÂN</a>
      <a v-if="isUser"><button class="btn btn-blue" round>Đăng Xuất</button></a>
      <a href="/login"><button class="btn btn-white" round>Đăng Nhập</button></a>
      <a href="/sign-up"><button class="btn btn-blue" round>Đăng Ký</button></a>
    </div>
  </el-header>
</template>
<script>
  import Auth from "@/security/Authentication";
  import Roles from "@/security/Roles";

  export default {
    name: "HeaderUser",
    computed: {
      isUser(){
        console.log(Auth.getCurrentRole())
        return Auth.getCurrentUser().hasAnyRoles(Roles.ROLE_ADMIN);
      }
    }
  }
</script>
<style scoped>
  .header {
    border-bottom: .5px solid #6637EB;
    font-weight: bold;
    min-height: 80px !important;
  }
  .header-el {
    height: 100%;
    display:flex;
    align-items:center;
  }

  .header-el > * {
    margin-right: 10px;
    margin-left: 10px;
    vertical-align: middle;
  }
  .header-el-link {
    color: #6637EB;
  }
</style>
