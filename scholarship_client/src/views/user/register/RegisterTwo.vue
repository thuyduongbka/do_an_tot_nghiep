<template>
  <el-row :gutter="60" style="width: 100%">
    <el-col :span="12" :xs="24">
      <h1>Welcome! </h1>
      <h4>Tell us a bit about yourself.</h4>
      <el-form :model="formData" status-icon :rules="rules" ref="formData" class="register-form">
        <el-row :gutter="20">
          <el-col :span="12" :xs="24">
            <el-form-item prop="name">
              <span>Name</span>
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :xs="24">
            <el-form-item prop="gender">
              <span>Gender</span>
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
          <el-col :span="12" :xs="24">
            <el-form-item prop="phone">
              <span>Phone</span>
              <el-input v-model="formData.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" :xs="24">
            <el-form-item prop="level">
              <span>Desired education level</span>
              <el-select multiple v-model="formData.level" placeholder="Select">
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
          <el-col :span="12" :xs="24">
            <el-form-item prop="graduationDate">
              <span>Graduation Date</span>
              <el-date-picker
                v-model="formData.graduationDate"
                format="dd/MM/yyyy"
                placeholder="Pick a day"
                type="date">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12" :xs="24">
            <el-form-item prop="birthday">
              <span>Birthday</span>
              <el-date-picker
                v-model="formData.birthday"
                type="date"
                placeholder="Pick a day"
                format="dd/MM/yyyy">
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
    <el-col :span="12" :xs="24">
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
    const checkExist = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please input data'));
      }
      callback();
    };
    const checkExistArray = (rule, value, callback) => {
      if (value.length == 0) {
        return callback(new Error('Please input data'));
      }
      callback();
    };
      return {
        optionsGender: ["Male", "Female", "Other"],
        optionsLevel: ["Bachelor",
          "Fellowship",
          "Master",
          "PHD",
          "Postdoctoral",
          "Postgraduate",
          "Research"],
        rules: {
          name: [
            {validator: checkExist, trigger: 'blur'}
          ],
          level: [
            {validator: checkExistArray, trigger: 'blur'}
          ],
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

