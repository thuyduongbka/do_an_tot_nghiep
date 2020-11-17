<template>
  <el-card v-loading="loading" v-if="changePassword" class="box-card" shadow="always">
    <div slot="header" class="clearfix">
      <span>Thay đổi mật khẩu</span>
      <el-button style="float: right; padding: 3px 0" type="text" @click="open=!open">Thay đổi</el-button>
    </div>
    <el-form v-if="open" ref="formData" :model="changePassword" :rules="rules" class="form" status-icon>
      <el-form-item prop="oldPassword">
        <div>Nhập password hiện tại</div>
        <el-input v-model="changePassword.oldPassword" type="password"></el-input>
      </el-form-item>

      <el-form-item prop="newPassword">
        <div>Nhập password mới</div>
        <el-input v-model="changePassword.newPassword" type="password"></el-input>
      </el-form-item>
      <el-form-item prop="checkPassword">
        <div>Nhập lại password mới</div>
        <el-input v-model="changePassword.checkPassword" type="password"></el-input>
      </el-form-item>

      <el-form-item style="text-align: center; margin-top: 60px;">
        <el-button class="btn btn-white" round @click="reset">Reset</el-button>
        <el-button class="btn btn-blue" round @click="submitForm('formData')">Đổi password</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>

import AlertService from "@/services/AlertService";
import EndUserApi from "@/api/EndUserApi";
import Auth from "@/security/Authentication";

export default {
  name: "ChangePassword",
  props: ["changePassword"],
  data() {
    const check = (rule, value, callback) => {
      if (!value || value === '') {
        return callback(new Error('Please input password'));
      }
      callback();
    };
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'));
      } else {
        if (this.changePassword.checkPassword !== '') {
          this.$refs.formData.validateField('checkPassword');
        }
        callback();
      }
    };
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password again'));
      } else if (value !== this.changePassword.newPassword) {
        callback(new Error('Two inputs don\'t match!'));
      } else {
        callback();
      }
    };
    return {
      open: false,
      rules: {
        oldPassword: [
          {validator: check, trigger: 'blur'}
        ],
        newPassword: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPassword: [
          {validator: validatePassCheck, trigger: 'blur'}
        ],
      },
      loading: false,
    }
  },
  methods: {
    reset() {
      this.$emit("reset");
    },
    async submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.changePass();
        } else {
          AlertService.error("Password nhập chưa đúng");
          return;
        }
      });
    },
    async changePass() {
      try {
        this.loading = true;
        await EndUserApi.changePassword(this.changePassword).then(result => {
          if (result === true){
            AlertService.success("Đổi mk thành công! Yêu cầu đăng nhập lại");
            setTimeout(function () {
              Auth.logout();
              location.reload();
            }, 3000);
          } else {
            AlertService.error("Mật khẩu nhập sai!");
          }
          this.loading = false;
        })
      } catch (e) {
        AlertService.error("Không đổi được mật khẩu");
        this.loading = false;
      }
    }
  }

}
</script>
<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 90%;
  margin: auto;
}

.form {
  text-align: left;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  color: #6637EB;
  font-weight: bold;
  text-decoration: underline;
}

.form > * {
  margin: 15px;
}

.btn {
  padding: 8px 20px 8px 20px;
}

</style>
