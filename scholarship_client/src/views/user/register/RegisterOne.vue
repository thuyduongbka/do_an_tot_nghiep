<template>
  <el-row :gutter="60" style="width: 100%">
    <el-col :span="12" :xs="24">
      <img width="80%" src="@/assets/register-1.svg" alt="register">
    </el-col>
    <el-col :span="12" :xs="24">
      <h1>Create Account </h1>
      <h3>Find colleges and scholarships that are right for you</h3>
      <el-form :model="formData" status-icon :rules="rules" ref="formData" class="register-form">
        <el-form-item prop="username">
          <span>Email Address</span>
          <el-input v-model="formData.username"></el-input>
        </el-form-item>
        <el-form-item label="" prop="password">
          <span>Password</span>
          <el-input type="password" v-model="formData.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="" prop="checkPassword">
          Re-password
          <el-input type="password" v-model="formData.checkPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button class="btn btn-blue" round @click="submitForm('formData')">Next</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>
<script>
  import AlertService from "@/services/AlertService";

  export default {
    name: "RegisterOne",
    props: ["formData"],
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
          if (this.formData.checkPassword !== '') {
            this.$refs.formData.validateField('checkPassword');
          }
          callback();
        }
      };
      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please input the password again'));
        } else if (value !== this.formData.password) {
          callback(new Error('Two inputs don\'t match!'));
        } else {
          callback();
        }
      };
      return {
        rules: {
          password: [
            {validator: validatePass, trigger: 'blur'}
          ],
          checkPassword: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          username: [
            {validator: checkEmail, trigger: 'blur'}
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
    }
  }
</script>
<style>
  .register-form {
    text-align: left;
  }
</style>

