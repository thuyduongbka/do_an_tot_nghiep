<template>
  <el-row :gutter="60" style="width: 100%">
    <el-col :span="12">
      <img width="80%" src="@/assets/register-3.svg" alt="register">
    </el-col>
    <el-col :span="12">
      <h4>... và sở thích của bạn</h4>
      <el-form :model="formData" status-icon ref="formData" class="register-form">
        <el-form-item prop="listCountryId">
          <div>đất nước bạn muốn tới là: </div>
          <el-select multiple v-model="formData.listCountryId" filterable placeholder="Select">
            <el-option
              v-for="item in listCountry"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="listSchoolId">
          <div>trường bạn muốn học là: </div>
          <el-select multiple v-model="formData.listSchoolId" filterable placeholder="Select">
            <el-option
              v-for="item in listSchool"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="listSchoolId">
          <div>ngành bạn muốn theo đuổi là: </div>
          <el-select multiple v-model="formData.listMajorId" filterable placeholder="Select">
            <el-option
              v-for="item in listMajor"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button class="btn btn-white" round @click="back">Trở lại</el-button>
          <el-button class="btn btn-blue" round @click="submitForm('formData')">Hoàn thành</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>
<script>
  import AlertService from "@/services/AlertService";
  import AdminApi from "@/api/AdminApi";
  import CountryApi from "@/api/CountryApi";
  import SchoolApi from "@/api/SchoolApi";
  import MajorApi from "@/api/MajorApi";

  export default {
    name: "RegisterOne",
    props: ["formData"],
    created() {
      this.getData();
    },
    data() {
      return {
        listSchool: [],
        listCountry: [],
        listMajor: [],
      }
    },
    methods: {
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      back(){
        this.$emit("changeTab",2);
      },
      async submitForm(formName) {
        this.$emit("submit");
      },
      async getData(){
        try {
          await CountryApi.getAll().then(result => {
            this.listCountry = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
        try {
          await SchoolApi.getAll().then(result => {
            this.listSchool = result;
          })
        } catch (e) {
          AlertService.error(e)
        }
        try {
          await MajorApi.getAll().then(result => {
            this.listMajor= result;
          })
        } catch (e) {
          AlertService.error(e)
        }
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

