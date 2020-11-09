<template>
  <div>
    <el-container>
      <el-header>
        <Header></Header>
      </el-header>
      <el-main style="margin-top: 50px;">
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>
<script>
  import Header from "@/components/layouts/modules/HeaderUser";
  export default {
    name: "LayoutUser",
    components : {
      Header,
    }
  }
</script>
<style scoped>
  /deep/
  .el-main {
    margin-top: 30px;
  }
</style>
