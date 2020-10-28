<template>
  <div class="login" v-loading="loading">
    <h1>ĐĂNG NHẬP QUẢN TRỊ VIÊN</h1>
    <el-form :model="formData" status-icon :rules="rules" ref="formData" class="login-form">
      <el-form-item prop="email">
        <span>Email</span>
        <el-input v-model.number="formData.email"></el-input>
      </el-form-item>
      <el-form-item label="" prop="pass">
        <span>Password</span>
        <el-input type="password" v-model="formData.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="" prop="checkPass">
        Password Confirm
        <el-input type="password" v-model="formData.checkPass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('formData')">Login</el-button>
        <el-button @click="resetForm('formData')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import AlertService from "@/services/AlertService.js";
  import LoginApi from "@/api/LoginApi";
  import Auth from "@/security/Authentication";
  import Pages from "../../router/Pages";

  export default {
    name: "LoginAdmin",
    data() {
      const checkEmail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('Please input the email'));
        }
        callback();
      };
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please input the password'));
        } else {
          if (this.formData.checkPass !== '') {
            this.$refs.formData.validateField('checkPass');
          }
          callback();
        }
      };
      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please input the password again'));
        } else if (value !== this.formData.pass) {
          callback(new Error('Two inputs don\'t match!'));
        } else {
          callback();
        }
      };
      return {
        loading: false,
        formData: {
          pass: '',
          checkPass: '',
          email: ''
        },
        rules: {
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          checkPass: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          email: [
            {validator: checkEmail, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      async submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let formData = {
              username: this.formData.email,
              password: this.formData.pass
            }
            this.login(formData);
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
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
<style scoped>
  .login {
    margin-left: auto;
    margin-right: auto;
    margin-top: 100px;
    max-width: 500px;

  }
  .login-form {
    text-align: left;
    font-weight: bold;
  }
</style>
