<template>
  <el-row type="flex" justify="center" align="center">
    <el-col :span="8" class="row h-center v-center">
      <el-form :inline="false"
               class="form-style text-center"
               ref="errorForm">

        <el-form-item v-if="error403">
          <img style="width: 100px; height: 100px;" src="@/assets/error.png"
               alt="">
          <h2 style="font-size: 4em !important;">403</h2>
          <p style="font-size: 1.5em !important;">You are not authorized to access this page.</p>
          <el-link href="/">Go to homepage</el-link>
        </el-form-item>

        <el-form-item v-if="error404">
          <img style="width: 100px; height: 100px;" src="@/assets/error.png"
               alt="">
          <h2 style="font-size: 4em !important;">404</h2>
          <p style="font-size: 1.5em !important;">The page you requested does not exist.</p>
          <el-link href="/">Go to homepage</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: "Error",
    data() {
      return {
        errorMessage: "Error",
        error403: false,
        error404: false,
      }
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        let errorMessage = null;
        let error403 = false;
        let error404 = false;

        if (to.meta.errorMessage) {
          errorMessage = to.meta.errorMessage;
        }

        if (to.meta.error403) {
          error403 = to.meta.error403;
        }

        if (to.meta.error404) {
          error404 = to.meta.error404;
        }
        vm.setData(errorMessage, error403, error404);
        next();
      })
    },
    methods: {
      setData(errorMessage, error403, error404) {
        this.errorMessage = errorMessage;
        this.error403 = error403;
        this.error404 = error404;
      }
    }
  }

</script>

<style scoped>
  .el-form-item {
    line-height: 20px;
  }
</style>
