<template>
    <el-header class="header">
        <span style="text-decoration: underline; font-size: large">{{getUser.name}}</span>
        <el-avatar class="header-avatar" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
    </el-header>

</template>

<script>
    import Auth from "@/security/Authentication";

    export default {
      name: "HeaderAdmin",
      computed: {
        getUser(){
          return Auth.getCurrentUser();
        }
      }
    }
</script>

<style scoped>
    .header {
        text-align: right;
        font-size: 14px;
        color: #FFFFFF;
        padding: 10px;
        background-color: #36474F;
    }
    .header > * {
        margin-right: 20px;
        vertical-align: middle;
    }
    .header-avatar {
        width: 40px;
        height: 40px;
    }
</style>
