<template>
  <el-row :gutter="60" style="width: 100%">
    <el-col :span="12">
      <h1>XIN CHÀO</h1>
      <h4>hãy cho chúng tôi biết thêm về bạn ...</h4>
      <el-form :model="formData" status-icon :rules="rules" ref="formData" class="register-form">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item prop="name">
              <span>Họ và tên</span>
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="gender">
              <span>Giới tính</span>
              <el-select v-model="formData.gender" placeholder="Select">
                <el-option
                  v-for="item in optionsGender"
                  :key="item"
                  :label="item"
                  :value="item">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item prop="phone">
              <span>Số điện thoại</span>
              <el-input v-model="formData.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="level">
              <span>Cấp bậc</span>
              <el-select v-model="formData.level" placeholder="Select">
                <el-option
                  v-for="item in optionsLevel"
                  :key="item"
                  :label="item"
                  :value="item">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-col :span="12">
              <el-form-item prop="dateGraduation">
                <span>Ngày tốt nghiệp</span>
                <el-date-picker
                  v-model="formData.dateGraduation"
                  type="date"
                  placeholder="Pick a day"
                  format="yyyy/MM/dd">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="birthday">
              <span>Ngày sinh</span>
              <el-date-picker
                v-model="formData.birthday"
                type="date"
                placeholder="Pick a day"
                format="yyyy/MM/dd">
              </el-date-picker>
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item style="text-align: center">
          <el-button class="btn btn-white" round @click="back">Trở lại</el-button>
          <el-button class="btn btn-blue" round @click="submitForm('formData')">Tiếp theo</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="12">
      <img width="80%" src="@/assets/register-2.svg" alt="register">
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
        optionsGender: ["Nam", "Nữ", "Khác"],
        optionsLevel: ["THCS","THPT","Cử nhân", "Thạc sỹ", "Tiến sỹ"],
        rules: {
          password: [
            {validator: validatePass, trigger: 'blur'}
          ],
          checkPassword: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          email: [
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
            this.$emit("changeTab",3)
          } else {
            AlertService.error("Error");
            return;
          }
        });
      },
      back(){
        this.$emit("changeTab",1);
      }
    }
  }
</script>
<style>
  .register-form {
    text-align: left;
  }
  .el-select {
    display: flex;
  }
</style>

