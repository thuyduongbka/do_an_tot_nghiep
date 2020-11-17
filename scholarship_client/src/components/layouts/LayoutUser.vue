<template>
  <div>
    <el-container>
      <el-header>
        <Header></Header>
      </el-header>
      <el-main style="margin-top: 30px;">
        <router-view></router-view>
      </el-main>
      <el-footer>
        <Footer></Footer>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
  import Header from "@/components/layouts/modules/HeaderUser";
  import Footer from "@/components/layouts/modules/Footer";
  export default {
    name: "LayoutUser",
    components : {
      Header,
      Footer
    }
  }
</script>
<style scoped>
  /deep/
  .el-header {
    padding: 0 0px;
  }
</style>
