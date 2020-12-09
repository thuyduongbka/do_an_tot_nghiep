<template>
  <el-container>
    <el-header>
      <header-admin></header-admin>
    </el-header>
    <el-container>
      <el-aside style="margin-top: 20px; border-right: 1px solid #36474F; min-height: 500px;" width="250px">
        <div class="menu">
          <div><a href="/admin"><p>Setting the Crawl source</p></a></div>
          <div><a href="/admin/report"><p>Report</p></a></div>
          <div><a href="/admin/admin-management"><p>Manage admin account</p></a></div>
          <div><a href="/admin/major-management"><p>Classification of Major</p></a></div>
          <div><a @click="logout" style="text-decoration: underline">Logout</a></div>
        </div>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
  import HeaderAdmin from "./modules/HeaderAdmin";
  import Auth from "@/security/Authentication";
  import router from "@/router";
  import Pages from "@/router/Pages";
  export default {
    name: "LayoutAdmin",
    components: {HeaderAdmin},
    methods: {
      logout(){
        Auth.logout();
        router.push({path: Pages.loginAdmin.path});
      }
    }
  }
</script>
<style>
  .menu {
    margin-top: 50%;
    font-weight: bold;
    font-size: large;
    text-align: left;
  }
  .menu > * {
    margin: 30px;
  }
  a {
    color: #36474F !important;
  }
</style>
