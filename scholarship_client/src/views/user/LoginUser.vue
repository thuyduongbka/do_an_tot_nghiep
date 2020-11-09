<template>
  <el-row :gutter="60" style="width: 100%" v-loading="loading">
    <el-col :span="12">
      <img width="80%" src="@/assets/register-1.svg" alt="register">
    </el-col>
    <el-col :span="12">
      <h1>xin chào, </h1>
      <h3>đăng nhập để xem nhiều học bổng hơn.</h3>
      <el-form :model="formData" status-icon :rules="rules" ref="formData" class="register-form">
        <el-form-item prop="username">
          <span>Địa chỉ Email</span>
          <el-input v-model="formData.username"></el-input>
        </el-form-item>
        <el-form-item label="" prop="password">
          <span>Mật khẩu</span>
          <el-input type="password" v-model="formData.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button class="btn btn-blue" round @click="submitForm('formData')">Đăng nhập</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>
<script>
  import AlertService from "@/services/AlertService";
  import LoginApi from "@/api/LoginApi";
  import Auth from "@/security/Authentication";
  import Pages from "@/router/Pages";

  export default {
    name: "LoginUser",
    data() {
      const checkEmpty= (rule, value, callback) => {
        if (!value) {
          return callback(new Error('Please input field'));
        }
        callback();
      };
      return {
        loading: false,
        formData: {
          password: '',
          username: '',
        },
        rules: {
          password: [
            {validator: checkEmpty, trigger: 'blur'}
          ],
          username: [
            {validator: checkEmpty, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      async submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$emit("changeTab",2);
          } else {
            AlertService.error("Error");
            return;
          }
        });
      },
      async login(formData){
        try {
          this.loading = true;
          await LoginApi.login(formData).then((response) => {
            if (response.status.code == 200) {
              Auth.setToken(response.data.accessToken);
              Auth.setCurrentUser(response.data.userDetails);
              this.$router.push({path: "/admin" + Pages.homeAdmin.path});
            } else if (response.status.code == 400) {
              AlertService.error("Error");
            }
          });
        } catch (e) {
          AlertService.error("Error");
          Auth.logout();
        } finally {
          this.loading = false;
        }
      },
    }
  }
</script>
<style>
  .register-form {
    text-align: left;
  }
</style>
